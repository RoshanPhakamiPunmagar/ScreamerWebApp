package com.example.screamer;



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
    private Customer customer;

    public void addMovie(Movies movie) {
        // Check to avoid adding duplicates
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
            movie.getWatchLists().add(this); // Maintain the bidirectional relationship
        }
    }

}
