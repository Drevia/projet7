package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     *
     * @return all Rating from the database
     */
    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }

    /**
     *
     * @param id of the Rating we want to find
     * @return Optional with Rating we found if exist or Optional empty
     */
    public Optional<Rating> findRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     *
     * @param rating we want to save in database
     */
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    /**
     *
     * @param ratingToUpdate we send to update
     */
    public void updateRating(Rating ratingToUpdate) {
        ratingRepository.save(ratingToUpdate);
    }

    /**
     *
     * @param id of the Rating we want to delete from the database
     */
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }

}
