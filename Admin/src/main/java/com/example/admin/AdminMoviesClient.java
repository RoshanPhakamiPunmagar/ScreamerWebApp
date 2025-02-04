package com.example.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminMoviesClient.java
 * Date: 16/9/2024
 * Purpose:
 * Interface for communicating with movie-related services in the AdminApplication.
 * Handles requests like fetching, blocking, and unblocking movies.
 * ******************************************************
 */

@FeignClient(name = "database")
// Feign client interface to communicate with the "admin-movies" microservice.
// Base URL for the movies service is: http://localhost:8009/admin/movies
interface AdminMoviesClient {

    @GetMapping("/admin/movies/get/all")
    // Fetches a list of all movies from the movies microservice.
    List<Movies> listMovies();

    @GetMapping("/admin/movies/blocked")
    // Fetches a list of all blocked movies.
    List<Movies> listBlockedMovies();

    @GetMapping("/admin/movies/unblocked")
    // Fetches a list of all unblocked movies.
    List<Movies> listUnblockedMovies();

    @GetMapping("/admin/movies/status/{id}")
    // Checks if a movie with the given ID is blocked.
    // Returns true if blocked, false otherwise.
    Boolean checkMovieStatus(@PathVariable Long id);

    @PostMapping("/admin/movies/block/{id}")
    // Blocks a movie by its ID.
    // Returns a ResponseEntity<Void> indicating the result of the operation.
    ResponseEntity<Void> blockMovie(@PathVariable Long id);

    @PostMapping("/admin/movies/unblock/{id}")
    // Unblocks a movie by its ID.
    // Returns a ResponseEntity<Void> indicating the result of the operation.
    ResponseEntity<Void> unblockMovie(@PathVariable Long id);
}
