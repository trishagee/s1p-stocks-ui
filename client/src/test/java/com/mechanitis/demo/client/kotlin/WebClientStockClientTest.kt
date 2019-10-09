package com.mechanitis.demo.client.kotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

internal class WebClientStockClientIntegrationTest {

    private val webClient: WebClient = WebClient.builder().build()

    @Test
    fun shouldConnectToServiceAndReturnValues() {
        val symbol = "TEST"
        val prices = WebClientStockClient(webClient).pricesFor(symbol)
            .take(5)
            .log()

        assertEquals(5, prices.count().block())

        val stockPrice = prices.blockLast()
        Assertions.assertNotNull(stockPrice)

        Assertions.assertNotNull(stockPrice!!.price)
        Assertions.assertTrue(stockPrice.price > 0 && stockPrice.price <= 100.0)

        assertEquals(symbol, stockPrice.symbol)
        Assertions.assertNotNull(stockPrice.time)
    }
}
