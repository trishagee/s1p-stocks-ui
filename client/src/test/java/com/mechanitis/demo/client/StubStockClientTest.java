package com.mechanitis.demo.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StubStockClientTest {

    @Test
    void shouldCreateAFluxOfGeneratedRandomNumbers() {
        // OK this is a horrible automated test because it requires you to eyeball it to see if it works
        // but it's better than nothing at this stage and allows me to use the ClientApplication to drive the
        // implementation of other clients

        // Create a stub client and print out the first 10 items generated
        // This blocks so that this service runs until this is completed
        new StubStockClient().pricesFor("FAKE")
                             .take(10)
                             .log()
                             .blockLast();

    }
}