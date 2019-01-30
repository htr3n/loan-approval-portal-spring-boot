package com.westbank.web.controller;

import com.westbank.demo.ExampleData;
import com.westbank.entity.Role;
import com.westbank.entity.Staff;
import com.westbank.repository.RoleRepository;
import com.westbank.repository.StaffRepository;
import com.westbank.service.StaffService;
import com.westbank.web.form.LoginForm;
import com.westbank.web.validator.StaffLoginValidator;
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
import java.util.HashMap;
import java.util.Map;

@Controller
//@Import(StaffUtil.class)
@RequestMapping(StaffLoginController.PATH)
public class StaffLoginController {

    static final String PATH = "/staff/login";

    private static final Logger LOG = LoggerFactory.getLogger(StaffLoginController.class);

    private static final String STAFF_LOGIN_VIEW = "/staff/login";
    private static final String REDIRECT_CREDIT_BROKER_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/staff/broker";
    private static final String REDIRECT_CLERK_SUPERVISOR_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/staff/clerk";
    private static final String REDIRECT_MANAGER_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/staff/manager";
    private static Map<String, String> ROLE_TO_VIEW;

    static {
        ROLE_TO_VIEW = new HashMap<>();
        ROLE_TO_VIEW.put(Role.ID_MANAGER, REDIRECT_MANAGER_VIEW);
        ROLE_TO_VIEW.put(Role.ID_CREDIT_BROKER, REDIRECT_CREDIT_BROKER_VIEW);
        ROLE_TO_VIEW.put(Role.ID_POST_PROCESSING_CLERK, REDIRECT_CLERK_SUPERVISOR_VIEW);
        ROLE_TO_VIEW.put(Role.ID_SUPERVISOR, REDIRECT_CLERK_SUPERVISOR_VIEW);
    }

    private static final String LOGIN_FORM_MODEL_ATTRIBUTE = "loginForm";

    @Autowired
    private StaffLoginValidator validator;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @ModelAttribute(LOGIN_FORM_MODEL_ATTRIBUTE)
    public LoginForm setupStaffLoginForm() {
        return ExampleData.broker();
        //return new StaffLoginForm();
    }

    @GetMapping
    public String prepare(@ModelAttribute LoginForm form, HttpSession session) {
        session.removeAttribute(StaffSession.PROCESS_STATUS);
        session.removeAttribute(StaffSession.PROCESS_STATUS_KEY);
        session.removeAttribute(StaffSession.ACTIVE_AUTHENTICATION);
        session.removeAttribute(StaffSession.ACTIVE_ROLE);
        return STAFF_LOGIN_VIEW;
    }

    @PostMapping
    public String processSubmission(@ModelAttribute LoginForm form, BindingResult result, HttpSession session) {
        LOG.debug("Processing staff log-in form {}", form);
        LOG.debug("Staff login form is being validated");
        validator.validate(form, result);

        if (result.hasFieldErrors()) {
            LOG.debug("Form validation failed. Stay!");
            return STAFF_LOGIN_VIEW;
        }

        final Staff staff = staffService.authenticate(form.getLoginId(), form.getLoginPassword());

        boolean isAuthenticated = (staff != null);

        if (isAuthenticated) {
            LOG.debug("Successful authentication.");
            LOG.debug("Setting '{}' -> '{}'", StaffSession.ACTIVE_AUTHENTICATION, staff);
            session.setAttribute(StaffSession.ACTIVE_AUTHENTICATION, staff);
            session.setAttribute(StaffSession.ACTIVE_ROLE, staff.getRole().getId());

            final String dispatchedView = getDispatchingViewByRole(staff.getRole());

            if (dispatchedView != null) {
                LOG.debug("Dispatching to '{}'", dispatchedView);
                return dispatchedView;
            }
        } else {
            LOG.debug("Authentication failed. Stay!");
            session.setAttribute(StaffSession.PROCESS_STATUS, StaffSession.PROCESS_STATUS_ERROR);
            session.setAttribute(StaffSession.PROCESS_STATUS_KEY, StaffSession.MSG_LOGIN_FAILED);
        }
        return STAFF_LOGIN_VIEW;
    }

    private String getDispatchingViewByRole(Role role) {
        if (role != null)
            return ROLE_TO_VIEW.get(role.getId());
        return null;
    }

}
