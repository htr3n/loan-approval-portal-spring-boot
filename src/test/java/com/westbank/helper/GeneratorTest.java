package com.westbank.helper;

import com.westbank.AppProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import({Generator.class, AppProperties.class})
public class GeneratorTest {

    @Autowired
    private Generator generator;

    @Autowired
    private AppProperties appProperties;

    @Test
    public void newCustomerId() {
        assertThat(generator.newCustomerId())
                .as("Customer ID")
                .isNotNull()
                .hasSize(appProperties.getLength().getCustomeId());
    }

    @Test
    public void newStaffId() {
        assertThat(generator.newStaffId())
                .as("Staff ID")
                .isNotNull()
                .hasSize(appProperties.getLength().getStaffId());
    }

    @Test
    public void newLoanFileId() {
        assertThat(generator.newLoanFileId())
                .as("Loan file ID")
                .isNotNull()
                .hasSize(appProperties.getLength().getLoanfileId());
    }

    @Test
    public void newPin() {
        assertThat(generator.newCustomerPin())
                .as("Customer PIN")
                .isNotNull()
                .hasSize(appProperties.getLength().getCustomerPin());
    }

    @Test
    public void newUsername() {
        assertThat(generator.newUsername())
                .as("Customer username")
                .isNotNull()
                .hasSize(appProperties.getLength().getCustomerUsername());
    }

    @Test
    public void newPassword() {
        final int passwordLength = 20;
        assertThat(generator.newPassword(passwordLength))
                .as("Password")
                .isNotNull()
                .hasSize(passwordLength);
    }

    @Test
    public void newRandomString() {
        final int passwordLength = 20;
        assertThat(generator.newPassword(passwordLength))
                .as("Password")
                .isNotNull()
                .hasSize(passwordLength);
    }
}