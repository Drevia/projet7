package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

    @Mock
    TradeRepository tradeRepository;

    @InjectMocks
    TradeService service;


    @Test
    void findTradeById_OK() {
        when(tradeRepository.findById(any())).thenReturn(Optional.of(new Trade()));
        assertDoesNotThrow(() -> service.findById(1));
    }

    @Test
    void findAllTrade_OK() {
        List<Trade> tradeList = new ArrayList<>();
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        tradeList.add(trade);

        when(tradeRepository.findAll()).thenReturn(tradeList);
        assertDoesNotThrow(() -> service.findAll());
        assertEquals(1, tradeList.size());
    }

    @Test
    void saveTrade_OK() {
        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());
        assertDoesNotThrow(() -> service.saveTrade(new Trade()));
    }

    @Test
    void updateTrade_OK() {
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");

        Trade tradeUpdate = new Trade();
        trade.setAccount("accountUpdate");
        trade.setType("typeUpdate");
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        assertDoesNotThrow(() -> service.updateTrade(1,
                tradeUpdate));
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void deleteTrade_Ok() {
        doNothing().when(tradeRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> service.deleteById(anyInt()));
    }
}
