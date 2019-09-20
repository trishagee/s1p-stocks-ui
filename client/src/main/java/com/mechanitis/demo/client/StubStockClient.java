package com.mechanitis.demo.client;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

import static java.time.LocalDateTime.now;

public class StubStockClient implements StockClient {
    public Flux<StockPrice> pricesFor(String symbol) {
        final Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1L))
                   .map(aLong -> new StockPrice(symbol, random.nextDouble() * 100, now()));
    }
}
