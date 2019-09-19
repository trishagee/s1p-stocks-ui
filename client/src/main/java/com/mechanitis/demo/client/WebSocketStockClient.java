package com.mechanitis.demo.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

@Log4j2
public class WebSocketStockClient implements StockClient{
    private final WebClient webClient;

    WebSocketStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Double> pricesFor(String symbol) {
        return webClient.get()
                        .uri("http://localhost:8080/stocks/{symb}", symbol)
                        .retrieve()
                        .bodyToFlux(Double.class)
                        .retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(5))
                        .doOnError(IOException.class,
                                   e -> log.info(() -> "Closing stream for " + symbol + ". Received " + e.getMessage()));
    }
}
