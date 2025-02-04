package com.example.screamer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: MovieViewController.java
 * Date :16/9/2024
 * Purpose :
 * MovieViewController a controller that all the user request go through.
 * All the request functionality depends upon
 * All the request return ResposnseEntity type
 * ******************************************************
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/get/watchlist/all/{custID}")
    public ResponseEntity<WatchList> getAllWatchlistMovies(@PathVariable("custID") String custID) {
        System.out.println("xx");
        WatchList movies = movieService.getAllWatchListMovies(custID);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/add/watchlist/{id}")
    public void addMovieToWatchlist(@PathVariable("id") Long movieId, @RequestParam String action, @RequestBody Customer customer) {

        System.out.println(action);
        WatchList watchList = new WatchList();
        if ("Add".equals(action)) {
            movieService.addToWatchList(movieId, customer);
        } else if ("Remove".equals(action)) {
            movieService.removeFromWatchList(movieId, customer);
        }

    }

}
