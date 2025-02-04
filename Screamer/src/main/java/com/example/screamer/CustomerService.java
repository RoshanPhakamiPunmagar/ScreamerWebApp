package com.example.screamer;


import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: CustomerService.java
 * Date :16/9/2024
 * Purpose :
 * CustomerService gets request from controller then sends the request to its corresponding client
 * And then it fetches the data it recived from its corresponding client to user
 * ******************************************************
 */
@Service
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    //gets customer by all customers
    public List<Customer> getAllCustomers() {
        return customerClient.getAllCustomer();
    }

    //gets customer by id
    public Customer getCustomerById(Long id) {
        return customerClient.retrieveById(id).getBody();
    }

    //gets customer by email
    public Customer getByEmail(String email) {
        return customerClient.getByEmail(email);
    }

    void addCustomer(Customer customer) {
        customerClient.addCustomer(customer);
    }
}
