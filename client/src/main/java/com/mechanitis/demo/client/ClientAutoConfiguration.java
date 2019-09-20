package com.mechanitis.demo.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    StockClient stockClient() {
        return new StubStockClient();
    }

    @Bean
    RSocketRequester rSocketRequester() {
        return RSocketRequester.builder()
                               .connectTcp("localhost", 7000)
                               .block();
    }

}
