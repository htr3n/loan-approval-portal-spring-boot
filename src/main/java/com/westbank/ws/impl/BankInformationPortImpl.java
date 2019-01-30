package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.ws.business.bankinformation._2019._01.BankInformation;
import com.westbank.ws.business.bankinformation._2019._01.BankInformationRequest;
import com.westbank.ws.business.bankinformation._2019._01.BankInformationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "BankInformation",
        portName = "BankInformationPort",
        targetNamespace = "urn:com:westbank:ws:business:BankInformation:2019:01",
        endpointInterface = "com.westbank.ws.business.bankinformation._2019._01.BankInformation")
public class BankInformationPortImpl implements BankInformation {

    private static final Logger LOG = LoggerFactory.getLogger(BankInformationPortImpl.class);

    /**
     * Calculates the monthly payment according to the amortization formulas at
     * <p>
     * http://www.vertex42.com/ExcelArticles/amortization-calculation.html
     * <p>
     * monthlyPayment = loanAmount * r * (1 + r) ^ n / ( ( 1 + r )^n - 1) =
     * loanAmount * r / (1 - 1/ (1 + r)^n )
     * <p>
     * where: monthlyPayment = payment amount per month, r = interest rate per period,
     * n = loan terms (by years convert to the number of months)
     */
    public BankInformationResponse retrieve(BankInformationRequest request) {
        LOG.debug("retrieve() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        // loan term = loanTerm (years) * 12;
        int loanTerm = request.getLoanTerm() * 12;
        // monthlyInterestRate = annualInterestRate / 12
        double monthlyInterestRate = request.getInterestRate() / 12.0;
        // total loan amount
        double loanAmount = request.getLoanAmount();
        final BankInformationResponse response = new BankInformationResponse();
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1.0 / Math.pow(monthlyInterestRate + 1, loanTerm));
        response.setMonthlyPayment(monthlyPayment);
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }
}
