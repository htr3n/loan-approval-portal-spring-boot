package com.westbank.config;

import com.westbank.ws.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    BankInformationPortImpl bankInformationImpl() {
        return new BankInformationPortImpl();
    }

    @Bean
    BankPrivilegePortImpl bankPrivilegeImpl() {
        return new BankPrivilegePortImpl();
    }

    @Bean
    CallbackLoanApprovalPortImpl callbackLoanApprovalImpl() {
        return new CallbackLoanApprovalPortImpl();
    }

    @Bean
    CallbackLoanContractPortImpl callbackLoanContractImpl() {
        return new CallbackLoanContractPortImpl();
    }

    @Bean
    CreditWorthinessPortImpl creditWorthinessImpl() {
        return new CreditWorthinessPortImpl();
    }

    @Bean
    LoanApprovalClosingPortImpl loanApprovalClosingImpl() {
        return new LoanApprovalClosingPortImpl();
    }

    @Bean
    LoanContractPortImpl loanContractImpl() {
        return new LoanContractPortImpl();
    }

    @Bean
    LoanFilePortImpl loanFileImpl() {
        return new LoanFilePortImpl();
    }

    @Bean
    LoanRiskPortImpl loanRiskImpl() {
        return new LoanRiskPortImpl();
    }

    @Bean
    LoanSettlementPortImpl loanSettlementImpl() {
        return new LoanSettlementPortImpl();
    }

    @Bean
    TaskDispatchPortImpl taskDispatchImpl() {
        return new TaskDispatchPortImpl();
    }

    @Bean
    TestImpl testImpl() {
        return new TestImpl();
    }

}
