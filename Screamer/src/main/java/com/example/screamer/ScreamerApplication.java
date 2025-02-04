package com.example.screamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: ServerApplication.java
 * Date :16/9/2024
 * Purpose :
 * Runs ServerApplicaiton
 * ******************************************************
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ScreamerApplication {

    public static void main(String[] args) {
        try{
        SpringApplicationBuilder screamerService = new SpringApplicationBuilder(ScreamerApplication.class);
        screamerService.properties("server.port=8888");
        screamerService.properties("spring.application.name=screamerService");
        screamerService.properties("eureka.client.service-url.defaultZone=http://localhost:8761/eureka/");
        screamerService.properties("eureka.instance.prefer-ip-address=true");
        screamerService.run(args);
        }catch(Exception  e)
        {
            System.out.println("--------------------------ERROR---------------------------");
            System.out.println("Cannot connect to discovery server");
            System.out.println("--------------------------ERROR---------------------------");
        }
    }

}
