package com.westbank.repository;

import com.westbank.PortalApplicationTest;
import com.westbank.config.DataConfig;
import com.westbank.config.DataTestConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.Role;
import com.westbank.entity.Staff;
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
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class, DataTestConfig.class, PortalApplicationTest.class})})
public class StaffRepositoryTest {

    private static final String ALICE_ID = "alice";
    private static final String ALICE_EMAIL = "alice@test.com";
    private static final String ALICE_PASSWORD = "coLZAuDeudI";

    private static final String BOB_ID = "bob";

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void save_shouldSucceed_forStaffWithId() {
        final Staff alice = new Staff(ALICE_ID);
        assertThat(staffRepository.save(alice)).isNotNull();
        assertThat(staffRepository.findById(alice.getId())).isPresent();
    }

    @Test
    public void save_shouldSucceed_forStaffWithIdAndRole() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        final Role manager = new Role();
        manager.setId(Role.ID_MANAGER);
        alice.setRole(manager);

        assertThat(staffRepository.save(alice)).isNotNull();
        assertThat(staffRepository.findById(alice.getId())).isPresent();
    }

    @Test
    public void findAllByRole_shouldYieldTwoManagers() {
        final Role role = new Role();
        role.setId(Role.ID_MANAGER);

        assertThat(testEntityManager.persist(role)).isNotNull();

        final Staff alice = new Staff(ALICE_ID);
        alice.setRole(role);

        final Staff bob = new Staff(BOB_ID);
        bob.setRole(role);

        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(staffRepository.findAllByRole(Role.ID_MANAGER))
                .isNotNull()
                .hasSize(1);

        assertThat(testEntityManager.persist(bob)).isNotNull();
        assertThat(staffRepository.findAllByRole(Role.ID_MANAGER))
                .isNotNull()
                .hasSize(2);
    }

    @Test
    public void getCreditBrokers_shouldSucceed() {
        final Role role = new Role();
        role.setId(Role.ID_CREDIT_BROKER);

        assertThat(testEntityManager.persist(role)).isNotNull();

        final Staff alice = new Staff(ALICE_ID);
        alice.setRole(role);

        final Staff bob = new Staff(BOB_ID);
        bob.setRole(role);

        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(staffRepository.getCreditBrokers()).isNotNull().hasSize(1);
        assertThat(testEntityManager.persist(bob)).isNotNull();
        assertThat(staffRepository.getCreditBrokers()).isNotNull().hasSize(2);
    }

    @Test
    public void getManagers_shouldSucceed() {
        final Role role = new Role();
        role.setId(Role.ID_MANAGER);

        assertThat(testEntityManager.persist(role)).isNotNull();

        final Staff alice = new Staff(ALICE_ID);
        alice.setRole(role);

        final Staff bob = new Staff(BOB_ID);
        bob.setRole(role);

        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(staffRepository.getManagers()).isNotNull().hasSize(1);
        assertThat(testEntityManager.persist(bob)).isNotNull();
        assertThat(staffRepository.getManagers()).isNotNull().hasSize(2);
    }

    @Test
    public void getPostProcessingClerks_shouldSucceed() {
        final Role role = new Role();
        role.setId(Role.ID_POST_PROCESSING_CLERK);

        assertThat(testEntityManager.persist(role)).isNotNull();

        final Staff alice = new Staff(ALICE_ID);
        alice.setRole(role);

        final Staff bob = new Staff(BOB_ID);
        bob.setRole(role);

        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(staffRepository.getPostProcessingClerks()).isNotNull().hasSize(1);
        assertThat(testEntityManager.persist(bob)).isNotNull();
        assertThat(staffRepository.getPostProcessingClerks()).isNotNull().hasSize(2);
    }

    @Test
    public void getSupervisors_shouldSucceed() {
        final Role role = new Role();
        role.setId(Role.ID_SUPERVISOR);

        assertThat(testEntityManager.persist(role)).isNotNull();

        final Staff alice = new Staff(ALICE_ID);
        alice.setRole(role);

        final Staff bob = new Staff(BOB_ID);
        bob.setRole(role);

        assertThat(testEntityManager.persist(alice)).isNotNull();
        assertThat(staffRepository.getSupervisors()).isNotNull().hasSize(1);
        assertThat(testEntityManager.persist(bob)).isNotNull();
        assertThat(staffRepository.getSupervisors()).isNotNull().hasSize(2);
    }

    @Test
    public void findByEmail_shouldYieldEmpty_forEmptyDatabase() {
        assertThat(staffRepository.findByEmail(ALICE_EMAIL)).isEmpty();
    }

    @Test
    public void findByEmail_shouldYieldEmpty_forEmptyEmail() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByEmail("")).isEmpty();
    }

    @Test
    public void findByEmail_shouldSucceed_forMatchedEmail() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByEmail(alice.getEmail())).isNotEmpty();
    }

    @Test
    public void findByIdAndPassword_shouldYieldEmpty_forEmptyDatabase() {
        assertThat(staffRepository.findByIdAndPassword(ALICE_ID, ALICE_PASSWORD)).isEmpty();
    }

    @Test
    public void findByIdAndPassword_shouldYieldEmpty_forEmptyIdOrPassword() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        assertThat(staffRepository.findByIdAndPassword("", alice.getPassword())).isEmpty();
        assertThat(staffRepository.findByIdAndPassword(alice.getId(), "")).isEmpty();
        assertThat(staffRepository.findByIdAndPassword("", "")).isEmpty();
    }

    @Test
    public void findByIdAndPassword_shouldSucceed_forMatchedIdAndPassword() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByIdAndPassword(alice.getId(), alice.getPassword())).isNotEmpty();
    }

    @Test
    public void findByIdOrEmail_shouldYieldEmpty_forEmptyDatabase() {
        assertThat(staffRepository.findByIdOrEmail(ALICE_ID, ALICE_EMAIL)).isEmpty();
    }

    @Test
    public void findByIdOrEmail_shouldYieldEmpty_forBothEmptyIdAndEmail() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        assertThat(staffRepository.findByIdOrEmail("", "")).isEmpty();
    }

    @Test
    public void findByIdOrEmail_shouldSucceed_forMatchedIdAndEmptyEmail() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByIdOrEmail(alice.getId(), "")).isNotEmpty();
    }

    @Test
    public void findByIdOrEmail_shouldSucceed_forEmptyIdAndMatchedEmail() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByIdOrEmail("", alice.getEmail())).isNotEmpty();
    }

    @Test
    public void findByIdOrEmail_shouldSucceed_forMatchedIdAndPassword() {
        final Staff alice = new Staff(ALICE_ID, ALICE_EMAIL, ALICE_PASSWORD);
        testEntityManager.persist(alice);
        assertThat(staffRepository.findByIdOrEmail(alice.getId(), alice.getEmail())).isNotEmpty();
    }

}