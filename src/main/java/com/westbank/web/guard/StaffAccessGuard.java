package com.westbank.web.guard;

import com.westbank.web.controller.StaffSession;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class StaffAccessGuard extends AccessGuard {

    static final String STAFF_LOGIN_VIEW = "/staff/login";

    public StaffAccessGuard() {
        super(STAFF_LOGIN_VIEW);
    }

    @Override
    protected void beforeRedirecting(HttpSession session) {
    }

    @Override
    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION) != null;
    }
}

