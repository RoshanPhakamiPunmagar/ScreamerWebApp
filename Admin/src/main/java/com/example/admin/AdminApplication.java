package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;


/**
 *
 * @author Roshan Phakami PunMagar
 * File Name: AdminApplication.java
 * Date :16/9/2024
 * Purpose : Runs AdminApplicaiton
 * ******************************************************
 */
//Enable Spring Boot application
@SpringBootApplication
// Enables service discovery for this application
@EnableDiscoveryClient
// Enables Feign clients for making RESTFUL HTTP requests to other services
@EnableFeignClients
public class AdminApplication {

    public static void main(String[] args) {

        try{
        SpringApplicationBuilder adminService = new SpringApplicationBuilder(AdminApplication.class);
        adminService.properties("server.port=8222");
        // #comment in application.properties
        adminService.properties("spring.application.name=adminService");
        adminService.properties("eureka.client.service-url.defaultZone=http://localhost:8761/eureka/");
        adminService.properties("eureka.instance.prefer-ip-address=true");

        adminService.run(args);
        }catch(Exception  e)
        {
            System.out.println("--------------------------ERROR---------------------------");
            System.out.println("Cannot connect to discovery server");
            System.out.println("--------------------------ERROR---------------------------");
        }
    }

}
