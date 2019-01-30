package com.westbank.web.controller;

import com.westbank.config.WebTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("web")
@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {WebTestConfig.class, CustomerLogoutController.class})
public class CustomerLogoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void logout() throws Exception {
        HttpSession session = mockMvc.perform(get(CustomerLogoutController.PATH))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(view().name(CustomerLogoutController.REDIRECT_LOGIN_VIEW))
                .andExpect(redirectedUrl(CustomerLogoutController.LOGIN_VIEW))
                .andReturn()
                .getRequest()
                .getSession();
        assertThat(session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION)).isNull();
        assertThat(session.getAttribute(CustomerSession.TITLE)).isNull();
        assertThat(session.getAttribute(CustomerSession.FIRSTNAME)).isNull();
        assertThat(session.getAttribute(CustomerSession.LASTNAME)).isNull();
        assertThat(session.getAttribute(CustomerSession.EMAIL)).isNull();
    }
}
