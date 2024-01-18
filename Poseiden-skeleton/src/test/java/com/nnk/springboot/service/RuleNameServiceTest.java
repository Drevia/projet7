package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameServiceTest {

    @Mock
    RuleNameRepository ruleNameRepository;

    @InjectMocks
    RuleNameService service;


    @Test
    void findRuleNameById_OK() {
        when(ruleNameRepository.findById(any())).thenReturn(Optional.of(new RuleName()));
        assertDoesNotThrow(() -> service.findById(1));
    }

    @Test
    void findAllRuleName_OK() {
        List<RuleName> ruleNameList = new ArrayList<>();
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setTemplate("temp");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameList.add(ruleName);

        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        assertDoesNotThrow(() -> service.findAllRuleName());
        assertEquals(1, ruleNameList.size());
    }

    @Test
    void saveRuleName_OK() {
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(new RuleName());
        assertDoesNotThrow(() -> service.saveRuleName(new RuleName()));
    }

    @Test
    void updateRuleName_OK() {
        RuleName ruleName = new RuleName();
        ruleName.setName("name");
        ruleName.setDescription("desc");
        ruleName.setJson("json");
        ruleName.setTemplate("temp");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");

        RuleName ruleNameUpdate = new RuleName();
        ruleName.setName("1");
        ruleName.setDescription("2");
        ruleName.setJson("3");
        ruleName.setTemplate("4");
        ruleName.setSqlStr("5");
        ruleName.setSqlPart("6");

        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        assertDoesNotThrow(() -> service.updateRuleName(1, ruleNameUpdate));
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    void deleteRuleName_Ok() {
        doNothing().when(ruleNameRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> service.deleteById(anyInt()));
    }

}
