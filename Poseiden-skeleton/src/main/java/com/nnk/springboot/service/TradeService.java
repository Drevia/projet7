package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository repository;

    public List<Trade> findAll() {
        return repository.findAll();
    }

    public Optional<Trade> findById(Integer id){
        return repository.findById(id);
    }

    public void saveTrade(Trade trade){
        repository.save(trade);
    }

    public void updateTrade(Integer id, Trade newTrade){
        Trade trade = repository.findById(id).orElseThrow();
        trade.setAccount(newTrade.getAccount());
        trade.setBuyQuantity(newTrade.getBuyQuantity());
        trade.setType(newTrade.getType());

        repository.save(trade);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
