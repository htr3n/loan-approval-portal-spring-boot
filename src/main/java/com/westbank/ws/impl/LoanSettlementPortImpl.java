package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.ws.business.loansettlement._2019._01.LoanSettlement;
import com.westbank.ws.business.loansettlement._2019._01.LoanSettlementRequest;
import com.westbank.ws.business.loansettlement._2019._01.LoanSettlementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "LoanSettlement",
        portName = "LoanSettlementPort",
        targetNamespace = "urn:com:westbank:ws:business:LoanSettlement:2019:01",
        endpointInterface = "com.westbank.ws.business.loansettlement._2019._01.LoanSettlement")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class LoanSettlementPortImpl implements LoanSettlement {

    private static final Logger LOG = LoggerFactory.getLogger(LoanSettlementPortImpl.class);

    public LoanSettlementResponse start(LoanSettlementRequest request) {
        LOG.debug("start() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        // TODO
        final LoanSettlementResponse response = new LoanSettlementResponse();
        response.setStatus(true);
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }

}
