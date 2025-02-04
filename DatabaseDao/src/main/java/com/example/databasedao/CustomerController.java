package com.example.databasedao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Roshan Phakami Punmagar
 * File Name: CustomerController.java
 * Date: 16/9/2024
 * Purpose:
 * Routes for interfacing with customer database
 * ******************************************************
 */
@RestController
@RequestMapping("/user")
public class CustomerController {
    //injects service class
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //returns all customers
    @GetMapping("/get/all")
    public ResponseEntity<List<Customer>> retrieveAll() {
        List<Customer> movies = customerService.getAllCustomers();

        return ResponseEntity.ok(movies);
    }

    //returns a customer based on the customers ID provided
    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> retrieveById(@RequestParam Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //adds a new customer
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.postCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    //Gets customer by email (used with auth to provide user specific content)
    @GetMapping("/getByEmail/{email}")
    public Customer getByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }
}