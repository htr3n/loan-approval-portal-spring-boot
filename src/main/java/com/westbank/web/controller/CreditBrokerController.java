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
@RequestMapping(CreditBrokerController.PATH)
public class CreditBrokerController {

    private static final Logger LOG = LoggerFactory.getLogger(CreditBrokerController.class);

    static final String PATH = "/staff/broker";
    static final String BROKER_VIEW = "staff/broker";

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
        return BROKER_VIEW;
    }

    @PostMapping
    public String process(TaskForm form, HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        LOG.debug("Processing {}", form);
        LOG.debug("Authenticated staff: {}", session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION));
        executeCreditBrokerTask(form, session);
        return BROKER_VIEW;
    }

    private void executeCreditBrokerTask(TaskForm form, HttpSession session) {
        LOG.debug("Authenticated staff: {}", session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION));
        if (TaskForm.ACTION_ACCEPT.equals(form.getAction())) {
            final Staff broker = (Staff) session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION);
            try {
                LOG.debug("Invoking the loan approval process via its proxy");
                final LoanFile loanFile = loanFileRepository.findById(form.getLoanFileId()).orElse(null);
                if (loanFile != null) {
                    staffTaskHandler.updateLoanFileStatus(loanFile, LoanFileStatus.UNDER_CONSIDERATION, broker);
                }
                processProxy.processedByCreditBroker(broker.getId(), broker.getRole().getId(), form.getLoanFileId());
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
