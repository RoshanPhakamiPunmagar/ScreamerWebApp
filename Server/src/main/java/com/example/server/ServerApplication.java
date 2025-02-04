package com.example.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder eureka = new SpringApplicationBuilder(ServerApplication.class);
        eureka.properties("server.port=8761");
        eureka.properties("eureka.client.register-with-eureka=false");
        eureka.properties("eureka.client.fetch-registry=false");
        eureka.run(args);
    }

}
