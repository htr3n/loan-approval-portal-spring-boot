package com.westbank.config;

import com.westbank.ws.ServiceFactory;
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
import com.westbank.ws.process.loanapproval._2019._01.LoanApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ServiceClientConfig {

    private static final String ENDPOINT_SUFFIX = "?wsdl";

    private final Environment env;

    private final String endpointBase;
    private final String endpointProcess;

    @Autowired
    public ServiceClientConfig(Environment env) {
        this.env = env;
        this.endpointBase = env.getProperty("com.westbank.endpoint.base") + "/";
        this.endpointProcess = env.getProperty("com.westbank.endpoint.process");
    }

    @Bean("processClient")
    LoanApproval processClient() {
        return new ServiceFactory<LoanApproval>().create(LoanApproval.class, endpointProcess);
    }

    @Bean("bankInformationClient")
    BankInformation bankInformationClient() {
        return new ServiceFactory<BankInformation>().create(BankInformation.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("bankPrivilegeClient")
    BankPrivilege bankPrivilegeClient() {
        return new ServiceFactory<BankPrivilege>().create(BankPrivilege.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("creditWorthinessClient")
    CreditWorthiness creditWorthinessClient() {
        return new ServiceFactory<CreditWorthiness>().create(CreditWorthiness.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("loanApprovalClosingClient")
    LoanApprovalClosing loanApprovalClosingClient() {
        return new ServiceFactory<LoanApprovalClosing>().create(LoanApprovalClosing.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("loanContractClient")
    LoanContract loanContractClient() {
        return new ServiceFactory<LoanContract>().create(LoanContract.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("loanFileClient")
    LoanFile loanFileClient() {
        return new ServiceFactory<LoanFile>().create(LoanFile.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("loanRiskClient")
    LoanRisk loanRiskClient() {
        return new ServiceFactory<LoanRisk>().create(LoanRisk.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("loanSettlementClient")
    LoanSettlement loanSettlementClient() {
        return new ServiceFactory<LoanSettlement>().create(LoanSettlement.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("taskDispatchClient")
    TaskDispatch taskDispatchClient() {
        return new ServiceFactory<TaskDispatch>().create(TaskDispatch.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("callbackLoanApprovalClient")
    CallbackLoanApproval callbackLoanApprovalClient() {
        return new ServiceFactory<CallbackLoanApproval>().create(CallbackLoanApproval.class, endpointBase, ENDPOINT_SUFFIX);
    }

    @Bean("callbackLoanContractClient")
    CallbackLoanContract callbackLoanContractClient() {
        return new ServiceFactory<CallbackLoanContract>().create(CallbackLoanContract.class, endpointBase, ENDPOINT_SUFFIX);
    }
}
