package com.example.databasedao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
/**
 * Author: Roshan Phakami Punmagar
 * File Name: Movies.java
 * Date: 16/9/2024
 * Purpose:
 * main movie class
 * ******************************************************
 */
@Entity
@Data
@NoArgsConstructor
public class Movies {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String url;

    @NonNull
    private String description;

    @NonNull
    private String subGenre;

    //is movie in watchlist
    @NonNull
    private boolean inWatchList; // Renamed for clarity

    //is movie blocked
    @NonNull
    private boolean blocked;

    //link to watchlist
    @ManyToOne
    @JsonBackReference(value = "watchlist-class")
    private WatchList watchList;

    // Optionally, add a toggle method
    public boolean getInWatchList() {
        return this.inWatchList;
    }

    public void setInWatchList(boolean inWatchList) {
        this.inWatchList = inWatchList;
    }

    @Override
    public String toString() {
        return "Movies{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", url='" + url + '\''
                + ", description='" + description + '\''
                + ", subGenre='" + subGenre + '\''
                + ", inWatchList=" + this.inWatchList
                + '}';
    }
}
