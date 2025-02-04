package com.example.screamerwebapp;

import feign.FeignException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * AdminMovieViewController
 *
 * Author: Roshan Phakami Pun Magar
 * File Name: MovieViewController.java
 * Date: 16/9/2024
 * Purpose:
 * It is responsible for handling requests related to movie management
 *  by the admin. It interacts with the AdminMoviesClient to perform operations on movie data.
 *
 */
@Controller
@RequestMapping("/admin/movie/view")
public class AdminMovieViewController {

    private final AdminMoviesClient adminMoviesClient;

    /**
     * Constructor for AdminMovieViewController.
     */
    public AdminMovieViewController(AdminMoviesClient adminMoviesClient) {
        this.adminMoviesClient = adminMoviesClient;
    }

    /**
     * Handles GET requests to list all movies.
     */
    @GetMapping("/all")
    public String listMovies(Model model) {
        List<Movies> movies = adminMoviesClient.listMovies();
        model.addAttribute("movies", movies);
        return "movie-list";
    }

    /**
     * Handles GET requests to list blocked movies.
     */
    @GetMapping("/blocked")
    public String listBlockedMovies(Model model) {
        List<Movies> blockedMovies = adminMoviesClient.listBlockedMovies();
        // Note: Redirecting to "/all" instead of displaying blocked movies directly
        return "redirect:/admin/movie/view/all";
    }

    /**
     * Handles GET requests to list unblocked movies.
     */
    @GetMapping("/unblocked")
    public String listUnblockedMovies(Model model) {
        List<Movies> unblockedMovies = adminMoviesClient.listUnblockedMovies();
        // Note: Redirecting to "/all" instead of displaying unblocked movies directly
        return "redirect:/admin/movie/view/all";
    }

    /**
     * Handles GET requests to check the status of a specific movie.
     */
    @GetMapping("/status/{id}")
    public String checkMovieStatus(@PathVariable Long id, Model model) {
        boolean isBlocked = adminMoviesClient.checkMovieStatus(id);
        model.addAttribute("isBlocked", isBlocked);
        return "movie-status";
    }

    /**
     * Handles POST requests to block a specific movie.
     */
    @PostMapping("/block/{id}")
    public String blockMovie(@PathVariable Long id) {
        try {
            adminMoviesClient.blockMovie(id);
        } catch (FeignException e) {
            // Log the Feign exception details for troubleshooting
            System.err.println("Feign exception while blocking movie: " + e.getMessage());
        } catch (Exception e) {
            // Log the general exception details
            System.err.println("Exception while blocking movie: " + e.getMessage());
        }
        return "redirect:/admin/movie/view/all";
    }

    /**
     * Handles POST requests to unblock a specific movie.
     */
    @PostMapping("/unblock/{id}")
    public String unblockMovie(@PathVariable Long id) {
        try {
            adminMoviesClient.unblockMovie(id);
        } catch (FeignException e) {
            // Log the Feign exception details for troubleshooting
            System.err.println("Feign exception while unblocking movie: " + e.getMessage());
        } catch (Exception e) {
            // Log the general exception details
            System.err.println("Exception while unblocking movie: " + e.getMessage());
        }
        return "redirect:/admin/movie/view/all";
    }
}
