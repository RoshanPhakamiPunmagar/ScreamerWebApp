package com.example.admin;

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
 * Author: Roshan Phakami PunMagar
 * File Name: Movies.java
 * Date: 16/9/2024
 * Purpose:
 * This class represents a movie entity with attributes such as id, title, URL, description, sub-genre, and blocked status.
 * It is used for mapping movie data to the database.
 * ******************************************************
 */


@Entity
@Data
@NoArgsConstructor
// Represents a movie entity in the database.
// This class is annotated with Lombok annotations to generate boilerplate code such as getters, setters, and constructors.
public class Movies {

    @Id
    @GeneratedValue
    // Primary key for the Movies entity. It is auto-generated.
    private Long id;

    @NonNull
    // The title of the movie. This field is required (non-nullable).
    private String title;

    @NonNull
    // The URL or location where the movie can be accessed or streamed. This field is required (non-nullable).
    private String url;

    @NonNull
    // A brief description of the movie. This field is required (non-nullable).
    private String description;

    @NonNull
    // The sub-genre of the movie (e.g., action, drama). This field is required (non-nullable).
    private String subGenre;

    @NonNull
    // Indicates whether the movie is blocked or not. This field is required (non-nullable).
    private boolean blocked;
}
