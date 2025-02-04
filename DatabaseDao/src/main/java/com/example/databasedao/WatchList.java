package com.example.databasedao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/**
 * Author: Roshan Phakami Punmagar
 * File Name: AdminMoviesController.java
 * Date: 16/9/2024
 * Purpose:
 * watch list data class
 * ******************************************************
 */
@Entity
@Data
@NoArgsConstructor
public class WatchList {

    @Id
    @GeneratedValue
    private Long id;

    //links to movie list
    @ManyToMany
    @JoinTable(
            name = "watchlist_movies",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movies> movies = new ArrayList<>();

    //add movie to list
    public void addMovie(Movies movie) {
        // Check to avoid adding duplicates
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
        }
    }

    //links to customer
    @OneToOne
    @JsonBackReference(value = "customer-class")
    private Customer customer;

    public void removeMovie(Movies movie) {
        if (!this.movies.contains(movie)) {
            for (Movies m : this.movies) {
                if (movie.getId().equals(m.getId())) {
                    this.movies.remove(movie);
                }

            }
            System.out.println("Movie removed: " + movie.getId() + " " + this.id);
        }

    }

    @Override
    public String toString() {
        return "WatchList{"
                + "id=" + id
                + ", moviesCount=" + (movies != null ? movies.size() : 0)
                + '}';
    }
}
