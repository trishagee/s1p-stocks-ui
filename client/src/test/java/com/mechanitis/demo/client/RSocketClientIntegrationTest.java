package com.mechanitis.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class RSocketClientIntegrationTest {

    public static void main(String[] args) {
        SpringApplication.run(RSocketClientIntegrationTest.class, args);
    }
}

@RestController
@RequiredArgsConstructor
class RSocketController {
    private final RSocketRequester rSocketRequester;

    @GetMapping
    Flux<StockPrice> getPrices () {
        return new RSocketClient(rSocketRequester).pricesFor("RSOCKET_TEST")
                                           .take(5)
                                           .log();
    }
}

