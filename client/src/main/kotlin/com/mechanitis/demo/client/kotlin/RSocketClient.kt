package com.mechanitis.demo.client.kotlin

import lombok.extern.log4j.Log4j2
import org.springframework.messaging.rsocket.RSocketRequester
import reactor.core.publisher.Flux

@Log4j2
class RSocketClient internal constructor(private val rSocketRequester: RSocketRequester) : StockClient {

    override fun pricesFor(symbol: String): Flux<StockPrice> {
        return rSocketRequester
            .route("stockPrices")
            .data(symbol)
            .retrieveFlux(StockPrice::class.java)
    }
}
