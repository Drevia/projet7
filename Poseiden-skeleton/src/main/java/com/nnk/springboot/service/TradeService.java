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

    /**
     *
     * @return all Trade from the database
     */
    public List<Trade> findAll() {
        return repository.findAll();
    }

    /**
     *
     * @param id of the Trade we want to find
     * @return Optional with Trade we found if exist or Optional empty
     */
    public Optional<Trade> findById(Integer id){
        return repository.findById(id);
    }

    /**
     *
     * @param trade we want to save in database
     */
    public void saveTrade(Trade trade){
        repository.save(trade);
    }

    /**
     *
     * @param newTrade we send to update
     */
    public void updateTrade(Trade newTrade){
        repository.save(newTrade);
    }

    /**
     *
     * @param id of the Trade we want to delete from the database
     */
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
