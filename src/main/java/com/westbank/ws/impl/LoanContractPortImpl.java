package com.westbank.ws.impl;

import com.westbank.entity.LoanContract;
import com.westbank.helper.JaxbUtil;
import com.westbank.service.LoanContractService;
import com.westbank.ws.business.loancontract._2019._01.LoanContractRequest;
import com.westbank.ws.business.loancontract._2019._01.LoanContractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;

@WebService(
        serviceName = "LoanContract",
        portName = "LoanContractPort",
        targetNamespace = "urn:com:westbank:ws:business:LoanContract:2019:01",
        endpointInterface = "com.westbank.ws.business.loancontract._2019._01.LoanContract")
public class LoanContractPortImpl implements com.westbank.ws.business.loancontract._2019._01.LoanContract {

    private static final Logger LOG = LoggerFactory.getLogger(LoanContractPortImpl.class);

    @Autowired
    private LoanContractService loanContractService;

    public LoanContractResponse create(LoanContractRequest request) {
        LOG.debug("create() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");

        return createResponseFromLoanContract(loanContractService.createAndSaveContract(request));
    }

    private LoanContractResponse createResponseFromLoanContract(LoanContract contract) {
        final LoanContractResponse response = new LoanContractResponse();
        response.setLoanContractId(contract.getId());
        LOG.debug(" Response: {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }
}
