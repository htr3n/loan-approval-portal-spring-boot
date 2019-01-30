package com.westbank.web.controller;

import com.westbank.entity.LoanContract;
import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.entity.Staff;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Component
public class StaffTaskHandler {

    private static final Logger LOG = LoggerFactory.getLogger(StaffTaskHandler.class);

    @Autowired
    private LoanFileRepository loanFileRepository;
    @Autowired
    private LoanContractRepository loanContractRepository;

    public void prepare(HttpSession session) {
        clearProcessStatus(session);
        reload(session);
    }

    public void clearProcessStatus(final HttpSession session) {
        session.removeAttribute(StaffSession.PROCESS_STATUS);
        session.removeAttribute(StaffSession.PROCESS_STATUS_KEY);
    }

    public void setProcessStatus(final HttpSession session, Object status, Object statusKey) {
        session.setAttribute(StaffSession.PROCESS_STATUS, status);
        session.setAttribute(StaffSession.PROCESS_STATUS_KEY, statusKey);
    }

    public void setProcessInvocationError(final HttpSession session) {
        setProcessStatus(session, StaffSession.PROCESS_STATUS_ERROR, StaffSession.MSG_INVOCATION_ERR);
    }

    public static void clearStaffAuthentication(final HttpSession session) {
        session.removeAttribute(StaffSession.ACTIVE_AUTHENTICATION);
    }

    public void updateLoanFileStatus(LoanFile loanFile, LoanFileStatus status, Staff updatedBy) {
        loanFile.setStatus(status);
        loanFile.setUpdatedDate(LocalDate.now());
        loanFile.setUpdatedBy(updatedBy);
        loanFile.setUpdatedByRole(updatedBy.getRole().getId());
        loanFileRepository.save(loanFile);
    }

    public void reload(HttpSession session) {
        final List<LoanFile> loans = loanFileRepository.findAllWithFetch();
        LOG.trace("Loan files: {}", loans);
        session.setAttribute(StaffSession.MANAGED_LOAN_FILES, loans);

        final List<LoanContract> contracts = loanContractRepository.findAllWithFetch();
        LOG.trace("Loan contracts: {}", contracts);
        session.setAttribute(StaffSession.MANAGED_LOAN_CONTRACTS, contracts);

    }
}
