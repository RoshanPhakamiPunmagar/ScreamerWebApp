package com.example.databasedao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminCustomerController.java
 * Date: 16/9/2024
 * Purpose:
 * This class serves as a REST controller for handling HTTP requests related to customer management.
 * It provides endpoints for fetching all customers, blocked and unblocked customers, checking a customer's blocked status,
 * and for blocking or unblocking customers.
 * ******************************************************
 */


@RestController
@RequestMapping("/admin/customer")
// This class is a REST controller for managing customer-related HTTP requests.
// It handles operations such as listing customers, checking their status, and blocking/unblocking them.
public class AdminCustomerController {

    private final AdminCustomerService adminCustomerService;

    // Constructor injection for AdminCustomerService to manage customer-related operations.
    public AdminCustomerController(AdminCustomerService adminCustomerService) {
        this.adminCustomerService = adminCustomerService;
    }

    @GetMapping("/get/all")
    // Endpoint to retrieve a list of all customers.
    // Returns a ResponseEntity containing the list of customers.
    public ResponseEntity<List<Customer>> listCustomers() {
        List<Customer> customer = adminCustomerService.getAllCustomers();
        return ResponseEntity.ok(customer); // 200 OK with the list of customers
    }

    @GetMapping("/blocked")
    // Endpoint to retrieve a list of blocked customers.
    // Returns a ResponseEntity containing the list of blocked customers.
    public ResponseEntity<List<Customer>> listBlockedCustomers() {
        List<Customer> customerList = adminCustomerService.getBlockedCustomers();
        return ResponseEntity.ok(customerList); // 200 OK with the list of blocked customers
    }

    @GetMapping("/unblocked")
    // Endpoint to retrieve a list of unblocked customers.
    // Returns a ResponseEntity containing the list of unblocked customers.
    public ResponseEntity<List<Customer>> listUnblockedCustomers() {
        List<Customer> unBlockedCustomer = adminCustomerService.getUnblockedCustomers();
        return ResponseEntity.ok(unBlockedCustomer); // 200 OK with the list of unblocked customers
    }

    @GetMapping("/status/{id}")
    // Endpoint to check if a customer with the given ID is blocked.
    // Returns true if blocked, false otherwise.
    public ResponseEntity<Boolean> checkCustomerStatus(@PathVariable Long id) {
        boolean isBlocked = adminCustomerService.isCustomerBlocked(id);
        return ResponseEntity.ok(isBlocked); // 200 OK with the customer's blocked status
    }

    @PostMapping("/block/{id}")
    // Endpoint to block a customer by their ID.
    // Returns 204 No Content on successful blocking.
    public ResponseEntity<Void> blockCustomers(@PathVariable Long id) {
        adminCustomerService.blockCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful blocking
    }

    @PostMapping("/unblock/{id}")
    // Endpoint to unblock a customer by their ID.
    // Returns 204 No Content on successful unblocking.
    public ResponseEntity<Void> unblockCustomers(@PathVariable Long id) {
        adminCustomerService.unblockCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content on successful unblocking
    }
}
