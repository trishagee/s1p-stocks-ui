package com.mechanitis.demo.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientAutoConfiguration.class)
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
}
