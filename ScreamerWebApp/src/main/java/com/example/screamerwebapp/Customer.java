package com.example.screamerwebapp;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: Movies.java
 * Date :16/9/2024
 * Purpose :
 * Customer class that defines the Customer Entity
 * ******************************************************
 */
@Entity @Data @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private long id;


    private String name;

    private String email;

    private String password;
    private String roll;
private String genre;
    
    @OneToOne (mappedBy = "customer")
    private WatchList watchList;

    @NonNull
    private boolean blocked;


}