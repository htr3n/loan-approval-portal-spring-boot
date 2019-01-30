package com.westbank.service;

import com.westbank.config.DataConfig;
import com.westbank.config.HelperConfig;
import com.westbank.entity.Staff;
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
@DataJpaTest
@ActiveProfiles("data")
@Import(StaffService.class)
@ContextHierarchy({@ContextConfiguration(classes = {DataConfig.class, HelperConfig.class})})
public class StaffServiceTest {

    private static final String STAFF_ID = "pbezmudmfn";
    private static final String STAFF_EMAIL = "staff@westbank.com";
    private static final String STAFF_PASSWORD = "hxXzs5hXllL";
    private static final String STAFF_DIFFERENT_PASSWORD = "w0uTtFLT080";

    @Autowired
    private StaffService staffService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void authenticate_shouldYieldNull_forEmptyDatabase() {
        assertThat(staffService.authenticate(STAFF_EMAIL, STAFF_PASSWORD)).isNull();
    }

    @Test
    public void authenticate_shouldYieldNull_forEmptyPassword() {
        final Staff staff = new Staff(STAFF_ID, STAFF_EMAIL, STAFF_PASSWORD);
        testEntityManager.persist(staff);
        assertThat(staffService.authenticate(STAFF_EMAIL, "")).isNull();
    }

    @Test
    public void authenticate_shouldYieldNull_forWrongPassword() {
        final Staff staff = new Staff(STAFF_ID, STAFF_EMAIL, STAFF_PASSWORD);
        testEntityManager.persist(staff);
        assertThat(staffService.authenticate(staff.getEmail(), STAFF_DIFFERENT_PASSWORD)).isNull();
    }

    @Test
    public void authenticate_shouldSucceed_forMatchedAccount() {
        final Staff staff = new Staff(STAFF_ID, STAFF_EMAIL, STAFF_PASSWORD);
        testEntityManager.persist(staff);
        assertThat(staffService.authenticate(staff.getEmail(), staff.getPassword())).isNotNull();
    }

}