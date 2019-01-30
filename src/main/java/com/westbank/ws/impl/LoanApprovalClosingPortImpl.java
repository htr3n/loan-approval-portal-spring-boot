package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.ws.business.loanapprovalclosing._2019._01.LoanApprovalClosing;
import com.westbank.ws.business.loanapprovalclosing._2019._01.LoanApprovalClosingRequest;
import com.westbank.ws.business.loanapprovalclosing._2019._01.LoanApprovalClosingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "LoanApprovalClosing",
        portName = "LoanApprovalClosingPort",
        targetNamespace = "urn:com:westbank:ws:business:LoanApprovalClosing:2019:01",
        endpointInterface = "com.westbank.ws.business.loanapprovalclosing._2019._01.LoanApprovalClosing")
public class LoanApprovalClosingPortImpl implements LoanApprovalClosing {

    private static final Logger LOG = LoggerFactory.getLogger(LoanApprovalClosingPortImpl.class);

    public LoanApprovalClosingResponse close(LoanApprovalClosingRequest request) {
        LOG.debug("close() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        //TODO
        final LoanApprovalClosingResponse response = new LoanApprovalClosingResponse();
        response.setStatus(true);
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }

}
