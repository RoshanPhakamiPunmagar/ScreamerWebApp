package com.example.databasedao;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminMoviesService.java
 * Date: 16/9/2024
 * Purpose:
 * This service class manages movie-related operations, including blocking, unblocking, and retrieving movie information.
 * It interacts with the MoviesRepository to perform CRUD operations on the movie data.
 * ******************************************************
 */


@Service
// Service class for managing movie-related operations within the application.
// Interacts with MoviesRepository to perform CRUD operations on movie data.
public class AdminMoviesService {

    private final MoviesRepository moviesRepository;

    // Constructor injection for MoviesRepository to handle movie data operations.
    public AdminMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    // Method to block a movie by its ID.
    public void blockMovie(Long movieId) {
        try {
            // Retrieve movie by ID, throw exception if not found.
            Movies movie = moviesRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
            movie.setBlocked(true); // Set the movie as blocked.
            moviesRepository.save(movie); // Save the updated movie entity.
            System.out.println("Movie blocked"); // Log the operation result.
        } catch (FeignException e) {
            // Handle Feign-specific exceptions and log the error.
            System.err.println("Feign exception while blocking movie: " + e.getMessage());
            throw new RuntimeException("Failed to block movie", e);
        } catch (Exception e) {
            // Handle general exceptions and log the error.
            System.err.println("Exception while blocking movie: " + e.getMessage());
            throw new RuntimeException("Failed to block movie", e);
        }
    }

    // Method to unblock a movie by its ID.
    public void unblockMovie(Long movieId) {
        try {
            // Retrieve movie by ID, throw exception if not found.
            Movies movie = moviesRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
            movie.setBlocked(false); // Set the movie as unblocked.
            moviesRepository.save(movie); // Save the updated movie entity.
            System.out.println("Movie unblocked"); // Log the operation result.
        } catch (FeignException e) {
            // Handle Feign-specific exceptions and log the error.
            System.err.println("Feign exception while unblocking movie: " + e.getMessage());
            throw new RuntimeException("Failed to unblock movie", e);
        } catch (Exception e) {
            // Handle general exceptions and log the error.
            System.err.println("Exception while unblocking movie: " + e.getMessage());
            throw new RuntimeException("Failed to unblock movie", e);
        }
    }

    // Method to check if a movie with the given ID is blocked.
    public boolean isMovieBlocked(Long movieId) {
        try {
            // Retrieve movie by ID, throw exception if not found.
            Movies movie = moviesRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
            // Log and return the blocked status of the movie.
            System.out.println("Movie with ID: " + movieId + " is blocked: " + movie.isBlocked());
            return movie.isBlocked();
        } catch (Exception e) {
            // Handle and throw a runtime exception if checking the status fails.
            throw new RuntimeException("Failed to check movie status", e);
        }
    }

    // Method to retrieve a list of blocked movies.
    public List<Movies> getBlockedMovies() {
        // Fetch and return movies where blocked status is true.
        return moviesRepository.findByBlocked(true);
    }

    // Method to retrieve a list of unblocked movies.
    public List<Movies> getUnblockedMovies() {
        // Fetch and return movies where blocked status is false.
        return moviesRepository.findByBlocked(false);
    }

    // Method to retrieve a list of all movies.
    public List<Movies> getAllMovies() {
        // Fetch and return all movie entities.
        return moviesRepository.findAll();
    }
}
