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

    /**
     *
     * @return list of all bidList from the database
     */
    public List<BidList> findAll(){
        return repository.findAll();
    }

    /**
     *
     * @param id of the Bidlist we want to find
     * @return Optional with bidList we found if exist or Optional empty
     */
    public Optional<BidList> findById(Integer id){
        return repository.findById(id);
    }

    /**
     *
     * @param bidList we want to save in database
     */
    public void saveBidList(BidList bidList) {
        repository.save(bidList);
    }

    /**
     *
     * @param newBidList we send to update
     */
    public void updateBidList(BidList newBidList) {
        repository.save(newBidList);
    }

    /**
     *
     * @param id of the bidlist we want to delete from the database
     */
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
