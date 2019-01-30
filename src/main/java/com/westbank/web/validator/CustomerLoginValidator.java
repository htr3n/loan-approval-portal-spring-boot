package com.westbank.web.validator;

import com.westbank.web.form.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerLoginValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "loginId", "error.required", new String[]{"Email"});
        ValidationUtils.rejectIfEmpty(errors, "loginPassword", "error.required", new String[]{"PIN"});
    }

}
