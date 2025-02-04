package com.example.databasedao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Author: Roshan Phakami Punmagar
 * File Name: Customer.java
 * Date: 16/9/2024
 * Purpose:
 * Main data class for customer entity
 * ******************************************************
 */

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    //customers security roll usually user or admin however any roll can be assigned
    private String roll;
    private String genre;

    //watch list mapping
    @OneToOne(mappedBy = "customer")
    @JsonManagedReference(value = "customer-class")
    private WatchList watchList;
    //is this customer blocked (I.E not allowed to login)
    @NonNull
    private boolean blocked;

}
