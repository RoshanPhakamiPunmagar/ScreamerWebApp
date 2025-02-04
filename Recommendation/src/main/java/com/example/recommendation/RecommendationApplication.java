package com.example.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * Roshan Phakami Punmagar
 * 
 * File Name: RecommendationApplication.java
 * Date: 16/9/2024
 * Purpose:
 * 
 * ******************************************************
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RecommendationApplication {

    public static void main(String[] args) {
        try{
        //setup disovery client
        SpringApplicationBuilder recommendationService = new SpringApplicationBuilder(RecommendationApplication.class);
        recommendationService.properties("server.port=8333");
        recommendationService.properties("spring.application.name=recommendationService");
        recommendationService.properties("eureka.client.service-url.defaultZone=http://localhost:8761/eureka/");
        recommendationService.properties("eureka.instance.prefer-ip-address=true");
        recommendationService.run(args);
        }catch(Exception  e)
        {
            System.out.println("--------------------------ERROR---------------------------");
            System.out.println("Cannot connect to discovery server");
            System.out.println("--------------------------ERROR---------------------------");
        }
    }

}
