package com.example.recommendation;

import org.springframework.stereotype.Service;
/**
 * Author: Roshan Phakami Punmagar
 * 
 * File Name: RecommendationService
 * Date: 16/9/2024
 * Purpose:
 * service for getting recommendations 
 * ******************************************************
 */
@Service
public class RecommendationService {

    private final RecomendationClient recommendationClient;

    public RecommendationService(RecomendationClient recommendationClient) {
        this.recommendationClient = recommendationClient;
    }

    public Movies getRecomendation() {
        return recommendationClient.getRecomendation().getBody();
    }
}
