package com.mechanitis.demo.client;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

import static java.time.LocalDateTime.now;

@Log4j2
public class StubStockClient implements StockClient {
    public Flux<StockPrice> pricesFor(String symbol) {
        final Random random = new Random();
        log.info("StubStockClient");
        return Flux.interval(Duration.ofSeconds(1L))
                   .map(aLong -> new StockPrice(symbol, random.nextDouble() * 100, now()));
    }
}
