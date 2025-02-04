package com.example.databasedao;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 * Author: Roshan Phakami Punmagar
 * File Name: MovieService.java
 * Date: 16/9/2024
 * Purpose:
 * 
 * ******************************************************
 */
@Service
public class MovieService {

    private final MoviesRepository movieRepository;

    private final CustomerRepository customerRepository;

    private final CustomerService customerService;
    private final WatchListRepository watchListRepository;
    private WatchList watchListToAdd = new WatchList();
    private List<Movies> moviesToAdd;

    public MovieService(MoviesRepository movieRepository, CustomerRepository customerRepository, CustomerService customerService, WatchListRepository watchListRepository) {
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.watchListRepository = watchListRepository;

    }

    //add new movie
    public Movies postMovie(Movies movie) {
        System.out.println(movie.getId());
        return movieRepository.save(movie);
    }

    //remove movie from watchlist
    @Transactional
    public void removeWatchList(Long id, Customer customer) {
        Optional<Movies> movies = movieRepository.findById(id);
        WatchList watchLists = getAllWatchList(customer.getEmail());
        Movies movieToRemove = movies.get();
        movieToRemove.setInWatchList(false);
        movieRepository.save(movieToRemove);
        watchLists.getMovies().remove(movieToRemove);
        watchListToAdd = watchListRepository.save(watchLists);

    }

    //create a new watch list for customer with ID
    public void postWatchlist(Long id, Customer customer) {
        // Retrieve the movie from the repository
        Movies movie = movieRepository.findById(id).get();
        Customer cust = customerRepository.findByemail(customer.getEmail());

        movie.setInWatchList(true);

        
        if (cust.getWatchList() == null) {
            System.out.println("Debug: --------------------------------------ADD TO WATCHLIST-----------------------------------------------------------------------------");

            System.out.println("Debug: watch list is null adding new ");
            WatchList newWatchlist = new WatchList();
            newWatchlist.setCustomer(cust);
            newWatchlist.addMovie(movie);
            cust.setWatchList(newWatchlist);
            watchListRepository.save(newWatchlist);
            customerRepository.save(cust);

        } else {
            System.out.println("Debug: watch list is not null adding just movies ");

            cust.getWatchList().addMovie(movie);
            watchListRepository.findById(cust.getWatchList().getId()).get().addMovie(movie);
            customerRepository.save(cust);

        }
        System.out.println("Debug : WatchListContents: " + cust);

        System.out.println("Debug: ----------------------------------------------END DEBUG------------------------------------------------------------------");

        
    }

    //get all movies
    public List<Movies> getAllMovies() {
        System.out.println(movieRepository.findAll());
        return movieRepository.findByBlocked(false);
    }

    //return customer watchlist
    public WatchList getAllWatchList(String custId) {
        System.out.println(watchListRepository.findAll().size());
        Customer cust = customerRepository.findByemail(custId);
        WatchList wL = new WatchList();
        System.out.println("Debug: ------------------------------------GETWatchList--------------------------------------------------------------------------");
        if (cust.getWatchList() == null || cust.getWatchList().getMovies().isEmpty()) {
            System.out.println("Debug: watchlist size 0 no watchlist");
            return wL;
        } else {
            System.out.println("Debug: watchlist size above 0 returning watchlist");

            wL = cust.getWatchList();
            WatchList wL2 = watchListRepository.findBycustomer(cust);
            System.out.println("Debug: wL movies: " + wL.getMovies());
            System.out.println("Debug: wL2 movies: " + wL2.getMovies());
            System.out.println("Debug: " + wL);

        }
        System.out.println("Debug: ------------------------------------END--------------------------------------------------------------------------");

        return wL;

    }

    //return a movie based on its ID
    public Optional<Movies> getMovieById(Long id) {
        try {
            return movieRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch movie by ID: " + e.getMessage(), e);
        }
    }

    //update a movie
    public Movies updateMovie(Long id, Movies movie) {
        System.out.println("124124");
        Movies existingMovie = movieRepository.findById(id).get();
        System.out.println(existingMovie.getId());
        // Update the fields as needed
        existingMovie.setInWatchList(movie.getInWatchList());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setSubGenre(movie.getSubGenre());
        System.out.println("Done");

        // Save the updated movie
        return movieRepository.save(existingMovie);
    }

}
