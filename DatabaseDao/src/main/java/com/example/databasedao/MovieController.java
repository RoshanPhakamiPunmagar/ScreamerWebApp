package com.example.databasedao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * Author: Roshan Phakami Punmagar
 * File Name: MovieController.java
 * Date: 16/9/2024
 * Purpose:
 * routes for interacting with movie database
 * ******************************************************
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //gets all movies
    @GetMapping("/get/all")
    public ResponseEntity<List<Movies>> retrieveAll() {
        List<Movies> movies = movieService.getAllMovies();
        System.out.println(movies.get(0).getInWatchList());
        return ResponseEntity.ok(movies);
    }

    //get watch list from a particular customer using their customer ID
    @GetMapping("/get/watchlist/all/{custID}")
    public ResponseEntity<WatchList> retrieveAllWatchList(@PathVariable String custID) {
        WatchList watchLists = movieService.getAllWatchList(custID);
        return ResponseEntity.ok(watchLists);
    }

    //gets a movie based on its ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Movies> retrieveId(@PathVariable Long id) {
        Optional<Movies> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //update a movie based on its ID
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Movies> updateMovieById(@PathVariable("id") Long id, @RequestBody Movies movie) {

        System.out.println(id + "21xx");
        try {
            System.out.println(id + "23xx");
            Movies updatedMovie = movieService.updateMovie(id, movie);
            System.out.println(id + "25xx");
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return null;
        }
    }

    //add a new movie
    @PostMapping("/add")
    public ResponseEntity<Movies> addMovie(@RequestBody Movies movie) {
        Movies createdMovie = movieService.postMovie(movie);
        return ResponseEntity.ok(createdMovie);
    }

    //add a movie to watchlist based on the watchlist ID
    @PostMapping(value = "/add/watchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMovieToWatchlist(@PathVariable("id") Long id, @RequestParam String action, @RequestBody Customer customer) {
        WatchList updatedMovie = new WatchList();
        if ("Add".equals(action)) {
            movieService.postWatchlist(id, customer);
        } else if ("Remove".equals(action)) {
            movieService.removeWatchList(id, customer);
        }

    }

}
