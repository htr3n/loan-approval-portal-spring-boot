package com.westbank.ws.impl;

import com.westbank.entity.EstateType;
import com.westbank.helper.JaxbUtil;
import com.westbank.repository.LoanFileRepository;
import com.westbank.ws.business.loanrisk._2019._01.LoanRisk;
import com.westbank.ws.business.loanrisk._2019._01.LoanRiskRequest;
import com.westbank.ws.business.loanrisk._2019._01.LoanRiskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "LoanRisk",
        portName = "LoanRiskPort",
        targetNamespace = "urn:com:westbank:ws:business:LoanRisk:2019:01",
        endpointInterface = "com.westbank.ws.business.loanrisk._2019._01.LoanRisk")
public class LoanRiskPortImpl implements LoanRisk {

    private static final Logger LOG = LoggerFactory.getLogger(LoanRiskPortImpl.class);

    @Autowired
    private LoanFileRepository loanFileDao;

    public LoanRiskResponse evaluate(LoanRiskRequest request) {
        LOG.debug("evaluate() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");

        final LoanRiskResponse response = new LoanRiskResponse();
        final String loanFileId = request.getLoanFileId();

        loanFileDao.findById(loanFileId).ifPresent(loanFile -> {
            if (EstateType.HOUSE != loanFile.getEstateType()
                    || "Power".equalsIgnoreCase(loanFile.getBorrower().getLastName())) {
                loanFile.setRisk(com.westbank.entity.LoanRisk.LOW);
                response.setRisk(com.westbank.entity.LoanRisk.LOW.name());
                response.setHighRisk(false);
            } else {
                loanFile.setRisk(com.westbank.entity.LoanRisk.HIGH);
                response.setRisk(com.westbank.entity.LoanRisk.HIGH.name());
                response.setHighRisk(true);
            }
            loanFileDao.save(loanFile);
        });
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }

}
