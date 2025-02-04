package com.example.screamerwebapp;

import feign.FeignException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Roshan Phakami PunMagar
 *  File Name: AdminCustomerViewController.java
 *  Date :16/9/2024
 *  Purpose :
 * Controller for handling HTTP requests related to customer management in the web application.
 * Provides endpoints for viewing, blocking, and unblocking customers, and integrates with the AdminCustomerClient.
 * ******************************************************
 */
@Controller
@RequestMapping("/admin/customer/view")
public class AdminCustomerViewController {

    private final AdminCustomerClient adminCustomerClient;

    // Constructor to inject AdminCustomerClient
    public AdminCustomerViewController(AdminCustomerClient adminCustomerClient) {
        this.adminCustomerClient = adminCustomerClient;
    }

    /**
     * Fetches and displays a list of all customers.
     */
    @GetMapping("/all")
    public String listCustomers(Model model) {
        List<Customer> customers = adminCustomerClient.listCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    /**
     * Fetches and displays a list of all blocked customers.
     */
    @GetMapping("/customers/blocked")
    public String listBlockedCustomers(Model model) {
        List<Customer> blockedCustomers = adminCustomerClient.listBlockedCustomers();
        model.addAttribute("customers", blockedCustomers);
        return "blocked-customers";
    }

    /**
     * Fetches and displays a list of all unblocked customers.
     */
    @GetMapping("/customers/unblocked")
    public String listUnblockedCustomers(Model model) {
        List<Customer> unblockedCustomers = adminCustomerClient.listUnblockedCustomers();
        model.addAttribute("customers", unblockedCustomers);
        return "unblocked-customers";
    }

    /**
     * Checks and displays the block status of a specific customer based on their ID.
     */
    @GetMapping("/customers/status/{id}")
    public String checkCustomerStatus(@PathVariable Long id, Model model) {
        boolean isBlocked = adminCustomerClient.checkCustomerStatus(id);
        model.addAttribute("isBlocked", isBlocked);
        return "customer-status";
    }

    /**
     * Blocks a customer based on their ID and redirects to the list of all customers.
     */
    @PostMapping("block/{id}")
    public String blockCustomer(@PathVariable Long id) {
        adminCustomerClient.blockCustomers(id);
        return "redirect:/admin/customer/view/all";
    }

    /**
     * Unblocks a customer based on their ID and redirects to the list of all customers.
     */
    @PostMapping("unblock/{id}")
    public String unblockCustomer(@PathVariable Long id) {
        adminCustomerClient.unblockCustomers(id);
        return "redirect:/admin/customer/view/all";
    }
}
