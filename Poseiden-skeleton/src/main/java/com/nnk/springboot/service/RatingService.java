package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id).orElseThrow();
    }

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void updateRating(Integer id, Rating ratingToUpdate) {
        Rating rating = ratingRepository.findById(id).orElseThrow();
        rating.setFitchRating(ratingToUpdate.getFitchRating());
        rating.setMoodysRating(ratingToUpdate.getMoodysRating());
        rating.setSandPRating(ratingToUpdate.getSandPRating());
        rating.setOrderNumber(ratingToUpdate.getOrderNumber());

        ratingRepository.save(rating);
    }

    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }

}
