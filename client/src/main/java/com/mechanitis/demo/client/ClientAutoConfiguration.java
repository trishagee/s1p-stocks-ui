package com.mechanitis.demo.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
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
    @Profile("rsocket")
    StockClient rSocketStockClient(RSocketRequester rSocketRequester) {
        return new RSocketClient(rSocketRequester);
    }

    @Bean
    @Profile("sse")
    StockClient webStockClient(WebClient webClient) {
        return new WebClientStockClient(webClient);
    }

    @Bean
    @Lazy
    RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 7000)
                      .block();
    }

}
