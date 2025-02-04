package com.example.databasedao;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Roshan Phakami Punmagar
 * File Name: CustomerService.java
 * Date: 16/9/2024
 * Purpose:
 * service class containing customer related logic
 * ******************************************************
 */

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //add customer
    public Customer postCustomer(Customer customer) {
        System.out.println("Debug: " + customer);
        return customerRepository.save(customer);
    }

    //get list of all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findByBlocked(false);
    }

    //returns a customer based on their ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    //returns a customer based on their email
    Customer getByEmail(String email) {
        return customerRepository.findByemail(email);
    }

}
