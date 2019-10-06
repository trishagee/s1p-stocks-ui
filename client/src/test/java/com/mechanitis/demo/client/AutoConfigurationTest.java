package com.mechanitis.demo.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration;
import org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

@Disabled("This does not work yet, but I think it's the right direction")
public class AutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ClientAutoConfiguration.class,
                                                     RSocketRequesterAutoConfiguration.class,
                                                     RSocketStrategiesAutoConfiguration.class));

    @Test
    void name() {
        contextRunner.run(context -> {
            StockClient stocksClient = (StockClient) context.getBean("rSocketStocksClient");
            stocksClient.pricesFor("TEST")
                        .take(5)
                        .log()
                        .blockLast();
        });
    }
}
