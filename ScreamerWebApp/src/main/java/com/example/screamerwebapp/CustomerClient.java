package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: CustomerClient.java
 * Date :16/9/2024
 * Purpose :
 * CustomerClient that takes request and sends that request to it assigned url
 * ******************************************************
 */
@FeignClient(name = "screamerService")
public interface CustomerClient {

    @GetMapping("/user/get/all")
    List<Customer> getAllCustomer();

    @GetMapping("/user/get/{id}")
    ResponseEntity<Customer> retrieveById(@RequestParam Long id);
    
    @GetMapping("/user/getByEmail/{email}")
    Customer getByEmail(@RequestParam String email);
    
    @PostMapping("/user/addCustomer")
    @ResponseBody
    ResponseEntity<Void> addCustomer(@RequestBody Customer customer);

}
