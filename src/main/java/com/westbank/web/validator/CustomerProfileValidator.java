package com.westbank.web.validator;

import com.westbank.web.form.LoanForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerProfileValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return LoanForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final LoanForm form = (LoanForm) target;

        // Validating customer information
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerTitle", "error.required", new String[] { "Title " });
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerFirstName", "error.required", new String[]{"First name"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerLastName", "error.required", new String[]{"Last name"});

        // validating contract address
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerStreet", "error.required", new String[]{"Street"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerCity", "error.required", new String[]{"City"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerZipcode", "error.required", new String[]{"Zipcode"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerPhone", "error.required", new String[]{"Phone"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerEmail", "error.required", new String[]{"Email"});

        // validating income
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerOccupation", "error.required", new String[]{"Occupation"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerIncome", "error.required", new String[]{"Income"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "borrowerLengthOfService", "error.required", new String[]{"Length of service"});

        String pinFirst = form.getNewPin();
        String pinSecond = form.getNewPinAgain();
        if (pinFirst != null && !pinFirst.isEmpty() && !pinFirst.equals(pinSecond)) {
            errors.rejectValue("newPin", "profile.newpin.not.matched", new String[]{"Password"}, null);
        }

    }
}
