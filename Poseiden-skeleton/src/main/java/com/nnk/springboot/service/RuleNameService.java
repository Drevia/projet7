package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository repository;

    /**
     *
     * @return all RuleName from the database
     */
    public List<RuleName> findAllRuleName() {
        return repository.findAll();
    }

    /**
     *
     * @param id of the RuleName we want to find
     * @return Optional with RuleName we found if exist or Optional empty
     */
    public Optional<RuleName> findById(Integer id) {
        return repository.findById(id);
    }

    /**
     *
     * @param ruleName we want to save in database
     */
    public void saveRuleName(RuleName ruleName) {
        repository.save(ruleName);
    }

    /**
     *
     * @param newRuleName we send to update
     */
    public void updateRuleName(RuleName newRuleName) {
        repository.save(newRuleName);
    }

    /**
     *
     * @param id of the RuleName we want to delete from the database
     */
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
