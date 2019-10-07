package com.mechanitis.demo.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {

    private WebClient webClient;

    @Test
    void shouldConnectToServiceAndReturnValues() {
        webClient = WebClient.builder().build();
        String symbol = "TEST";
        Flux<StockPrice> prices = new WebClientStockClient(webClient).pricesFor(symbol)
                                                                     .take(5)
                                                                     .log();

        Assertions.assertEquals(5, prices.count().block());

        StockPrice stockPrice = prices.blockLast();
        Assertions.assertNotNull(stockPrice);

        Double price = stockPrice.getPrice();
        Assertions.assertNotNull(price);
        Assertions.assertTrue(price > 0 && price <= 100.0);

        Assertions.assertEquals(symbol, stockPrice.getSymbol());
        Assertions.assertNotNull(stockPrice.getTime());
    }
}
