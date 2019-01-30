package com.westbank.web.controller;

import com.westbank.web.form.TaskForm;
import com.westbank.web.guard.StaffAccessGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(StaffPortalController.PATH)
public class StaffPortalController {

    static final String PATH = "/staff/portal";
    private static final String PORTAL_VIEW = "staff/portal";

    @Autowired
    private StaffTaskHandler staffUtil;

    @Autowired
    StaffAccessGuard guard;

    @ModelAttribute("loanList")
    public TaskForm setupTaskForm() {
        return new TaskForm();
    }

    @GetMapping
    public String prepare(HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        this.staffUtil.prepare(session);
        return PORTAL_VIEW;
    }

    @PostMapping
    public String process(@ModelAttribute TaskForm form, BindingResult result, HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        return PORTAL_VIEW;
    }

}
