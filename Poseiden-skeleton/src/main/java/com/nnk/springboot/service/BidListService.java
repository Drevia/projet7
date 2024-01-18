package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository repository;

    public List<BidList> findAll(){
        return repository.findAll();
    }

    public Optional<BidList> findById(Integer id){
        return repository.findById(id);
    }

    public void saveBidList(BidList bidList) {
        repository.save(bidList);
    }

    public void updateBidList(Integer id, BidList newBidList) {
        BidList bidList = repository.findById(id).orElseThrow();
        bidList.setAccount(newBidList.getAccount());
        bidList.setBidQuantity(newBidList.getBidQuantity());
        bidList.setType(newBidList.getType());

        repository.save(bidList);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
