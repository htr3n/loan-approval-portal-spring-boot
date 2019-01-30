package com.westbank.web.controller;

import com.westbank.entity.LoanContract;
import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.entity.Staff;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.web.form.TaskForm;
import com.westbank.web.guard.StaffAccessGuard;
import com.westbank.ws.LoanApprovalProcessProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(ManagerController.PATH)
public class ManagerController {

    static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);
    static final String PATH = "/staff/manager";
    static final String MANAGER_VIEW = "staff/manager";

    @Autowired
    private StaffTaskHandler staffTaskHandler;
    @Autowired
    StaffAccessGuard guard;
    @Autowired
    private LoanApprovalProcessProxy processProxy;
    @Autowired
    private LoanFileRepository loanFileRepository;
    @Autowired
    private LoanContractRepository loanContractRepository;

    @ModelAttribute("loanList")
    public TaskForm setupTaskForm() {
        return new TaskForm();
    }

    @GetMapping
    public String prepare(HttpSession session) {
        if (!guard.isAuthenticated(session))
            return guard.redirect(session);
        this.staffTaskHandler.prepare(session);
        return MANAGER_VIEW;
    }

    @PostMapping
    public String process(TaskForm form, BindingResult result, HttpSession session) {
        executeManagerTask(form, session);
        return MANAGER_VIEW;
    }

    void executeManagerTask(TaskForm form, HttpSession session) {
        LOG.debug("Handling the manager task form {}", form);
        final String action = form.getAction();
        final Staff manager = (Staff) session.getAttribute(StaffSession.ACTIVE_AUTHENTICATION);
        final String staffName = manager.getFirstName() + " " + manager.getLastName();
        final LoanFile loanFile = loanFileRepository.findById(form.getLoanFileId()).orElse(null);

        if (TaskForm.ACTION_SIGN.equals(action)) {
            LOG.debug("Send manager's signature to the process");
            final Long contractId = form.getContractId();
            boolean isOK = processProxy.signedContractByManager(manager.getId(), staffName, form.getLoanFileId(), contractId);
            if (!isOK) {
                this.staffTaskHandler.setProcessInvocationError(session);
            } else {
                LOG.debug("The contract is signed. Update the contract's status.");
                final Optional<LoanContract> contract = loanContractRepository.findById(contractId);
                if (contract.isPresent()) {
                    contract.get().setSignedByManager(LocalDate.now());
                    loanContractRepository.save(contract.get());
                } else {
                    LOG.error("Cannot update the status of the contract '{}'", contractId);
                }
            }

        } else {
            boolean isGranted = false;
            LOG.debug("Send manager's decision on high-risk loan");
            LOG.debug("High-risk loan is granted? {}", isGranted);
            boolean isOK = processProxy.informManagerDecision(manager.getId(), manager.getRole().getId(), form.getLoanFileId(), isGranted);
            if (!isOK) {
                this.staffTaskHandler.setProcessInvocationError(session);
            } else {
                // the invocation is successful, update the loan
                // file status
                if (TaskForm.ACTION_GRANT.equals(action)) {
                    isGranted = true;
                    loanFile.setStatus(LoanFileStatus.APPROVED);
                } else if (TaskForm.ACTION_REJECT.equals(action)) {
                    isGranted = false;
                    loanFile.setStatus(LoanFileStatus.REJECTED);
                }
                loanFile.setUpdatedDate(LocalDate.now());
                loanFile.setUpdatedBy(manager);
                loanFile.setUpdatedByRole(manager.getRole().getId());
                loanFileRepository.save(loanFile);
            }
        }
    }
}
