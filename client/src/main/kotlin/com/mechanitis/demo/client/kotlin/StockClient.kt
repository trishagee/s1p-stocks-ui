package com.mechanitis.demo.client.kotlin

import reactor.core.publisher.Flux

interface StockClient {
    fun pricesFor(symbol: String): Flux<StockPrice>
}
