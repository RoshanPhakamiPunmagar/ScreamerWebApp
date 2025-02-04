package com.example.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminCustomerController.java
 * Date: 16/9/2024
 * Purpose:
 * Interface for communicating with customer-related services in the AdminApplication.
 * Handles requests like fetching, blocking, and unblocking customers.
 * ******************************************************
 */


@RestController
@RequestMapping("/admin/customer")
// This class is a REST controller for handling customer-related requests at the /admin/customer endpoint.
public class AdminCustomerController {

    private final AdminCustomerService adminCustomerService;

    // Constructor injection for AdminCustomerService to handle business logic.
    public AdminCustomerController(AdminCustomerService adminCustomerService) {
        this.adminCustomerService = adminCustomerService;
    }

    @GetMapping("/get/all")
    // Endpoint to fetch all customers. Returns a ResponseEntity containing the list of customers.
    public ResponseEntity<List<Customer>> listCustomers() {
        List<Customer> customers = adminCustomerService.getAllCustomers();
        return ResponseEntity.ok(customers); // 200 OK with the list of customers
    }

    @GetMapping("/blocked")
    // Endpoint to fetch all blocked customers. Returns a ResponseEntity containing the list of blocked customers.
    public ResponseEntity<List<Customer>> listBlockedCustomers() {
        List<Customer> blockedCustomers = adminCustomerService.getBlockedCustomers();
        return ResponseEntity.ok(blockedCustomers); // 200 OK with the list of blocked customers
    }

    @GetMapping("/unblocked")
    // Endpoint to fetch all unblocked customers. Returns a ResponseEntity containing the list of unblocked customers.
    public ResponseEntity<List<Customer>> listUnblockedCustomers() {
        List<Customer> unblockedCustomers = adminCustomerService.getUnblockedCustomers();
        return ResponseEntity.ok(unblockedCustomers); // 200 OK with the list of unblocked customers
    }

    @GetMapping("/status/{id}")
    // Endpoint to check if a customer is blocked or not by their ID.
    // Returns a ResponseEntity containing a Boolean value (true if blocked, false otherwise).
    public ResponseEntity<Boolean> checkCustomerStatus(@PathVariable Long id) {
        boolean isBlocked = adminCustomerService.isCustomerBlocked(id);
        return ResponseEntity.ok(isBlocked); // 200 OK with the status of the customer
    }

    @PostMapping("/block/{id}")
    // Endpoint to block a customer by their ID.
    // Returns a ResponseEntity with no content to indicate success.
    public ResponseEntity<Void> blockCustomer(@PathVariable Long id) {
        adminCustomerService.blockCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful blocking
    }

    @PostMapping("/unblock/{id}")
    // Endpoint to unblock a customer by their ID.
    // Returns a ResponseEntity with no content to indicate success.
    public ResponseEntity<Void> unblockCustomer(@PathVariable Long id) {
        adminCustomerService.unblockCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful unblocking
    }
}
