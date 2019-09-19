package com.mechanitis.demo.client;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

// this is basically a singleton, so I'm making it an interface with a single static method
// maybe it could be a default method. For the current use cases it doesn't matter.
public interface StubStockClient {
    static Flux<Double> pricesFor(String ticker) {
        final Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1L))
                   .map(aLong -> random.nextDouble() * 100);
    }
}
