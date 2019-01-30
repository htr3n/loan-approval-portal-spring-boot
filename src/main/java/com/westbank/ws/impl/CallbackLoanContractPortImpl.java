package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.ws.client.callbackloancontract._2019._01.CallbackLoanContract;
import com.westbank.ws.client.callbackloancontract._2019._01.CallbackLoanContractRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "CallbackLoanContract",
        portName = "CallbackLoanContractPort",
        targetNamespace = "urn:com:westbank:ws:client:CallbackLoanContract:2019:01",
        endpointInterface = "com.westbank.ws.client.callbackloancontract._2019._01.CallbackLoanContract")
public class CallbackLoanContractPortImpl implements CallbackLoanContract {

    private static final Logger LOG = LoggerFactory.getLogger(CallbackLoanContractPortImpl.class.getName());

    public void send(CallbackLoanContractRequest request) {
        LOG.debug("send() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        // TODO
        // long borrowerId = request.getBorrowerCustomerId();
        // String contractId = request.getLoanContractId();
    }
}
