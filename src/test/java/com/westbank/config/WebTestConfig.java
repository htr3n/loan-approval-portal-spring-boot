package com.westbank.config;

import com.westbank.repository.AgencyRepository;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.service.CustomerService;
import com.westbank.web.validator.CustomerLoginValidator;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile("web")
@Configuration
public class WebTestConfig {

    @Bean
    @Primary
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }

    @Bean
    @Primary
    public LoanContractRepository loanContractRepository() {
        return Mockito.mock(LoanContractRepository.class);
    }

    @Bean
    @Primary
    public LoanFileRepository loanFileRepository() {
        return Mockito.mock(LoanFileRepository.class);
    }

    @Bean
    @Primary
    public AgencyRepository agencyRepository() {
        return Mockito.mock(AgencyRepository.class);
    }

    @Bean
    @Primary
    public CustomerLoginValidator loginValidator() {
        return new CustomerLoginValidator();
    }

}
