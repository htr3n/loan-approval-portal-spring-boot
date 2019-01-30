package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.service.LoanFileService;
import com.westbank.ws.business.creditworthiness._2019._01.CreditWorthiness;
import com.westbank.ws.business.creditworthiness._2019._01.CreditWorthinessRequest;
import com.westbank.ws.business.creditworthiness._2019._01.CreditWorthinessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "CreditWorthiness",
        portName = "CreditWorthinessPort",
        targetNamespace = "urn:com:westbank:ws:business:CreditWorthiness:2019:01",
        endpointInterface = "com.westbank.ws.business.creditworthiness._2019._01.CreditWorthiness")
public class CreditWorthinessPortImpl implements CreditWorthiness {

    private static final Logger LOG = LoggerFactory.getLogger(CreditWorthinessPortImpl.class.getName());

    @Autowired
    private LoanFileService loanFileService;

    public CreditWorthinessResponse check(CreditWorthinessRequest request) {
        LOG.debug("check() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");

        final CreditWorthinessResponse response = new CreditWorthinessResponse();
        response.setCreditWorthiness(loanFileService.checkCreditWorthiness(request));

        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }

}
