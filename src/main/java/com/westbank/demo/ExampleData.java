package com.westbank.demo;

import com.westbank.entity.EstateType;
import com.westbank.entity.MaritalStatus;
import com.westbank.entity.ResidenceType;
import com.westbank.web.form.LoanForm;
import com.westbank.web.form.LoginForm;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ExampleData {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static LoanForm applicationForm(boolean withBorrowerData) {
        final LoanForm form = new LoanForm();
        form.setAgency("VIC002");
        form.setLoanAmount(100000.0);
        form.setLoanReason("Home Mortgage");
        form.setLoanTerm(20);
        form.setTotalPurchasePrice(800000.0);
        form.setPersonalCapitalContribution(700000.0);
        form.setInterestRate(0.02);
        form.setResidenceType(ResidenceType.MAIN_HOUSE);
        form.setEstateType(EstateType.FLAT);
        form.setEstateAddress("123 Green Ave, South Yarra, VIC 3149");
        form.setStartDate(LocalDate.now());
        form.setAccessSensitiveData(true);
        if (withBorrowerData) {
            form.setBorrowerTitle("Miss");
            form.setBorrowerFirstName("Alice");
            form.setBorrowerLastName("White");
            form.setBorrowerCity("Melbourne");
            form.setBorrowerState("Victoria");
            form.setBorrowerEmail("alice@test.com");
            form.setBorrowerMaritalStatus(MaritalStatus.MARRIED);
            form.setBorrowerIncome(180000.0);
            form.setBorrowerLengthOfService(6);
            form.setBorrowerPhone("0431234567");
            form.setBorrowerMobilePhone("0431234567");
            form.setBorrowerStreet("3 Queen Rd");
            form.setBorrowerZipcode("3031");
            form.setBorrowerOccupation("Accountant");
            form.setAccessSensitiveData(true);
            form.setBorrowerDateOfBirth(LocalDate.of(1988, 01, 01));
            form.setHasCoborrower(true);
            form.setCoborrowerTitle("Mr");
            form.setCoborrowerFirstName("Lewis");
            form.setCoborrowerLastName("Schneider");
            form.setAccessSensitiveData(true);
            form.setCoborrowerIncome(200000.0);
            form.setCoborrowerLengthOfService(8);
            form.setCoborrowerOccupation("Project Manager");
            form.setCoborrowerDateOfBirth(LocalDate.of(1982, 01, 01));
            form.setCoborrowerEmail("schneider@test.com");
        }
        return form;
    }

    private static LoginForm loginForm(String loginId, String loginPassword) {
        final LoginForm form = new LoginForm();
        form.setLoginId(loginId);
        form.setLoginPassword(loginPassword);
        return form;
    }

    public static LoginForm alice() {
        return loginForm("alice@test.com", "abcd1234");
    }

    public static LoginForm broker() {
        return loginForm("broker", "abcd1234");
    }

    public static LoginForm clerk() {
        return loginForm("clerk", "abcd1234");
    }

    public static LoginForm supervisor() {
        return loginForm("supervisor", "abcd1234");
    }

    public static LoginForm manager() {
        return loginForm("manager", "abcd1234");
    }

}
