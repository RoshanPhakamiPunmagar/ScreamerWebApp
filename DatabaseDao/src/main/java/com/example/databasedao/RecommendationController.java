package com.example.databasedao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Author: Roshan Phakami Punmagar
 * 
 * File Name: RecommendationController.java
 * Date: 16/9/2024
 * Purpose:
 * 
 * ******************************************************
 */
@RestController
@Data
@RequestMapping("/recommendation")
public class RecommendationController {
    //inject recommendation service
    @Autowired
    private final RecomendationService recommendationService;

    @GetMapping("/get")
    @ResponseBody
    // returns a recommendation movie
    ResponseEntity<Movies> getRecommendation() {

        Movies recommendation = recommendationService.getRecommendation().getBody();

        return ResponseEntity.ok(recommendation);

    }
}
