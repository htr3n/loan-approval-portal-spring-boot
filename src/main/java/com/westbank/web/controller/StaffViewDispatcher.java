package com.westbank.web.controller;

import com.westbank.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StaffViewDispatcher {

    private static final Logger LOG = LoggerFactory.getLogger(StaffViewDispatcher.class);

    static final String STAFF_LOGIN_VIEW = "staff/login";
    private static final String BROKER_VIEW = "redirect:/staff/broker";
    private static final String SUPERVISOR_CLERK_VIEW = "redirect:/staff/clerk";
    private static final String MANAGER_VIEW = "redirect:/staff/manager";

    public String dispatch(String inputRole) {

        if (Role.ID_CREDIT_BROKER.equalsIgnoreCase(inputRole)) {
            return BROKER_VIEW;
        } else if (Role.ID_POST_PROCESSING_CLERK.equalsIgnoreCase(inputRole)
                || Role.ID_SUPERVISOR.equalsIgnoreCase(inputRole)) {
            return SUPERVISOR_CLERK_VIEW;
        } else if (Role.ID_MANAGER.equalsIgnoreCase(inputRole)) {
            return MANAGER_VIEW;
        } else {
            LOG.error("Role '{}' has not been supported yet.", inputRole);
        }
        return null;
    }
}
