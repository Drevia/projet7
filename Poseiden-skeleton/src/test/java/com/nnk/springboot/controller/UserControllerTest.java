package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = "toto", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void ShouldReturnUserListPage() throws Exception {
        mockMvc.perform(get("/user/list")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails(value = "toto", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void ShouldReturnUserAddPage() throws Exception {
        mockMvc.perform(get("/user/add")
                        .with(csrf()))
                .andDo(print())
                .andExpect(view().name("user/add"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails(value = "toto", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void ShouldReturnOkWhenValidateUser() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("fullname", "tata")
                        .param("username", "tata")
                        .param("password", "Password1!")
                        .param("role", "USER"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "toto", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void ShouldReturnUpdatePageUser() throws Exception {
        //TODO: fix le test. Comment trouver un user valide pour le test
        mockMvc.perform(get("/user/validate/1")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
