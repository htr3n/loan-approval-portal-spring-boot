package com.westbank.config;

import com.westbank.ws.business.bankinformation._2019._01.BankInformation;
import com.westbank.ws.business.bankprivilege._2019._01.BankPrivilege;
import com.westbank.ws.business.creditworthiness._2019._01.CreditWorthiness;
import com.westbank.ws.business.loanapprovalclosing._2019._01.LoanApprovalClosing;
import com.westbank.ws.business.loancontract._2019._01.LoanContract;
import com.westbank.ws.business.loanfile._2019._01.LoanFile;
import com.westbank.ws.business.loanrisk._2019._01.LoanRisk;
import com.westbank.ws.business.loansettlement._2019._01.LoanSettlement;
import com.westbank.ws.business.taskdispatch._2019._01.TaskDispatch;
import com.westbank.ws.client.callbackloanapproval._2019._01.CallbackLoanApproval;
import com.westbank.ws.client.callbackloancontract._2019._01.CallbackLoanContract;
import com.westbank.ws.impl.*;
import com.westbank.ws.test._2019._01.Test;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    private final Bus bus;

    @Autowired
    private BankInformationPortImpl bankInformationImpl;
    @Autowired
    private BankPrivilegePortImpl bankPrivilegeImpl;
    @Autowired
    private CallbackLoanApprovalPortImpl callbackLoanApprovalImpl;
    @Autowired
    private CallbackLoanContractPortImpl callbackLoanContractImpl;
    @Autowired
    private CreditWorthinessPortImpl creditWorthinessImpl;
    @Autowired
    private LoanApprovalClosingPortImpl loanApprovalClosingImpl;
    @Autowired
    private LoanContractPortImpl loanContractImpl;
    @Autowired
    private LoanFilePortImpl loanFileImpl;
    @Autowired
    private LoanRiskPortImpl loanRiskImpl;
    @Autowired
    private LoanSettlementPortImpl loanSettlementImpl;
    @Autowired
    private TaskDispatchPortImpl taskDispatchImpl;
    @Autowired
    private TestImpl testImpl;

    @Autowired
    public CxfConfig(Bus bus) {
        this.bus = bus;
    }

    @Bean
    Endpoint bankInformation() {
        EndpointImpl endpoint = new EndpointImpl(bus, bankInformationImpl);
        endpoint.publish("/" + BankInformation.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint bankPrivilege() {
        EndpointImpl endpoint = new EndpointImpl(bus, bankPrivilegeImpl);
        endpoint.publish("/" + BankPrivilege.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint callbackLoanApproval() {
        EndpointImpl endpoint = new EndpointImpl(bus, callbackLoanApprovalImpl);
        endpoint.publish("/" + CallbackLoanApproval.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint callbackLoanContract() {
        EndpointImpl endpoint = new EndpointImpl(bus, callbackLoanContractImpl);
        endpoint.publish("/" + CallbackLoanContract.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint creditWorthiness() {
        EndpointImpl endpoint = new EndpointImpl(bus, creditWorthinessImpl);
        endpoint.publish("/" + CreditWorthiness.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint loanApprovalClosing() {
        EndpointImpl endpoint = new EndpointImpl(bus, loanApprovalClosingImpl);
        endpoint.publish("/" + LoanApprovalClosing.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint loanContract() {
        EndpointImpl endpoint = new EndpointImpl(bus, loanContractImpl);
        endpoint.publish("/" + LoanContract.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint loanFile() {
        EndpointImpl endpoint = new EndpointImpl(bus, loanFileImpl);
        endpoint.publish("/" + LoanFile.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint loanRisk() {
        EndpointImpl endpoint = new EndpointImpl(bus, loanRiskImpl);
        endpoint.publish("/" + LoanRisk.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint loanSettlement() {
        EndpointImpl endpoint = new EndpointImpl(bus, loanSettlementImpl);
        endpoint.publish("/" + LoanSettlement.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint taskDispatch() {
        EndpointImpl endpoint = new EndpointImpl(bus, taskDispatchImpl);
        endpoint.publish("/" + TaskDispatch.class.getSimpleName());
        return endpoint;
    }

    @Bean
    Endpoint test() {
        EndpointImpl endpoint = new EndpointImpl(bus, testImpl);
        endpoint.publish("/" + Test.class.getSimpleName());
        return endpoint;
    }

}
