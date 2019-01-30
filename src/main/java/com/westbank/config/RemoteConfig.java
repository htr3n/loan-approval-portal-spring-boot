package com.westbank.config;

import com.westbank.ws.LoanApprovalProcessProxy;
import com.westbank.ws.process.loanapproval._2019._01.LoanApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RemoteConfig {

    @Bean
    LoanApprovalProcessProxy processProxy() {
        return new LoanApprovalProcessProxy();
    }
}
