package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.service.LoanFileService;
import com.westbank.ws.client.callbackloanapproval._2019._01.CallbackLoanApproval;
import com.westbank.ws.client.callbackloanapproval._2019._01.CallbackLoanApprovalRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "CallbackLoanApproval",
        portName = "CallbackLoanApprovalPort",
        targetNamespace = "urn:com:westbank:ws:client:CallbackLoanApproval:2019:01",
        endpointInterface = "com.westbank.ws.client.callbackloanapproval._2019._01.CallbackLoanApproval")
public class CallbackLoanApprovalPortImpl implements CallbackLoanApproval {

    private static final Logger LOG = LoggerFactory.getLogger(CallbackLoanApprovalPortImpl.class);

    @Autowired
    private LoanFileService loanFileService;

    public void notify(CallbackLoanApprovalRequest request) {
        LOG.debug("notify() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        loanFileService.updateLoanFile(request);
    }
}
