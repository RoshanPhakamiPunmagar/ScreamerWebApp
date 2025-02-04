package com.example.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Author: Roshan Phakami PunMagar
 * File Name: Customer.java
 * Date: 16/9/2024
 * Purpose:
 * This class represents a customer entity with attributes such as id, name, email, password, roll, and blocked status.
 * It is used for mapping customer data to the database.
 * ******************************************************
 */


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// Represents a customer entity in the database.
// This class is annotated with Lombok annotations to generate boilerplate code such as getters, setters, and constructors.
public class Customer {

    @Id
    @GeneratedValue
    // Primary key for the Customer entity. It is auto-generated.
    private long id;

    @NonNull
    // The customer's name. This field is required (non-nullable).
    private String name;

    @NonNull
    // The customer's email. This field is required (non-nullable).
    private String email;

    @NonNull
    // The customer's password. This field is required (non-nullable).
    private String password;

    // The role or additional identifier for the customer. This field is optional.
    private String roll;

    @NonNull
    // Indicates whether the customer is blocked or not. This field is required (non-nullable).
    private boolean blocked;
}
