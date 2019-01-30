package com.westbank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerHomeController {

    static final String PATH = "/";
    static final String INDEX_VIEW = "customer/index";
    static final String PORTAL_VIEW = "redirect:/portal";
    static final String NEW_REQUEST_VIEW = "redirect:/request";
    static final String PROFILE_VIEW = "redirect:/profile";

    @RequestMapping(CustomerHomeController.PATH)
    public String index(HttpSession session) {
        final Object sessionId = session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
        final Object nav = session.getAttribute(CustomerSession.NAV_INDEX);
        if (sessionId == null) {
            session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_HOME);
        } else {
            if (CustomerSession.NAV_PORTAL.equals(nav)) {
                return PORTAL_VIEW;
            } else if (CustomerSession.NAV_REQUEST.equals(nav)) {
                return NEW_REQUEST_VIEW;
            } else if (CustomerSession.NAV_PROFILE.equals(nav)) {
                return PROFILE_VIEW;
            }
        }
        return INDEX_VIEW;
    }
}
