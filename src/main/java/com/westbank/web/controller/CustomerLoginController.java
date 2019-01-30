package com.westbank.web.controller;

import com.westbank.demo.ExampleData;
import com.westbank.entity.Customer;
import com.westbank.service.CustomerService;
import com.westbank.web.form.LoginForm;
import com.westbank.web.validator.CustomerLoginValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(CustomerLoginController.PATH)
public class CustomerLoginController {

    static final String PATH = "/login";
    static final String LOGIN_VIEW = "customer/login";
    static final String PORTAL_VIEW = "/portal";
    static final String REDIRECT_PORTAL_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + PORTAL_VIEW;
    static final String LOGIN_FORM_ATTRIBUTE = "loginForm";

    private static final Logger LOG = LoggerFactory.getLogger(CustomerLoginController.class);

    private final CustomerService customerService;
    private final CustomerLoginValidator validator;

    @Autowired
    public CustomerLoginController(CustomerService customerService, CustomerLoginValidator validator) {
        this.customerService = customerService;
        this.validator = validator;
    }

    @ModelAttribute(LOGIN_FORM_ATTRIBUTE)
    public LoginForm createForm() {
        return ExampleData.alice();
        //return new LoginForm();
    }

    @GetMapping
    public String get(HttpSession session) {
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_LOGIN);
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);
        return LOGIN_VIEW;
    }

    @PostMapping
    public String post(@ModelAttribute(LOGIN_FORM_ATTRIBUTE) LoginForm loginForm,
                       BindingResult result,
                       HttpSession session) {

        // first, remove the session variables
        // TODO
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);

        LOG.debug("Login form is validated ");
        validator.validate(loginForm, result);

        if (result.hasFieldErrors()) {
            LOG.debug("Form validation failed. Stay!");
            return LOGIN_VIEW;
        } else { // processing authentication
            LOG.debug("Login form elements are valid ");
            final Customer customer = customerService.authenticate(loginForm.getLoginId(), loginForm.getLoginPassword());

            if (customer != null) { // authenticated successfully
                LOG.debug("Successful authentication for {} ... ", customer);
                LOG.debug("Forwarding to {} ... ", PORTAL_VIEW);
                session.setAttribute(CustomerSession.ACTIVE_AUTHENTICATION, customer);
                session.setAttribute(CustomerSession.TITLE, customer.getTitle());
                session.setAttribute(CustomerSession.FIRSTNAME, customer.getFirstName());
                session.setAttribute(CustomerSession.LASTNAME, customer.getLastName());
                session.setAttribute(CustomerSession.EMAIL, customer.getEmail());
                session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_PORTAL);
                return REDIRECT_PORTAL_VIEW;
            } else {
                LOG.debug("Authentication failed. Stay!");
                session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_ERROR);
                session.setAttribute(CustomerSession.PROCESS_STATUS_KEY, CustomerSession.MSG_LOGIN_FAILED);
                return LOGIN_VIEW;
            }
        }
    }
}
