package com.westbank.repository;

import com.westbank.config.DataConfig;
import com.westbank.config.DataTestConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.Agency;
import com.westbank.entity.LoanContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("data")
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class, DataTestConfig.class})})
public class LoanContractRepositoryTest {

    private static final String AGENCY_CODE = "VIC001";
    @Autowired
    private LoanContractRepository loanContractRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void save_shouldSucceed() {
        LoanContract contract = new LoanContract();
        //contract.setAgency(new Agency("VIC001"));
        assertThat(loanContractRepository.save(contract)).isNotNull();
    }

}
