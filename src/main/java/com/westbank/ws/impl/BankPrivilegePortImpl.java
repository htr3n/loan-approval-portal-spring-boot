package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.ws.business.bankprivilege._2019._01.BankPrivilege;
import com.westbank.ws.business.bankprivilege._2019._01.BankPrivilegeRequest;
import com.westbank.ws.business.bankprivilege._2019._01.BankPrivilegeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Random;

@WebService(
        serviceName = "BankPrivilege",
        portName = "BankPrivilegePort",
        targetNamespace = "urn:com:westbank:ws:business:BankPrivilege:2019:01",
        endpointInterface = "com.westbank.ws.business.bankprivilege._2019._01.BankPrivilege")
public class BankPrivilegePortImpl implements BankPrivilege {

    protected static final Logger LOG = LoggerFactory.getLogger(BankPrivilegePortImpl.class);

    public BankPrivilegeResponse verify(BankPrivilegeRequest request) {
        LOG.debug("verify() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");

        final BankPrivilegeResponse response = new BankPrivilegeResponse();
        final String lastName = request.getBorrowerLastName();
        // TODO
        int dice = new Random().nextInt(100);
        if ("Power".equalsIgnoreCase(lastName) || dice <= 90) {
            response.setRegistered(true);
            response.setNumberOfIncidents(0);
            response.setNumberOfBanks(2);
        } else {
            response.setRegistered(true);
            response.setNumberOfIncidents(10 + new Random().nextInt(10));
            response.setNumberOfBanks(1 + new Random().nextInt(10));
        }
        response.setDescription("Bank privilege checking");
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }
}
