package com.westbank.web.guard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpSession;

public abstract class AccessGuard {

    private static Logger LOG = LoggerFactory.getLogger(AccessGuard.class);

    private final String redirectView;

    public AccessGuard(String redirectView) {
        this.redirectView = UrlBasedViewResolver.REDIRECT_URL_PREFIX + redirectView;
    }

    private String getRedirectView() {
        return redirectView;
    }

    public String redirect(HttpSession session) {
        this.beforeRedirecting(session);
        LOG.debug("Redirecting to '{}'", getRedirectView());
        return getRedirectView();
    }

    protected abstract void beforeRedirecting(HttpSession session);

    public abstract boolean isAuthenticated(HttpSession session);
}
