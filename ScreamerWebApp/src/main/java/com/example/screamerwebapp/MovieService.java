package com.example.screamerwebapp;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: MovieService.java
 * Date :16/9/2024
 * Purpose :
 * MovieService gets request from controller then sends the request to its corresponding client
 * And then it fetches the data it recived from its corresponding client to user
 * ******************************************************
 */
@Service
public class MovieService {

    //initializing
    private final RecommendationClient recommendationClient;
    private final MovieClient movieClient;
    private final CustomerClient customerClient;
    //Constructor
    public MovieService(RecommendationClient recommendationClient, MovieClient movieClient , CustomerClient customerClient) {
        this.recommendationClient = recommendationClient;
        this.movieClient = movieClient;
        this.customerClient = customerClient;
    }

    //fetches all movies from the client and returns it to controller
    public List<Movies> getAllMovies() {
        try {
            return movieClient.getAllMovies();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all movies: " + e.getMessage(), e);
        }
    }
    //get recommended movie and returns it to controller
    public Movies getRecommendation(){
        return recommendationClient.getRecomendation().getBody();
    }
    //gets all the watchlist added and returns it to controller
    public WatchList getAllWatchListMovies(String custID) {
        try {
            return movieClient.getAllWatchListMovies(custID);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all movies: " + e.getMessage(), e);
        }
    }
    //removes movie from watchlist
public void removeFromWatchList(Long id, Customer customer) {
        movieClient.addMovieToWatchList(id, "Remove", customer);

}
        //adds movie from watchlist
    public void addToWatchList(Long id,  Customer customer) {
        movieClient.addMovieToWatchList(id, "Add", customer);
    }
}

