package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;
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
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @Mock
    BidListRepository bidListRepository;

    @InjectMocks
    BidListService service;


    @Test
    void findBidById_OK() {
        when(bidListRepository.findById(any())).thenReturn(Optional.of(new BidList()));
        assertDoesNotThrow(() -> service.findById(1));
    }

    @Test
    void findAllBid_OK() {
        List<BidList> bidLists = new ArrayList<>();
        BidList bidList = new BidList("account", "type", 1);
        bidLists.add(bidList);

        when(bidListRepository.findAll()).thenReturn(bidLists);
        assertDoesNotThrow(() -> service.findAll());
        assertEquals(1, bidLists.size());
    }

    @Test
    void saveTrade_OK() {
        when(bidListRepository.save(any(BidList.class))).thenReturn(new BidList());
        assertDoesNotThrow(() -> service.saveBidList(new BidList()));
    }

    @Test
    void updateTrade_OK() {
        BidList bidList = new BidList("account", "type", 1);
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));

        assertDoesNotThrow(() -> service.updateBidList(1,
                 new BidList("account", "type", 1)));
        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    void deleteTrade_Ok() {
        doNothing().when(bidListRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> service.deleteById(anyInt()));
    }
}
