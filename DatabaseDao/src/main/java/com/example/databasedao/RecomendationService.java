package com.example.databasedao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;
/**
 * Author: Roshan Phakami Punmagar
 * 
 * File Name: RecommendationService.java
 * Date: 16/9/2024
 * Purpose:
 * 
 * ******************************************************
 */
@Service
public class RecomendationService {
    //inject movie service
    @Autowired
    private final MovieService movieService;
    //create random number generator object
    Random random = new Random();

    public RecomendationService(MovieService movieService) {
        this.movieService = movieService;
    }

    //returns a random movie recommendation
    ResponseEntity<Movies> getRecommendation() {

        Movies randomMovie = getRandomMovie();

        return ResponseEntity.ok(randomMovie);

    }

    //returns a random movie recommendation
    Movies getRandomMovie() {
        List<Movies> movies = movieService.getAllMovies();

        return movies.get(random.nextInt(movies.size()));
    }

}
