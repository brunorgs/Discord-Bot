package com.discord.bot;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.gateway.intent.Intent;
import discord4j.gateway.intent.IntentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BotApplication implements CommandLineRunner {

	private final CommandHandler commandHandler;

    public BotApplication(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DiscordClient client = DiscordClient.create(System.getenv("DISCORD_TOKEN"));

		client
				.withGateway(c ->
//						c.on(ReadyEvent.class)
//								.subscribe(ready -> System.out.println("Logged in as " + ready.getSelf().getUsername()));

						c.on(MessageCreateEvent.class, event -> {
							Message message = event.getMessage();
							System.out.println(message.getContent());

							return commandHandler.handleCommand(message.getContent(), message.getChannel());
						}))
				.block();

	}
}
