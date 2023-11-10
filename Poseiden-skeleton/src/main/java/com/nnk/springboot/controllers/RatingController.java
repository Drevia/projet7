package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> ratingList = ratingService.findAllRating();
        model.addAttribute("ratings", ratingList);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result) {
        if (result.hasErrors()) {
            //ajouter un log warn/info
            return "rating/add";
        } else {
            ratingService.saveRating(rating);
            return "redirect:/rating/list";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Rating> rating = ratingService.findRatingById(id);
        //on s'assure que le modele soit bien présent
        if (rating.isPresent()){
            model.addAttribute("moodysRating", rating.get().getMoodysRating());
            model.addAttribute("fitchRating", rating.get().getFitchRating());
            model.addAttribute("sandPRating", rating.get().getSandPRating());
            model.addAttribute("orderNumber", rating.get().getOrderNumber());
            return "rating/update";
        } else {
            //log warn ou info
            //retourner sur une page 404 au lieu d'update
            return "rating/update";
        }
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            //ajouter un log warn/info
            return "rating/update";
        } else {
            ratingService.updateRating(id, rating);
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteById(id);
        return "redirect:/rating/list";
    }
}
