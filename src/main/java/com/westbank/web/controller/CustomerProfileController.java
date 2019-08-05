package com.westbank.web.controller;

import com.westbank.entity.Address;
import com.westbank.entity.Customer;
import com.westbank.repository.CustomerRepository;
import com.westbank.service.CustomerService;
import com.westbank.web.form.LoanForm;
import com.westbank.web.guard.CustomerAccessGuard;
import com.westbank.web.validator.CustomerProfileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(CustomerProfileController.PATH)
public class CustomerProfileController {

    private static Logger LOG = LoggerFactory.getLogger(CustomerProfileController.class);

    static final String PATH = "/profile";

    static final String PROFILE_VIEW = "customer/profile";

    @Autowired
    CustomerProfileValidator validator;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAccessGuard guard;

    @ModelAttribute("applicationForm")
    public LoanForm setupApplicationForm(HttpSession session) {
        return loadCustomerProfile(session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION));
    }

    @GetMapping
    public String prepare(HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        LOG.debug("Return the view '{}'", PROFILE_VIEW);
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_PROFILE);
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);
        return PROFILE_VIEW;
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @PostMapping
    public String updateProfile(LoanForm form, BindingResult result, HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        LOG.debug("Input application form:\n{}", form);
        boolean updatedOK = false;
        final Object sessionId = session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_PROFILE);
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);

        LOG.debug("Start validating profile update form");
        validator.validate(form, result);

        if (!result.hasFieldErrors()) {
            LOG.debug("Input data valid. Start updating customer profile");
            final Customer customer = customerService.updateProfile((Long) sessionId, form);
            if (customer != null) {
                updatedOK = true;
            }
        } else {
            for (FieldError fe : result.getFieldErrors()) {
                LOG.debug("{} --> {}", fe.getField(), fe.getRejectedValue());
            }
        }

        if (updatedOK) {
            session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_OK);
            session.setAttribute(CustomerSession.PROCESS_STATUS_KEY, CustomerSession.MSG_PROFILE_UPDATE_OK);
        } else {
            session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_ERROR);
            session.setAttribute(CustomerSession.PROCESS_STATUS_KEY, CustomerSession.MSG_CUSTOMER_DAO_ERR);
        }
        return PROFILE_VIEW;
    }

    private LoanForm loadCustomerProfile(Object sessionId) {
        final LoanForm form = new LoanForm();
        try {
            Customer customer = (Customer) sessionId;
            if (customer != null) {
                form.setBorrowerTitle(customer.getTitle());
                form.setBorrowerFirstName(customer.getFirstName());
                form.setBorrowerLastName(customer.getLastName());
                form.setBorrowerMaritalStatus(customer.getMaritalStatus());
                form.setBorrowerDateOfBirth(customer.getDateOfBirth());
                form.setBorrowerOccupation(customer.getOccupation());
                form.setBorrowerLengthOfService(customer.getLengthOfService());
                form.setBorrowerIncome(customer.getIncome());
                form.setBorrowerPhone(customer.getPhone());
                form.setBorrowerMobilePhone(customer.getMobilePhone());
                form.setBorrowerEmail(customer.getEmail());
                form.setBorrowerNumberOfChildren(customer.getNumberOfChildren());
                form.setBorrowerMaritalStatus(customer.getMaritalStatus());

                Address address = customer.getAddress();

                if (address != null) {
                    form.setBorrowerStreet(address.getStreet());
                    form.setBorrowerCity(address.getCity());
                    form.setBorrowerZipcode(address.getZipcode());
                    form.setBorrowerState(address.getState());
                }
            }
        } catch (NumberFormatException ignored) { // casting sessionId
            LOG.error("Unable to cast session ID {} to customer ID", sessionId);
        }
        return form;
    }
}
