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

    public List<RuleName> findAllRuleName() {
        return repository.findAll();
    }

    public Optional<RuleName> findById(Integer id) {
        return repository.findById(id);
    }

    public void saveRuleName(RuleName ruleName) {
        repository.save(ruleName);
    }

    public void updateRuleName(Integer id, RuleName newRuleName) {
        RuleName ruleName = repository.findById(id).orElseThrow();
        ruleName.setName(newRuleName.getName());
        ruleName.setDescription(newRuleName.getDescription());
        ruleName.setJson(newRuleName.getJson());
        ruleName.setTemplate(newRuleName.getTemplate());
        ruleName.setSqlPart(newRuleName.getSqlPart());
        ruleName.setSqlStr(newRuleName.getSqlStr());

        repository.save(ruleName);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
