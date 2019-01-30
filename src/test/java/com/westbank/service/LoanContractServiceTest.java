package com.westbank.service;

import com.westbank.CustomerTestConstants;
import com.westbank.config.DataConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.*;
import com.westbank.helper.Generator;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.ws.business.loancontract._2019._01.LoanContractRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("data")
@Import(LoanContractService.class)
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class})})
public class LoanContractServiceTest {

    private static final String AGENCY_CODE = "VIC001";
    @Autowired
    private LoanContractService loanContractService;

    @Autowired
    private LoanFileRepository loanFileRepository;

    @Autowired
    private LoanContractRepository loanContractRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private Generator generator;

    private Customer alice;
    private Customer bob;
    private LoanContractRequest request;
    private LoanFile aliceLoanFile;

    @Before
    public void setUp() {
        alice = new Customer();
        alice.setFirstName(CustomerTestConstants.ALICE_FIRSTNAME);
        alice.setLastName(CustomerTestConstants.ALICE_LASTNAME);
        alice.setPassword(CustomerTestConstants.ALICE_PIN);
        alice.setEmail(CustomerTestConstants.ALICE_EMAIL);

        bob = new Customer();
        bob.setFirstName(CustomerTestConstants.BOB_FIRSTNAME);
        bob.setLastName(CustomerTestConstants.BOB_LASTNAME);
        bob.setPassword(CustomerTestConstants.BOB_PIN);
        bob.setEmail(CustomerTestConstants.BOB_EMAIL);

        // loan file
        aliceLoanFile = new LoanFile();
        aliceLoanFile.setLoanFileId(generator.newLoanFileId());
        aliceLoanFile.setBorrower(alice);
        aliceLoanFile.setLoanReason("House buying");
        aliceLoanFile.setLoanAmount(Long.valueOf(new Random().nextLong()).doubleValue());
        aliceLoanFile.setLoanTerm(new Random().nextInt());
        aliceLoanFile.setInterestRate(Long.valueOf(new Random().nextLong()).doubleValue());
        aliceLoanFile.setResidenceType(ResidenceType.SECOND_HOUSE);
        aliceLoanFile.setEstateType(EstateType.HOUSE);
        aliceLoanFile.setEstateLocation("123 King St");
        aliceLoanFile.setTotalPurchasePrice(Long.valueOf(new Random().nextLong()).doubleValue());

        // contract request
        request = new LoanContractRequest();
        request.setAgencyCode(AGENCY_CODE);
    }

    @Test(expected = DataAccessException.class)
    public void createAndSaveContract_shouldThrowException_forNewRequestWithoutBorrower() {
        assertThat(loanContractService.createAndSaveContract(request)).isNotNull();
        assertThat(loanFileRepository.findAll()).hasSize(1);
        assertThat(loanContractRepository.findAll()).hasSize(1);
    }

    @Test
    public void createAndSaveContract_shouldSucceed_forBorrower() {
        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(testEntityManager.persist(aliceLoanFile)).isNotNull();

        request.setLoanFileId(aliceLoanFile.getLoanFileId());
        final LoanContract savedContract = loanContractService.createAndSaveContract(request);

        assertThat(savedContract).isNotNull();
        assertThat(loanFileRepository.findAll()).hasSize(1);
        assertThat(loanContractRepository.findAll()).hasSize(1);
    }

    @Test
    public void createAndSaveContract_shouldSucceed_forCoBorrower() {
        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(testEntityManager.persist(bob)).isNotNull();

        aliceLoanFile.setCoBorrower(bob);
        assertThat(testEntityManager.persist(aliceLoanFile)).isNotNull();

        request.setLoanFileId(aliceLoanFile.getLoanFileId());
        final LoanContract savedContract = loanContractService.createAndSaveContract(request);

        assertThat(savedContract).isNotNull();
        assertThat(loanFileRepository.findAll()).hasSize(1);
        assertThat(loanContractRepository.findAll()).hasSize(1);
    }

}
