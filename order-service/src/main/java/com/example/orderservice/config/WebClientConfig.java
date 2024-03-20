package com.example.orderservice.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    // create WebClient instances that are capable of load balancing requests across multiple instances of inventory-service.
   //allowing your application to communicate with multiple instances of a service in a load-balanced manner.
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder()
    {
        return WebClient.builder();
    }
}
