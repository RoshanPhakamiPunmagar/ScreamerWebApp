package com.example.screamerwebapp;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: ServerApplication.java
 * Date :16/9/2024
 * Purpose :
 * Entity Class For WatchList
 * ******************************************************
 */
@Entity
@Data
@NoArgsConstructor
public class WatchList {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(
            name = "watchlist_movies",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movies> movies = new ArrayList<>();

    @OneToOne
    @JsonBackReference
    private Customer customer;

    @Override
    public String toString() {
        return "WatchList{"
                + "id=" + id
                + ", movieList=" + movies
                + '}';
    }

}
