package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: MovieClient.java
 * Date :16/9/2024
 * Purpose :
 * MovieClient that takes request and sends that request to it assigned url
 * ******************************************************
 */
@FeignClient(name = "screamerService", contextId = "movieClient")
public interface MovieClient {

    @GetMapping("/movies/get/all")
    List<Movies> getAllMovies();

    @GetMapping("/movies/get/watchlist/all/{custID}")
    WatchList getAllWatchListMovies(@PathVariable("custID") String custID);

    @PutMapping("/movies/update/{id}")
    Movies updateMovieById(@PathVariable("id") Long id, @RequestBody Movies movie);

    @PostMapping(value = "/movies/add/watchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addMovieToWatchList(@PathVariable("id") Long id, @RequestParam("action") String action, @RequestBody Customer customer);

}
