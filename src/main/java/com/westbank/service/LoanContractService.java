package com.westbank.service;

import com.westbank.entity.Agency;
import com.westbank.entity.LoanContract;
import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.helper.JaxbUtil;
import com.westbank.repository.AgencyRepository;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.ws.business.loancontract._2019._01.LoanContractRequest;
import com.westbank.ws.business.loancontract._2019._01.LoanContractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
public class LoanContractService {

    private static Logger LOG = LoggerFactory.getLogger(LoanContractService.class);

    private final LoanContractRepository loanContractRepository;
    private final LoanFileRepository loanFileRepository;
    private final AgencyRepository agencyRepository;

    @Autowired
    public LoanContractService(LoanContractRepository loanContractRepository, LoanFileRepository loanFileRepository, AgencyRepository agencyRepository) {
        this.loanContractRepository = loanContractRepository;
        this.loanFileRepository = loanFileRepository;
        this.agencyRepository = agencyRepository;
    }

    public LoanContract createAndSaveContract(LoanContractRequest request) {
        LOG.debug("Start creating and saving a loan contract from request {}", request);
        LOG.trace(JaxbUtil.toXml(request));

        final LoanContract loanContract = createContractFromRequest(request);
        final LoanFile loanFile = loanContract.getLoanFile();

        LOG.debug("Created and saved a new loan contract");
        LOG.debug("Loan contract {}", loanContract);
        LOG.debug("Loan file {}", loanFile);
        final LoanContract savedContract = loanContractRepository.save(loanContract);
        return savedContract;
    }

    private LoanFile createNewLoanFile() {
        LoanFile loanFile = new LoanFile();
        loanFile.setStatus(LoanFileStatus.INITIALIZED);
        return loanFile;
    }

    private LoanContract createContractFromRequest(LoanContractRequest request) {
        Assert.notNull(request, "Loan contract request must not be null");
        LoanFile loanFile = null;

        if (request.getLoanFileId() != null) {
            loanFile = loanFileRepository.findById(request.getLoanFileId()).orElse(createNewLoanFile());
        }

        if (loanFile == null) {
            loanFile = createNewLoanFile();
            loanFileRepository.save(loanFile);
        }

        final LoanContract contract = new LoanContract();
        contract.setLoanFile(loanFile);
        contract.setSettlementDate(LocalDate.now());
        String agencyCode = request.getAgencyCode();
        final Agency agency = agencyRepository.findById(agencyCode).orElse(new Agency(agencyCode));
        contract.setAgency(agency);
        loanFile.setContract(contract);
        return contract;
    }

    public LoanContractResponse createLoanContractResponse(LoanContract contract) {
        LoanContractResponse response = new LoanContractResponse();
        if (contract != null) {
            response.setLoanContractId(contract.getId());
        }
        return response;
    }
}
