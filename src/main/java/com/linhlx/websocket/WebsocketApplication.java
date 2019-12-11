package com.linhlx.websocket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class WebsocketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("laxuanlinh1", "laxuanlinh2", "laxuanlinh3")
				.map(s->new User(UUID.randomUUID(), s));
	}
}
