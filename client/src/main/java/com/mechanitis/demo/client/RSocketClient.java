package com.mechanitis.demo.client;

import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

public class RSocketClient {
    private RSocketRequester rSocketRequester;

    RSocketClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    Flux<String> pricesFor(String symbol) {
        return rSocketRequester.route("stockPrices")
                               .data(symbol)
                               .retrieveFlux(String.class);
    }
}
