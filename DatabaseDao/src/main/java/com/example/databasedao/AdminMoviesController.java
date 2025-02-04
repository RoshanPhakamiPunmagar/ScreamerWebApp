package com.example.databasedao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminMoviesController.java
 * Date: 16/9/2024
 * Purpose:
 * This class acts as a REST controller to handle HTTP requests related to movie management.
 * It provides endpoints for listing all movies, blocked movies, and unblocked movies, checking a movie's blocked status,
 * and blocking or unblocking movies.
 * ******************************************************
 */

@RestController
@RequestMapping("admin/movies")
// This class is a REST controller for managing movie-related HTTP requests.
// It provides endpoints for listing movies, checking their status, and blocking/unblocking them.
public class AdminMoviesController {

    private final AdminMoviesService adminMoviesService;

    // Constructor injection for AdminMoviesService to manage movie-related operations.
    public AdminMoviesController(AdminMoviesService adminMoviesService) {
        this.adminMoviesService = adminMoviesService;
    }

    @GetMapping("/get/all")
    // Endpoint to retrieve a list of all movies.
    // Returns a ResponseEntity containing the list of movies.
    public ResponseEntity<List<Movies>> listMovies() {
        List<Movies> movies = adminMoviesService.getAllMovies();
        return ResponseEntity.ok(movies); // 200 OK with the list of all movies
    }

    @GetMapping("/blocked")
    // Endpoint to retrieve a list of blocked movies.
    // Returns a ResponseEntity containing the list of blocked movies.
    public ResponseEntity<List<Movies>> listBlockedMovies() {
        List<Movies> blockedMovies = adminMoviesService.getBlockedMovies();
        return ResponseEntity.ok(blockedMovies); // 200 OK with the list of blocked movies
    }

    @GetMapping("/unblocked")
    // Endpoint to retrieve a list of unblocked movies.
    // Returns a ResponseEntity containing the list of unblocked movies.
    public ResponseEntity<List<Movies>> listUnblockedMovies() {
        List<Movies> unblockedMovies = adminMoviesService.getUnblockedMovies();
        return ResponseEntity.ok(unblockedMovies); // 200 OK with the list of unblocked movies
    }

    @GetMapping("/status/{id}")
    // Endpoint to check if a movie with the given ID is blocked.
    // Returns true if the movie is blocked, false otherwise.
    public ResponseEntity<Boolean> checkMovieStatus(@PathVariable Long id) {
        boolean isBlocked = adminMoviesService.isMovieBlocked(id);
        return ResponseEntity.ok(isBlocked); // 200 OK with the movie's blocked status
    }

    @PostMapping("/block/{id}")
    // Endpoint to block a movie by its ID.
    // Returns 204 No Content on successful blocking.
    public ResponseEntity<Void> blockMovie(@PathVariable Long id) {
        adminMoviesService.blockMovie(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful blocking
    }

    @PostMapping("/unblock/{id}")
    // Endpoint to unblock a movie by its ID.
    // Returns 204 No Content on successful unblocking.
    public ResponseEntity<Void> unblockMovie(@PathVariable Long id) {
        adminMoviesService.unblockMovie(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful unblocking
    }
}
