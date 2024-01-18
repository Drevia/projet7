package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
class RatingServiceTest {

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService service;


    @Test
    void findRatingById_OK() {
        when(ratingRepository.findById(any())).thenReturn(Optional.of(new Rating()));
        assertDoesNotThrow(() -> service.findRatingById(1));
    }

    @Test
    void findAllRating_OK() {
        List<Rating> ratingList = new ArrayList<>();
        Rating rating = new Rating();
        rating.setId(1L);
        Rating rating2 = new Rating();
        rating.setId(2L);
        ratingList.add(rating);
        ratingList.add(rating2);

        when(ratingRepository.findAll()).thenReturn(ratingList);
        assertDoesNotThrow(() -> service.findAllRating());
        assertEquals(2, ratingList.size());
    }

    @Test
    void saveRating_OK() {
        when(ratingRepository.save(any(Rating.class))).thenReturn(new Rating());
        assertDoesNotThrow(() -> service.saveRating(new Rating()));
    }

    @Test
    void updateRating_OK() {
        Rating rating = new Rating();
        rating.setMoodysRating("1");
        rating.setFitchRating("1");
        rating.setOrderNumber(1);
        rating.setSandPRating("1");
        rating.setId(1L);

        Rating ratingUpdate = new Rating();
        rating.setMoodysRating("2");
        rating.setFitchRating("3");
        rating.setOrderNumber(4);
        rating.setSandPRating("5");
        rating.setId(1L);

        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        assertDoesNotThrow(() -> service.updateRating(1, ratingUpdate));
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void deleteRating_Ok() {
        doNothing().when(ratingRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> service.deleteById(anyInt()));
    }

}
