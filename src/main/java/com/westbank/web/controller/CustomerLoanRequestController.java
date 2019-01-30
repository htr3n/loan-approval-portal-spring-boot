package com.westbank.web.controller;

import com.westbank.demo.ExampleData;
import com.westbank.entity.Customer;
import com.westbank.repository.AgencyRepository;
import com.westbank.web.form.LoanForm;
import com.westbank.web.guard.CustomerAccessGuard;
import com.westbank.web.validator.NewRequestValidator;
import com.westbank.ws.LoanApprovalProcessProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(CustomerLoanRequestController.PATH)
public class CustomerLoanRequestController {

    private static Logger LOG = LoggerFactory.getLogger(CustomerLoanRequestController.class);

    static final String PATH = "/request";
    static final String REQUEST_VIEW = "customer/loanrequest";
    static final String NEW_REQUEST_INFO = "customer/info";

    @Autowired
    private NewRequestValidator validator;
    @Autowired
    private LoanApprovalProcessProxy processProxy;
    @Autowired
    private CustomerAccessGuard guard;
    @Autowired
    private AgencyRepository agencyRepository;

    @ModelAttribute("form")
    public LoanForm setupForm() {
        return ExampleData.applicationForm(false);
    }

    @GetMapping
    public String prepare(HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_REQUEST);
        session.setAttribute(CustomerSession.FORM_AGENCIES, agencyRepository.findAll());
        removeSessionAttributes(session);
        LOG.debug("Return the view {}", REQUEST_VIEW);
        return REQUEST_VIEW;
    }

    private void removeSessionAttributes(HttpSession session) {
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);
        session.removeAttribute(CustomerSession.TITLE);
        session.removeAttribute(CustomerSession.FIRSTNAME);
        session.removeAttribute(CustomerSession.LASTNAME);
        session.removeAttribute(CustomerSession.EMAIL);
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        final CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @PostMapping
    public String processSubmission(@ModelAttribute("form") LoanForm form,
                                    BindingResult result,
                                    HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        LOG.debug("Removing relevant session attributes ", session);
        removeSessionAttributes(session);
        LOG.debug("Start validating the new loan request form {}", form);
        validator.validate(form, result);

        if (result.hasFieldErrors()) {
            LOG.error("Form validation failed. Stay!");
            for (FieldError fe : result.getFieldErrors()) {
                LOG.debug("{} --> {}", fe.getField(), fe.getRejectedValue());
            }
            return REQUEST_VIEW;
        } else {
            final Customer customer = (Customer) session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
            session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_REQUEST);
            form.setBorrowerCustomerId(customer.getId());
            LOG.debug("Deliver the new request to the Loan Approval process");
            if (processProxy.startProcess(form, customer)) {
                session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_OK);
                return NEW_REQUEST_INFO;
            } else {
                session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_ERROR);
                session.setAttribute(CustomerSession.PROCESS_STATUS_KEY, CustomerSession.MSG_INVOCATION_ERR);
            }
        }
        return REQUEST_VIEW;
    }
}
