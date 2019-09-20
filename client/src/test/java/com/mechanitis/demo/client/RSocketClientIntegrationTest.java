package com.mechanitis.demo.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ClientAutoConfiguration.class, RSocketStrategiesAutoConfiguration.class})
public class RSocketClientIntegrationTest {

    @Autowired
    private RSocketRequester rSocketRequester;

    @Test
    public void shouldConnectToAnRSocketBackEnd() {

        new RSocketClient(rSocketRequester).pricesFor("RSOCKET_TEST")
                                           .take(10)
                                           .log()
                                           .blockLast();
    }

//    @Configuration
//    public static class ClientConfiguration {
//
//        @Bean
//        @Lazy
//        public RSocket rSocket() {
//            return RSocketFactory.connect()
//                                 .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
//                                 .frameDecoder(PayloadDecoder.ZERO_COPY)
//                                 .transport(TcpClientTransport.create(7000))
//                                 .start()
//                                 .block();
//        }
//
//        @Bean
//        @Lazy
//        RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
//            return RSocketRequester.wrap(rSocket(), APPLICATION_JSON, APPLICATION_JSON, rSocketStrategies);
//        }
//    }
}
