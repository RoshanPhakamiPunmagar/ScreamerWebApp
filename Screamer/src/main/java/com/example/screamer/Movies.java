package com.example.screamer;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: Movies.java
 * Date :16/9/2024
 * Purpose :
 * Movies class that defines the Movie Entity
 * ******************************************************
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Movies {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String url;

    @NonNull
    private String description;

    @NonNull
    private String subGenre;

    @NonNull
    private boolean inWatchList;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private List<WatchList> watchLists = new ArrayList<>();

    public void addWatchlist(WatchList watchList) {
        // Check to avoid adding duplicates
        if (!this.watchLists.contains(watchList)) {
            this.watchLists.add(watchList);
            watchList.getMovies().add(this); // Maintain the bidirectional relationship
        }
    }

    public boolean getIsWatchList() {
        return this.inWatchList;
    }

}
