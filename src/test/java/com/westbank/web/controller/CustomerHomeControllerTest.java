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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles({"web"})
@WebMvcTest
@ContextConfiguration(classes = {WebTestConfig.class, CustomerHomeController.class})
public class CustomerHomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void index() throws Exception {
        mockMvc.perform(get(CustomerHomeController.PATH))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name(CustomerHomeController.INDEX_VIEW))
                .andExpect(forwardedUrl(WebConstants.VIEW_PREFIX + CustomerHomeController.INDEX_VIEW + WebConstants.VIEW_SUFFIX))
        ;
    }
}
