package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RuleNameRepository repository;

    @Test
    @WithMockUser("userTest")
    void shouldCreateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                        .param("name", "test")
                        .param("description", "desc")
                        .param("json", "json")
                        .param("template", "temp")
                        .param("sqlStr", "str")
                        .param("sqlPart", "part"))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnErrorRatingNotValid() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                        .param("name", "")
                        .param("description", "")
                        .param("json", "")
                        .param("template", "")
                        .param("sqlStr", "")
                        .param("sqlPart", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingPageOk() throws Exception {

        mockMvc.perform(get("/ruleName/add").with(csrf())).andDo(print())
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingListPageOk() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleName.setTemplate("templ");

        repository.save(ruleName);
        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(ruleName);

        mockMvc.perform(get("/ruleName/list").with(csrf())).andDo(print())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attribute("ruleNames", ruleNameList))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenDeleteRating() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleName.setTemplate("templ");

        repository.save(ruleName);

        mockMvc.perform(get("/ruleName/delete/1").with(csrf())).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenUpdateRating() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleName.setTemplate("templ");

        repository.save(ruleName);

        mockMvc.perform(post("/ruleName/update/" + ruleName.getId()).with(csrf())
                .param("name", "test1")
                .param("description", "desc1")
                .param("json", "json1")
                .param("template", "temp1")
                .param("sqlStr", "str1")
                .param("sqlPart", "part1")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenGetUpdateRating() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleName.setTemplate("templ");

        repository.save(ruleName);

        mockMvc.perform(get("/ruleName/update/1").with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
