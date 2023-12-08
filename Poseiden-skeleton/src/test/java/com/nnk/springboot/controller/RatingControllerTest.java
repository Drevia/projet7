package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
/*@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
})*/
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    @WithMockUser("userTest")
    void shouldCreateRating() throws Exception {
        mockMvc.perform(post("/rating/validate")
                .with(csrf())
                .param("moodysRating", "1")
                .param("sandPRating", "1")
                .param("fitchRating", "1")
                .param("orderNumber", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnErrorRatingNotValid() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .with(csrf())
                        .param("moodysRating", "")
                        .param("sandPRating", "")
                        .param("fitchRating", "")
                        .param("orderNumber", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingPageOk() throws Exception {

        mockMvc.perform(get("/rating/add").with(csrf())).andDo(print())
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingListPageOk() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("1");
        rating.setOrderNumber(1);
        rating.setSandPRating("1");
        rating.setFitchRating("1");

        ratingRepository.save(rating);
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);

        mockMvc.perform(get("/rating/list").with(csrf())).andDo(print())
                .andExpect(view().name("rating/list"))
                /*.andExpect(model().attribute("ratings.size", 1))*/
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenDeleteRating() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("1");
        rating.setOrderNumber(1);
        rating.setSandPRating("1");
        rating.setFitchRating("1");

        ratingRepository.save(rating);

        mockMvc.perform(get("/rating/delete/1").with(csrf())).andDo(print())
                /*.andExpect(model().attribute("ratings.size", 1))*/
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenUpdateRating() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("1");
        rating.setOrderNumber(1);
        rating.setSandPRating("1");
        rating.setFitchRating("1");

        ratingRepository.save(rating);

        mockMvc.perform(post("/rating/update/1").with(csrf())
                .param("moodysRating", "1")
                .param("sandPRating", "2")
                .param("fitchRating", "3")
                .param("orderNumber", "4")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenGetUpdateRating() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("1");
        rating.setOrderNumber(1);
        rating.setSandPRating("1");
        rating.setFitchRating("1");

        ratingRepository.save(rating);

        mockMvc.perform(get("/rating/update/1").with(csrf())).andDo(print())
                .andExpect(status().is3xxRedirection());

    }
}
