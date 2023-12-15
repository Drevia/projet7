package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.TradeRepository;
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
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    @WithMockUser("userTest")
    void shouldCreateBid() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnErrorRatingNotValid() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingPageOk() throws Exception {

        mockMvc.perform(get("/bidList/add").with(csrf())).andDo(print())
                .andExpect(view().name("bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void testRatingListPageOk() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(10d);

        bidListRepository.save(bidList);
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(bidList);

        mockMvc.perform(get("/bidList/list").with(csrf())).andDo(print())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attribute("bidLists", bidLists))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenDeleteRating() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(10d);

        bidListRepository.save(bidList);

        mockMvc.perform(get("/bidList/delete/1").with(csrf())).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenUpdateRating() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(10d);

        bidListRepository.save(bidList);

        mockMvc.perform(post("/bidList/update/" + bidList.getId()).with(csrf())
                        .param("account", "account2")
                        .param("type", "type")
                        .param("bidQuantity", "1")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenGetUpdateRating() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(10d);

        bidListRepository.save(bidList);

        mockMvc.perform(get("/bidList/update/1").with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
