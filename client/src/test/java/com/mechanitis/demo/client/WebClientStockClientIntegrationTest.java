package com.mechanitis.demo.client;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {

    @Test
    void shouldConnectToServiceAndReturnValues() {
        Flux<Double> test = new WebSocketStockClient(WebClient.builder().build()).pricesFor("TEST");

        test.take(10)
            .log()
            .blockLast();
    }
}
