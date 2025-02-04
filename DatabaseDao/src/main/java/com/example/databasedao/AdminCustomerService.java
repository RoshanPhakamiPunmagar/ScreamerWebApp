package com.example.databasedao;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author: Roshan Phakami PunMagar
 * File Name: AdminCustomerService.java
 * Date: 16/9/2024
 * Purpose:
 * This service class manages customer-related operations, including blocking, unblocking, and retrieving customer information.
 * It interacts with the CustomerRepository to perform CRUD operations on the customer data.
 * ******************************************************
 */


@Service
// Service class for managing customer operations, interacting with the CustomerRepository for database operations.
public class AdminCustomerService {

    private final CustomerRepository customerRepository;

    // Constructor injection for CustomerRepository to access customer data.
    public AdminCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Method to block a customer by their ID.
    public void blockCustomer(Long custId) {
        try {
            // Retrieve customer by ID, throw exception if not found.
            Customer customer = customerRepository.findById(custId).orElseThrow(() -> new RuntimeException("Customer not found"));
            customer.setBlocked(true); // Set the customer as blocked.
            customerRepository.save(customer); // Save the updated customer entity.
            System.out.println("Customer blocked"); // Log the operation result.
        } catch (FeignException e) {
            // Handle Feign-specific exceptions and log the error.
            System.err.println("Feign exception while blocking customer: " + e.getMessage());
            throw new RuntimeException("Failed to block customer", e);
        } catch (Exception e) {
            // Handle general exceptions and log the error.
            System.err.println("Exception while blocking customer: " + e.getMessage());
            throw new RuntimeException("Failed to block customer", e);
        }
    }

    // Method to unblock a customer by their ID.
    public void unblockCustomer(Long custId) {
        try {
            // Retrieve customer by ID, throw exception if not found.
            Customer customer = customerRepository.findById(custId).orElseThrow(() -> new RuntimeException("Customer not found"));
            customer.setBlocked(false); // Set the customer as unblocked.
            customerRepository.save(customer); // Save the updated customer entity.
            System.out.println("Customer unblocked"); // Log the operation result.
        } catch (FeignException e) {
            // Handle Feign-specific exceptions and log the error.
            System.err.println("Feign exception while unblocking customer: " + e.getMessage());
            throw new RuntimeException("Failed to unblock customer", e);
        } catch (Exception e) {
            // Handle general exceptions and log the error.
            System.err.println("Exception while unblocking customer: " + e.getMessage());
            throw new RuntimeException("Failed to unblock customer", e);
        }
    }

    // Method to check if a customer is blocked by their ID.
    public boolean isCustomerBlocked(Long custId) {
        try {
            // Retrieve customer by ID, throw exception if not found.
            Customer customer = customerRepository.findById(custId).orElseThrow(() -> new RuntimeException("Customer not found"));
            // Log and return the blocked status of the customer.
            System.out.println("Customer with ID: " + custId + " is blocked: " + customer.isBlocked());
            return customer.isBlocked();
        } catch (Exception e) {
            // Handle and throw a runtime exception if checking the status fails.
            throw new RuntimeException("Failed to check customer status", e);
        }
    }

    // Method to retrieve a list of blocked customers.
    public List<Customer> getBlockedCustomers() {
        // Fetch and return customers where blocked status is true.
        return customerRepository.findByBlocked(true);
    }

    // Method to retrieve a list of unblocked customers.
    public List<Customer> getUnblockedCustomers() {
        // Fetch and return customers where blocked status is false.
        return customerRepository.findByBlocked(false);
    }

    // Method to retrieve a list of all customers.
    public List<Customer> getAllCustomers() {
        // Fetch and return all customer entities.
        return customerRepository.findAll();
    }
}
