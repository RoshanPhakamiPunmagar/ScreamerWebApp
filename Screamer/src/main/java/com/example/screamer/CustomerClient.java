package com.example.screamer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: CustomerClient.java
 * Date :16/9/2024
 * Purpose :
 * CustomerClient that takes request and sends that request to it assigned url
 * ******************************************************
 */
@FeignClient(name = "database", contextId = "customerClient")
public interface CustomerClient {

    @GetMapping("/user/get/all")
    List<Customer> getAllCustomer();

    @GetMapping("/user/get/{id}")
    ResponseEntity<Customer> retrieveById(@RequestParam Long id);

    @GetMapping("/user/getByEmail/{email}")
    Customer getByEmail(@RequestParam String email);

    @PostMapping("/user/add")
    @ResponseBody
    ResponseEntity<Customer> addCustomer(@RequestBody Customer customer);

}
