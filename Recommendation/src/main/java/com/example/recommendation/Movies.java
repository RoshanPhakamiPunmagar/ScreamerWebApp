package com.example.recommendation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
/**
 * Author: Roshan Phakami Punmagar
 * File Name: AdminMoviesController.java
 * Date: 16/9/2024
 * Purpose:
 * movie class
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

}
