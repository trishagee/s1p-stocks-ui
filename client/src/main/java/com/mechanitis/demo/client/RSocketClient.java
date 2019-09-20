package com.mechanitis.demo.client;

import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

public class RSocketClient implements StockClient {
    private RSocketRequester rSocketRequester;

    RSocketClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        return rSocketRequester.route("stockPrices")
                               .data(symbol)
                               .retrieveFlux(StockPrice.class);
    }
}
