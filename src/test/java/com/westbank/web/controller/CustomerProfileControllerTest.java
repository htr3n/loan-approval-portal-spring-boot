package com.westbank.web.controller;

import com.westbank.config.*;
import com.westbank.web.controller.CustomerProfileController;
import com.westbank.web.validator.CustomerProfileValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("web")
@RunWith(SpringRunner.class)
@WebMvcTest({CustomerProfileController.class, CustomerProfileValidator.class})
@ContextHierarchy({@ContextConfiguration(classes = {
        DataConfig.class,
        HelperConfig.class,
        RemoteConfig.class,
        ServiceConfig.class,
        ServiceClientConfig.class,
        WebTestConfig.class,
})})
public class CustomerProfileControllerTest {

    @Test
    public void prepare() {
    }

    @Test
    public void updateProfile() {
    }
}