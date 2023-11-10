package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService service;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> ruleNameList = service.findAllRuleName();
        model.addAttribute("rulenames", ruleNameList);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "ruleName/add";
        } else {
            service.saveRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = service.findById(id);
        if (ruleName.isPresent()) {
            model.addAttribute("name", ruleName.get().getName());
            model.addAttribute("description", ruleName.get().getDescription());
            model.addAttribute("json", ruleName.get().getJson());
            model.addAttribute("template", ruleName.get().getTemplate());
            model.addAttribute("sqlStr", ruleName.get().getSqlStr());
            model.addAttribute("sqlPart", ruleName.get().getSqlPart());
            return "ruleName/update";
        } else {
            //log warn ou info
            //TODO: retourner sur une page 404 au lieu d'update
            return "ruleName/update";
        }
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            //ajouter un log warn/info
            return "ruleName/update";
        } else {
            service.updateRuleName(id, ruleName);
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        service.deleteById(id);
        return "redirect:/ruleName/list";
    }
}
