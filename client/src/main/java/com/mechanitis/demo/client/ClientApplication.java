package com.mechanitis.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		// Create a stub client and print out the first 10 items generated
		// This blocks so that this service runs until this is completed
		new StubStockClient().pricesFor("FAKE")
							 .take(10)
							 .log()
							 .blockLast();
	}

}
