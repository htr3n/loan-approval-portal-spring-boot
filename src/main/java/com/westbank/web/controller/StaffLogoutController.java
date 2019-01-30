package com.westbank.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpSession;

@Controller
public class StaffLogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(StaffLogoutController.class);

    static final String PATH = "/staff/logout";
    static final String LOGIN_VIEW = "/staff/login";
    static final String REDIRECT_STAFF_LOGIN_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + LOGIN_VIEW;

    @RequestMapping(PATH)
    public String logout(HttpSession session) {
        LOG.debug("Removing session attributes ... ");
        session.removeAttribute(StaffSession.ACTIVE_AUTHENTICATION);
        session.removeAttribute(StaffSession.ACTIVE_ROLE);
        LOG.debug("Redirecting to '{}'", REDIRECT_STAFF_LOGIN_VIEW);
        return REDIRECT_STAFF_LOGIN_VIEW;
    }
}
