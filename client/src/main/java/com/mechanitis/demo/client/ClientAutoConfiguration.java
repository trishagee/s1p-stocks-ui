package com.mechanitis.demo.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.MetadataExtractor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
public class ClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    StockClient stockClient() {
        return new StubStockClient();
    }

    @Bean
    RSocket rSocket() {
        return RSocketFactory.connect()
                             .mimeType(MetadataExtractor.ROUTING.toString(), MimeTypeUtils.APPLICATION_JSON_VALUE)
                             .frameDecoder(PayloadDecoder.ZERO_COPY)
                             .transport(TcpClientTransport.create(7000))
                             .start()
                             .block();
    }

    @Bean
    RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
        return RSocketRequester.wrap(rSocket(), APPLICATION_JSON, MetadataExtractor.ROUTING, rSocketStrategies);
    }

//    @Bean
//    RSocketRequester requester(RSocketStrategies strategies) throws URISyntaxException {
//        return RSocketRequester.builder().rsocketStrategies(strategies)
//                               .rsocketFactory(factory -> {
//                                   factory.dataMimeType(MimeTypeUtils.ALL_VALUE)
//                                          .frameDecoder(PayloadDecoder.ZERO_COPY);
//                               })
//                               .connect(TcpClientTransport.create(new InetSocketAddress(
//                                       clientConfigProp.getHost(), clientConfigProp.getPort())))
//                               .retry().block();
//    }

}
