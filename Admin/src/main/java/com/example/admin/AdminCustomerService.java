package com.example.admin;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminCustomerService.java
 * Date: 16/9/2024
 * Purpose:
 * Interface for communicating with customer-related services in the AdminApplication.
 * Handles requests like fetching, blocking, and unblocking customers.
 * ******************************************************
 */

@Service
// This class provides the service layer for handling customer-related operations.
// It interacts with the AdminCustomerClient (Feign client) to communicate with the external customer service.
public class AdminCustomerService {

    private final AdminCustomerClient adminClient;

    // Constructor injection for AdminCustomerClient to handle external service communication.
    public AdminCustomerService(AdminCustomerClient adminClient) {
        this.adminClient = adminClient;
    }

    // Method to block a customer by ID. Delegates the task to the Feign client.
    public void blockCustomer(Long customerId) {
        adminClient.blockCustomers(customerId);
    }

    // Method to unblock a customer by ID. Delegates the task to the Feign client.
    public void unblockCustomer(Long customerId) {
        adminClient.unblockCustomers(customerId);
    }

    // Method to check if a customer is blocked. Returns true if blocked, false otherwise.
    public boolean isCustomerBlocked(Long customerId) {
        return adminClient.checkCustomerStatus(customerId);
    }

    // Method to fetch the list of blocked customers from the Feign client.
    public List<Customer> getBlockedCustomers() {
        return adminClient.listBlockedCustomers();
    }

    // Method to fetch the list of unblocked customers from the Feign client.
    public List<Customer> getUnblockedCustomers() {
        return adminClient.listUnblockedCustomers();
    }

    // Method to fetch the list of all customers from the Feign client.
    public List<Customer> getAllCustomers() {
        return adminClient.listCustomers();
    }
}
