package com.mechanitis.demo.client;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;


public class StubStockClient {
    public Flux<Double> pricesFor(String ticker) {
        final Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1L))
                   .map(aLong -> random.nextDouble() * 100);
    }
}
