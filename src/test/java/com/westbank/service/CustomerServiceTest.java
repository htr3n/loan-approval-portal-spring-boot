package com.westbank.service;

import com.westbank.CustomerTestConstants;
import com.westbank.config.DataConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.Customer;
import com.westbank.helper.CustomerMapper;
import com.westbank.web.form.LoanForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("data")
@DataJpaTest
@Import(CustomerService.class)
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class})})
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TestEntityManager testEntityManager;

    private Customer alice;
    private Customer bob;
    private LoanForm aliceForm;

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

        aliceForm = new LoanForm();
        aliceForm.setBorrowerFirstName(CustomerTestConstants.ALICE_FIRSTNAME);
        aliceForm.setBorrowerLastName(CustomerTestConstants.ALICE_LASTNAME);
        aliceForm.setBorrowerEmail(CustomerTestConstants.ALICE_EMAIL);
    }

    @Test
    public void authenticate_shouldYieldNull_forEmptyDatabase() {
        assertThat(customerService.authenticate(CustomerTestConstants.ALICE_EMAIL, CustomerTestConstants.ALICE_PIN)).isNull();
    }

    @Test
    public void authenticate_shouldYieldNull_forEmptyPin() {
        testEntityManager.persist(alice);
        assertThat(customerService.authenticate(CustomerTestConstants.ALICE_EMAIL, "")).isNull();
    }

    @Test
    public void authenticate_shouldYieldNull_forWrongPin() {
        testEntityManager.persist(alice);
        assertThat(customerService.authenticate(CustomerTestConstants.ALICE_EMAIL, CustomerTestConstants.BOB_PIN)).isNull();
    }

    @Test
    public void authenticate_shouldYieldNotNull_forMatchedAccount() {
        testEntityManager.persist(alice);
        assertThat(customerService.authenticate(CustomerTestConstants.ALICE_EMAIL, CustomerTestConstants.ALICE_PIN)).isNotNull();
    }

    @Test
    public void updateProfile_shouldYieldCorrectly_forExistingCustomer() {

        Customer customer = new CustomerMapper().from(aliceForm);
        testEntityManager.persist(customer);
        assertThat(customer).isNotNull();

        aliceForm.setBorrowerFirstName(CustomerTestConstants.BOB_FIRSTNAME);
        aliceForm.setBorrowerLastName(CustomerTestConstants.BOB_LASTNAME);
        customerService.updateProfile(customer.getId(), aliceForm);

        Customer updated = testEntityManager.find(Customer.class, customer.getId());
        assertThat(updated).isNotNull();
        assertThat(updated.getFirstName()).isEqualTo(aliceForm.getBorrowerFirstName());
        assertThat(updated.getLastName()).isEqualTo(aliceForm.getBorrowerLastName());
    }
}
