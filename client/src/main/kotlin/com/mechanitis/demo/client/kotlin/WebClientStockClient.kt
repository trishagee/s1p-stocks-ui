package com.mechanitis.demo.client.kotlin

import lombok.extern.log4j.Log4j2
import org.apache.commons.logging.LogFactory
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.io.IOException
import java.time.Duration.ofSeconds

@Log4j2
class WebClientStockClient internal constructor(private val webClient: WebClient) : StockClient {
    private val log = LogFactory.getLog(javaClass)

    override fun pricesFor(symbol: String): Flux<StockPrice> {
        log.info("WebClientStockClient")
        return webClient
            .get()
            .uri("http://localhost:8080/stocks/{symb}", symbol)
            .retrieve()
            .bodyToFlux(StockPrice::class.java)
            .retryBackoff(5, ofSeconds(1), ofSeconds(5))
            .doOnError(IOException::class.java) { e -> log.info({ "Closing stream for $symbol. Received ${e.message}" }) }
    }
}
