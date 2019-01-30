package com.westbank.web.controller;

import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.entity.Staff;
import com.westbank.repository.LoanFileRepository;
import com.westbank.web.form.TaskForm;
import com.westbank.web.guard.StaffAccessGuard;
import com.westbank.ws.LoanApprovalProcessProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(ClerkAndSupervisorController.PATH)
public class ClerkAndSupervisorController {

    private static final Logger LOG = LoggerFactory.getLogger(ClerkAndSupervisorController.class);

    static final String PATH = "/staff/clerk";
    static final String CLERK_OR_SUPERVISOR_VIEW = "staff/clerk";

    @Autowired
    private StaffTaskHandler staffTaskHandler;
    @Autowired
    StaffAccessGuard guard;
    @Autowired
    private LoanApprovalProcessProxy processProxy;
    @Autowired
    private LoanFileRepository loanFileRepository;

    @ModelAttribute("loanList")
    public TaskForm setupTaskForm() {
        return new TaskForm();
    }

    @GetMapping
    public String prepare(HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        this.staffTaskHandler.prepare(session);
        return CLERK_OR_SUPERVISOR_VIEW;
    }

    @PostMapping
    public String process(TaskForm form, HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        executeClerkOrSupervisorTask(form, session);
        return CLERK_OR_SUPERVISOR_VIEW;
    }

    private void executeClerkOrSupervisorTask(TaskForm form, HttpSession session) {
        if (TaskForm.ACTION_ACCEPT.equals(form.getAction())) {
            final Staff staff = (Staff) session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION);
            try {
                LOG.debug("Invoke the process via its proxy");
                final LoanFile loanFile = loanFileRepository.findById(form.getLoanFileId()).orElse(null);
                if (loanFile != null) {
                    staffTaskHandler.updateLoanFileStatus(loanFile, LoanFileStatus.WORTHINESS_ANALYSIS, staff);
                }
                processProxy.processedByClerkOrSupervisor(staff.getId(), staff.getRole().getId(), form.getLoanFileId());
                staffTaskHandler.reload(session);
            } catch (Exception e) {
                LOG.error("Unable to invoke Loan Approval process due to " + e.getMessage(), e);
                staffTaskHandler.setProcessInvocationError(session);
            }
        } else if (TaskForm.ACTION_RELOAD.equals(form.getAction())) {
            staffTaskHandler.reload(session);
        } else {
            LOG.error("The action {} is not supported", form.getAction());
            staffTaskHandler.reload(session);
        }
    }

}
