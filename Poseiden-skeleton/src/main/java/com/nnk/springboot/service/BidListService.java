package com.nnk.springboot.service;

import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidListService {

    @Autowired
    private BidListRepository repository;
}