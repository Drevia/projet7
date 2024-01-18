package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
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

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    @WithMockUser("userTest")
    void shouldCreateTrade() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .with(csrf())
                        .param("account", "account")
                        .param("type", "type")
                        .param("buyQuantity", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnErrorTradeNotValid() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "")
                        .param("buyQuantity", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("userTest")
    void testTradePageOk() throws Exception {

        mockMvc.perform(get("/trade/add").with(csrf())).andDo(print())
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void testTradeListPageOk() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(10d);

        tradeRepository.save(trade);
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);

        mockMvc.perform(get("/trade/list").with(csrf())).andDo(print())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attribute("trades", tradeList))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenDeleteTrade() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(10d);

        tradeRepository.save(trade);

        mockMvc.perform(get("/trade/delete/1").with(csrf())).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenUpdateTrade() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(10d);

        tradeRepository.save(trade);

        mockMvc.perform(post("/trade/update/" + trade.getId()).with(csrf())
                        .param("account", "account2")
                        .param("type", "type")
                        .param("buyQuantity", "1")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

    @Test
    @WithMockUser("userTest")
    void shouldReturnOkWhenGetUpdateTrade() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(10d);

        tradeRepository.save(trade);

        mockMvc.perform(get("/trade/update/1").with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
