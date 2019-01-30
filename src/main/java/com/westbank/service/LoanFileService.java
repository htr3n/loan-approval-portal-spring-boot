package com.westbank.service;

import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.repository.LoanFileRepository;
import com.westbank.ws.business.creditworthiness._2019._01.CreditWorthinessRequest;
import com.westbank.ws.client.callbackloanapproval._2019._01.CallbackLoanApprovalRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoanFileService {

    protected static Logger LOG = LoggerFactory.getLogger(LoanFileService.class);

    final LoanFileRepository loanFileRepository;

    @Autowired
    public LoanFileService(LoanFileRepository loanFileRepository) {
        this.loanFileRepository = loanFileRepository;
    }

    public void updateLoanFile(CallbackLoanApprovalRequest request) {
        final String loanFileId = request.getLoanFileId();
        final String description = request.getDescription();
        LoanFileStatus status;
        try {
            status = LoanFileStatus.valueOf(request.getStatus());
        } catch (Exception e) {
            status = LoanFileStatus.INITIALIZED;
        }
        final LoanFile loanFile = loanFileRepository.findById(loanFileId).orElse(null);

        if (loanFile != null) {
            LOG.debug("Update the loan file {}", loanFile);
            loanFile.setStatus(status);
            loanFile.setDescription(description);
            loanFileRepository.save(loanFile);
        }
    }

    public boolean checkCreditWorthiness(CreditWorthinessRequest request) {
        // TODO
        int dice = new Random().nextInt(100);
        return dice <= 96;
    }
}
