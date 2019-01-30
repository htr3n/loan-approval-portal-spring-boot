package com.westbank.web.controller;

import com.westbank.config.WebTestConfig;
import com.westbank.entity.Customer;
import com.westbank.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("web")
@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {WebTestConfig.class, CustomerLoginController.class})
public class CustomerLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerService customerServiceMock;

    @Test
    public void get_shouldSucceed() throws Exception {
        mockMvc.perform(get(CustomerLoginController.PATH))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists(CustomerLoginController.LOGIN_FORM_ATTRIBUTE))
                .andExpect(view().name(CustomerLoginController.LOGIN_VIEW))
                .andExpect(forwardedUrl(WebConstants.VIEW_PREFIX + CustomerLoginController.LOGIN_VIEW + WebConstants.VIEW_SUFFIX))
        ;
    }

    @Test
    public void post_shouldYieldValidationErrors_forEmptyFormData() throws Exception {
        mockMvc.perform(postForm("", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(CustomerLoginController.LOGIN_VIEW))
                .andExpect(forwardedUrl(WebConstants.VIEW_PREFIX + CustomerLoginController.LOGIN_VIEW + WebConstants.VIEW_SUFFIX))
                .andExpect(model().attributeHasFieldErrors(CustomerLoginController.LOGIN_FORM_ATTRIBUTE, "loginId"))
                .andExpect(model().attributeHasFieldErrors(CustomerLoginController.LOGIN_FORM_ATTRIBUTE, "loginPassword"));
    }

    @Test
    public void post_shouldSucceed_forValidFormData() throws Exception {
        final String loginId = "customer@westbank.com";
        final String loginPassword = "pF#UiYe}0r";
        final Customer customer = new Customer();
        customer.setId(1L);

        Mockito.when(customerServiceMock.authenticate(loginId, loginPassword)).thenReturn(customer);

        HttpSession session = mockMvc.perform(postForm(loginId, loginPassword))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(CustomerLoginController.REDIRECT_PORTAL_VIEW))
                .andExpect(redirectedUrl(CustomerLoginController.PORTAL_VIEW))
                .andReturn()
                .getRequest()
                .getSession();

        assertThat(session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION)).isNotNull();
    }

    private MockHttpServletRequestBuilder postForm(String loginId, String loginPassword) {
        return post(CustomerLoginController.PATH)
                .param("loginId", loginId)
                .param("loginPassword", loginPassword);
    }
}
