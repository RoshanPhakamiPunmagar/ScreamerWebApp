package com.example.screamerwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Roshan Phakami Punmagar
 * File: MovieViewController.java
 * Purpose : MovieViewController a controller that all the user request go
 * through. All the request functionality depends upon
 * ******************************************************
 */
@Controller
@RequestMapping("/view")
public class MovieViewController {

    private final MovieService movieService;
    private final CustomerClient customerClient;
    String username;
    //Constructor
    public MovieViewController(MovieService movieService, CustomerClient customerClient) {
        this.movieService = movieService;
        this.customerClient = customerClient;


    }

    //Gets all the watchlist movies and returns watchlist.html
    @GetMapping("/watchlist/all")
    public String getAllWatchlistMovies(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Debug: " + username);
        System.out.println("Debug: ------------------------------------------------ view watchlist ------------------------------------------- ");

        WatchList watchList = movieService.getAllWatchListMovies(username);
        System.out.println("Debug: watchlist contents" + watchList);
        System.out.println("Debug: ------------------------------------------------ END ------------------------------------------- ");

        model.addAttribute("watchlist", watchList);
        return "watchlist";
    }

   

    //Gets recommendation and return recommend_page.html
    @GetMapping("/recommendation")
    public String getRecommendationMovies(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Movies recommendedMovie = movieService.getRecommendation();
        model.addAttribute("recommendation", recommendedMovie);
        return "recommend_page";
    }

    //gets all the movies and returns movie_page.html
    @GetMapping("/all")
    public String getAllMovies(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        List<Movies> m = movieService.getAllMovies();
        for (Movies i : m) {
            i.setInWatchList(false);
        }
        if (customerClient.getByEmail(username).getWatchList() != null) {
            System.out.println("Customer: " + username + " watchlist is not null");
            if (customerClient.getByEmail(username).getWatchList().getMovies() != null) {
                System.out.println("Customer: " + customerClient.getByEmail(username) + " movie list is not null");

                for (Movies i : customerClient.getByEmail(username).getWatchList().getMovies()) {
                    for (Movies i2 : m) {
                        if (i2.getId() == i.getId()) {
                            System.out.println("IDs match " + i2.getId());
                            i2.setInWatchList(true);
                        }
                    }
                }

            }
        }

        model.addAttribute("movies", m);
        return "movie_page";
    }

    //Adds movie to watchlist and redirects user ot /view/all
    @PostMapping("/add/watchlist/{id}")
    public String addMovieToWatchlist(@PathVariable("id") Long movieId, @RequestParam String action) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        Customer cust = customerClient.getByEmail(username);

        if ("Add".equals(action)) {
            movieService.addToWatchList(movieId, cust);
        } else if ("Remove".equals(action)) {
            movieService.removeFromWatchList(movieId, cust);
        }
        return "redirect:/view/all";
    }
    
    

}

