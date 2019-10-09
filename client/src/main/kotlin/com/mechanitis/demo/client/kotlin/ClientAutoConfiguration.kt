package com.mechanitis.demo.client.kotlin

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Profile
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    internal fun webClient(): WebClient {
        return WebClient.builder().build()
    }

    @Bean
    @Profile("rsocket")
    internal fun rSocketStockClient(rSocketRequester: RSocketRequester): StockClient {
        return RSocketClient(rSocketRequester)
    }

    @Bean
    @Profile("sse")
    internal fun webStockClient(webClient: WebClient): StockClient {
        return WebClientStockClient(webClient)
    }

    @Bean
    @Lazy
    internal fun rSocketRequester(builder: RSocketRequester.Builder): RSocketRequester? {
        return builder.connectTcp("localhost", 7000)
            .block()
    }

}
