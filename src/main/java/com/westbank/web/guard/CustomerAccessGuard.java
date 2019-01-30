package com.westbank.web.guard;

import com.westbank.web.controller.CustomerSession;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class CustomerAccessGuard extends AccessGuard {

    static final String CUSTOMER_LOGIN_VIEW = "/login";

    public CustomerAccessGuard() {
        super(CUSTOMER_LOGIN_VIEW);
    }

    @Override
    protected void beforeRedirecting(HttpSession session) {
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_LOGIN);
    }

    @Override
    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION) != null;
    }
}
