package com.example.recommendation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Author: Roshan Phakami Punmagar
 *
 * File Name: AdminMoviesController.java
 * Date: 16/9/2024
 * Purpose:
 * class for sql queries 
 * ******************************************************
 */
@FeignClient(name = "database")
public interface RecomendationClient {

    //get recommendation
    @GetMapping("/recommendation/get")
    ResponseEntity<Movies> getRecomendation();
}
