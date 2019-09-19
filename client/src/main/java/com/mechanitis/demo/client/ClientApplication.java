package com.mechanitis.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		StubStockClient client = new StubStockClient();
		Flux<Double> fake = client.pricesFor("FAKE");

		fake.subscribe(System.out::println);
	}

}
