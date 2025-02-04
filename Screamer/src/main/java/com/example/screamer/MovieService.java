package com.example.screamer;


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
    private final MovieClient movieClient;
    private List<Movies> movies;

    //Constructor
    public MovieService(MovieClient movieClient) {
        this.movieClient = movieClient;

    }

    //fetches all movies from the client and returns it to controller
    public List<Movies> getAllMovies() {
        try {
            movies = movieClient.getAllMovies();
            return movies;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all movies: " + e.getMessage(), e);
        }
    }

    //gets all the watchlist added and returns it to controller
    public WatchList getAllWatchListMovies(String custID) {
        try {
            System.out.println(getAllMovies().get(0).getIsWatchList());
            WatchList watchLists = movieClient.getAllWatchList(custID);
            return watchLists;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all movies: " + e.getMessage(), e);
        }
    }

    //removes movie from watchlist
    public void removeFromWatchList(Long id, Customer customer) {

        movieClient.addMoveToWatchList(id, "Remove", customer);

    }

    //adds movie from watchlist
    public void addToWatchList(Long id, Customer customer) {

        movieClient.addMoveToWatchList(id, "Add", customer);
    }
}
