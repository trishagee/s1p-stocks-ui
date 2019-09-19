package com.mechanitis.demo.client;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;


public class StubStockClient {
    public Flux<Double> pricesFor(String ticker) {
        final Random random = new Random();
        return Flux.<Double, Double>generate(() -> 0.0, (state, sink) -> {
            sink.next(state);
            return state + random.nextDouble();
        }).delayElements(Duration.ofSeconds(1L));
    }
}
