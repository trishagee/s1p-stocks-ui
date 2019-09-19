package com.mechanitis.demo.client;

import reactor.core.publisher.Flux;

public interface StockClient {
    Flux<Double> pricesFor(String symbol);
}
