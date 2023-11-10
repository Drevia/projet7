package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> findRatingById(Integer id) {
        return ratingRepository.findById(id);
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
