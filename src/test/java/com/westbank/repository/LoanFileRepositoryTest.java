package com.westbank.repository;

import com.westbank.CustomerTestConstants;
import com.westbank.config.DataConfig;
import com.westbank.config.DataTestConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.Customer;
import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.helper.Generator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("data")
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class, DataTestConfig.class})})
public class LoanFileRepositoryTest {

    @Autowired
    LoanFileRepository loanFileRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private Generator generator;

    private Customer createCustomer() {
        Customer alice = new Customer();
        alice.setFirstName(CustomerTestConstants.ALICE_FIRSTNAME);
        alice.setLastName(CustomerTestConstants.ALICE_LASTNAME);
        alice.setPassword(CustomerTestConstants.ALICE_PIN);
        alice.setUsername(generator.newUsername());
        alice.setEmail(CustomerTestConstants.ALICE_EMAIL);
        return alice;
    }

    private LoanFile createLoanFile(Customer borrower) {
        final LoanFile loanFile = new LoanFile();
        loanFile.setLoanFileId(generator.newLoanFileId());
        loanFile.setBorrower(borrower);
        return loanFile;
    }

    @Test
    public void findAllByBorrowerId_shouldYieldEmptyList_forEmptyDatabase() {
        List<LoanFile> noLoanFiles = loanFileRepository.findAllByBorrowerId(new Random().nextLong());
        assertThat(noLoanFiles).isNullOrEmpty();
    }

    @Test
    public void findAllByBorrowerId_shouldYieldCorrectListOfTwoLoanFiles() {
        final Customer alice = createCustomer();
        testEntityManager.persist(alice);

        final LoanFile aliceLoanFile1 = createLoanFile(alice);
        testEntityManager.persist(aliceLoanFile1);

        final LoanFile aliceLoanFile2 = createLoanFile(alice);
        testEntityManager.persist(aliceLoanFile2);

        final List<LoanFile> foundLoanFiles = loanFileRepository.findAllByBorrowerId(alice.getId());
        assertThat(foundLoanFiles)
                .isNotNull()
                .hasSize(CustomerTestConstants.TWO_CUSTOMERS)
                .containsOnly(aliceLoanFile1, aliceLoanFile2);
    }

    @Test
    public void findGrantedLoanFileByCustomer_shouldYieldEmptyList_forEmptyDatabase() {
        final List<LoanFile> noLoanFiles = loanFileRepository.findGrantedLoanFileByCustomer(new Random().nextLong());
        assertThat(noLoanFiles).isNullOrEmpty();
    }

    @Test
    public void findGrantedLoanFileByCustomer_shouldYieldCorrectListOfGrantedLoanFiles() {
        final Customer alice = createCustomer();
        testEntityManager.persist(alice);

        final LoanFile aliceAcceptedLoanFile = createLoanFile(alice);
        aliceAcceptedLoanFile.setStatus(LoanFileStatus.ACCEPTED);
        testEntityManager.persist(aliceAcceptedLoanFile);

        final LoanFile aliceNormalLoanFile = createLoanFile(alice);
        testEntityManager.persist(aliceNormalLoanFile);

        final List<LoanFile> grantedLoanFiles = loanFileRepository.findGrantedLoanFileByCustomer(alice.getId());
        assertThat(grantedLoanFiles)
                .isNotNull()
                .hasSize(1)
                .containsOnly(aliceAcceptedLoanFile);
    }

    @Test
    public void findAllWithFetch_shouldYieldEmptyList_forEmptyDatabase() {
        assertThat(loanFileRepository.findAllWithFetch()).isNullOrEmpty();
    }

    @Test
    public void findAllWithFetch_shouldSucceed_forNonemptyDatabase() {
        final Customer alice = createCustomer();
        testEntityManager.persist(alice);

        final LoanFile aliceLoanFile1 = createLoanFile(alice);
        testEntityManager.persist(aliceLoanFile1);

        final LoanFile aliceLoanFile2 = createLoanFile(alice);
        testEntityManager.persist(aliceLoanFile2);

        final List<LoanFile> loanFiles = loanFileRepository.findAllWithFetch();
        assertThat(loanFiles)
                .isNotNull()
                .hasSize(2)
                .containsOnly(aliceLoanFile1, aliceLoanFile2);
    }
}