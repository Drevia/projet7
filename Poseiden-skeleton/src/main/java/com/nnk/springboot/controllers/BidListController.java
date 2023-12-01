package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class BidListController {
    @Autowired
    BidListService service;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> bidLists = service.findAll();
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "bidList/add";
        } else {
            service.saveBidList(bid);
            return "redirect:/bidList/list";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<BidList> bidList = service.findById(id);
        if (bidList.isPresent()){
            model.addAttribute("account", bidList.get().getAccount());
            model.addAttribute("type", bidList.get().getType());
            model.addAttribute("bidQuantity", bidList.get().getBidQuantity());
            return "bidList/update";
        } else {
            //TODO: retourner page 404
            return "bidList/update";
        }
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            return "ruleName/update";
        } else {
            service.updateBidList(id, bidList);
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        service.deleteById(id);
        return "redirect:/bidList/list";
    }
}
