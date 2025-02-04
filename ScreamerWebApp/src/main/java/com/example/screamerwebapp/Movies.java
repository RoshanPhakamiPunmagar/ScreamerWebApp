package com.example.screamerwebapp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
@Entity @Data @NoArgsConstructor @RequiredArgsConstructor
public class Movies {
    @Id @GeneratedValue
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

    @NonNull
    private boolean blocked;

    @ManyToMany (fetch = FetchType.EAGER, mappedBy = "movies")
    private List<WatchList> watchLists = new ArrayList<>();

      public boolean getInWatchList() {
        return this.inWatchList;
    }

    public void setInWatchList(boolean inWatchList) {
        this.inWatchList = inWatchList;
    }


}
