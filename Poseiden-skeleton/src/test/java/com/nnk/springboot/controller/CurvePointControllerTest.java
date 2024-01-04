package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class CurvePointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    @WithMockUser("userTest")
    void shouldCreateCurvePoint() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("term", "20")
                        .param("value", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnErrorCurvePointNotValid() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("term", "")
                        .param("value", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("userTest")
    void testCurvePointPageOk() throws Exception {

        mockMvc.perform(get("/curvePoint/add").with(csrf())).andDo(print())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingListPageOk() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setValue(2d);
        curvePoint.setTerm(20d);

        curvePointRepository.save(curvePoint);
        List<CurvePoint> curvePoints = new ArrayList<>();
        curvePoints.add(curvePoint);

        mockMvc.perform(get("/curvePoint/list").with(csrf())).andDo(print())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attribute("curvePoints", curvePoints))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenDeleteRating() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setValue(2d);
        curvePoint.setTerm(20d);

        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/delete/1").with(csrf())).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenUpdateRating() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setValue(2d);
        curvePoint.setTerm(20d);

        curvePointRepository.save(curvePoint);

        mockMvc.perform(post("/curvePoint/update/" + curvePoint.getId()).with(csrf())
                        .param("value", "10")
                        .param("term", "2")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenGetUpdateRating() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setValue(2d);
        curvePoint.setTerm(20d);

        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/update/1").with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
