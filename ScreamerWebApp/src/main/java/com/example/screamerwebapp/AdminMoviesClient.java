package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Roshan Phakami Pun Magar
 *   File Name: AdminMoviesClient.java
 *   Date :16/9/2024
 *   Purpose :
 * Feign client interface for interacting with the Admin Movies service.
 * This interface defines the endpoints for managing movies.
 */
@FeignClient(name = "adminService", contextId = "adminMoviesClient")
interface AdminMoviesClient {

    /**
     * Retrieve a list of all movies.
     */
    @GetMapping("/admin/movies/get/all")
    List<Movies> listMovies();

    /**
     * Retrieve a list of all blocked movies.
     */
    @GetMapping("/admin/movies/blocked")
    List<Movies> listBlockedMovies();

    /**
     * Retrieve a list of all unblocked movies.
     */
    @GetMapping("/admin/movies/unblocked")
    List<Movies> listUnblockedMovies();

    /**
     * Check the status of a specific movie to determine if it is blocked or not.
     */
    @GetMapping("/admin/movies/status/{id}")
    Boolean checkMovieStatus(@PathVariable Long id);

    /**
     * Block a specific movie, making it unavailable for viewing.
     */
    @PostMapping("/admin/movies/block/{id}")
    ResponseEntity<Void> blockMovie(@PathVariable Long id);

    /**
     * Unblock a specific movie, making it available for viewing.
     */
    @PostMapping("/admin/movies/unblock/{id}")
    ResponseEntity<Void> unblockMovie(@PathVariable Long id);
}
