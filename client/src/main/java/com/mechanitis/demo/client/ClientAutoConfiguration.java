package com.mechanitis.demo.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientAutoConfiguration {
    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
