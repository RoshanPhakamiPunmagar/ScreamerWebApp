package com.example.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminCustomerClient.java
 * Date: 16/9/2024
 * Purpose:
 * Interface for communicating with customer-related services in the AdminApplication.
 * Handles requests like fetching, blocking, and unblocking customers.
 * ******************************************************
 */

@FeignClient(name = "database", contextId = "adminCustomerClient")
// This interface uses Feign to communicate with the "admin-customer" microservice
// Base URL: http://localhost:8009/admin/customer
public interface AdminCustomerClient {

    @GetMapping("/admin/customer/get/all")
    // Retrieves a list of all customers
    List<Customer> listCustomers();

    @GetMapping("/admin/customer/blocked")
    // Retrieves a list of blocked customers
    List<Customer> listBlockedCustomers();

    @GetMapping("/admin/customer/unblocked")
    // Retrieves a list of unblocked customers
    List<Customer> listUnblockedCustomers();

    @GetMapping("/admin/customer/status/{id}")
    // Checks whether a customer with the given ID is blocked or not
    Boolean checkCustomerStatus(@PathVariable Long id);

    @PostMapping("/admin/customer/block/{id}")
    // Blocks the customer with the given ID and returns a ResponseEntity<Void> indicating success or failure
    ResponseEntity<Void> blockCustomers(@PathVariable Long id);

    @PostMapping("/admin/customer/unblock/{id}")
    // Unblocks the customer with the given ID and returns a ResponseEntity<Void> indicating success or failure
    ResponseEntity<Void> unblockCustomers(@PathVariable Long id);
}
