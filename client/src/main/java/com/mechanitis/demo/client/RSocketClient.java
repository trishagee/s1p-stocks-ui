package com.mechanitis.demo.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

@Log4j2
public class RSocketClient implements StockClient {
    private RSocketRequester rSocketRequester;

    RSocketClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        log.info("RSocketClient");
        return rSocketRequester.route("stockPrices")
                               .data(symbol)
                               .retrieveFlux(StockPrice.class);
    }
}
