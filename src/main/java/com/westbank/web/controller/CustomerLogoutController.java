package com.westbank.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerLogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerLogoutController.class);

    static final String PATH = "/logout";
    static final String LOGIN_VIEW = "/login";
    static final String REDIRECT_LOGIN_VIEW = UrlBasedViewResolver.REDIRECT_URL_PREFIX + LOGIN_VIEW;

    @RequestMapping(PATH)
    public String logout(HttpSession session) {
        LOG.debug("Removing all session attributes ... ");
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_LOGIN);
        session.removeAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
        session.removeAttribute(CustomerSession.TITLE);
        session.removeAttribute(CustomerSession.FIRSTNAME);
        session.removeAttribute(CustomerSession.LASTNAME);
        session.removeAttribute(CustomerSession.EMAIL);
        LOG.debug("Redirecting to '{}'", REDIRECT_LOGIN_VIEW);
        return REDIRECT_LOGIN_VIEW;
    }
}
