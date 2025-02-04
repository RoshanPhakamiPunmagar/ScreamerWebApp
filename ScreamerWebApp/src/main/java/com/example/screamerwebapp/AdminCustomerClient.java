package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
/**
 *
 * @author Roshan Phakami PunMagar
 * File Name: AdminCustomerClient.java
 * Date :16/9/2024
 * Purpose :
 * Runs ServerApplicaiton
 * ******************************************************
 */
// Admin Feign Client interface for interacting with the customer service
// This interface defines methods for various operations related to customers
// through the Feign client, which simplifies HTTP communication and serialization.

@FeignClient(name = "adminService", contextId = "adminCustomerClient")
public interface AdminCustomerClient {

    // Fetch a list of all customers
    @GetMapping("/admin/customer/get/all")
    List<Customer> listCustomers();

    // Fetch a list of all blocked customers
    @GetMapping("/admin/customer/blocked")
    List<Customer> listBlockedCustomers();

    // Fetch a list of all unblocked customers
    @GetMapping("/admin/customer/unblocked")
    List<Customer> listUnblockedCustomers();

    // Check if a specific customer is blocked based on their ID
    @GetMapping("/admin/customer/status/{id}")
    Boolean checkCustomerStatus(@PathVariable Long id);

    // Block a customer based on their ID
    @PostMapping("/admin/customer/block/{id}")
    ResponseEntity<Void> blockCustomers(@PathVariable Long id);

    // Unblock a customer based on their ID
    @PostMapping("/admin/customer/unblock/{id}")
    ResponseEntity<Void> unblockCustomers(@PathVariable Long id);
}
