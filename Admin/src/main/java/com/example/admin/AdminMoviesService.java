package com.example.admin;

import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminMoviesService.java
 * Date: 16/9/2024
 * Purpose:
 * This class acts as a service layer to handle movie-related operations within the AdminApplication.
 * It interacts with an external movie-related service to fetch, block, and unblock movies.
 * ******************************************************
 */

@Service
// This class acts as a service layer to manage movie-related operations.
// It communicates with the external movies service using the Feign client (AdminMoviesClient).
public class AdminMoviesService {

    private final AdminMoviesClient adminClient;

    // Constructor injection for AdminMoviesClient to handle interactions with the external movie service.
    public AdminMoviesService(AdminMoviesClient adminClient) {
        this.adminClient = adminClient;
    }

    // Method to block a movie by its ID. It uses the Feign client to perform the operation.
    public void blockMovie(Long movieId) {
        adminClient.blockMovie(movieId);
    }

    // Method to unblock a movie by its ID. It uses the Feign client to perform the operation.
    public void unblockMovie(Long movieId) {
        adminClient.unblockMovie(movieId);
    }

    // Method to check if a movie is blocked. Returns true if the movie is blocked, false otherwise.
    public boolean isMovieBlocked(Long movieId) {
        return adminClient.checkMovieStatus(movieId);
    }

    // Method to fetch the list of blocked movies from the external service using the Feign client.
    public List<Movies> getBlockedMovies() {
        return adminClient.listBlockedMovies();
    }

    // Method to fetch the list of unblocked movies from the external service using the Feign client.
    public List<Movies> getUnblockedMovies() {
        return adminClient.listUnblockedMovies();
    }

    // Method to fetch the list of all movies from the external service using the Feign client.
    public List<Movies> getAllMovies() {
        return adminClient.listMovies();
    }
}
