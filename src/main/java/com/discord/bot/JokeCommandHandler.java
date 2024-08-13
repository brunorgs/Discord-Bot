package com.discord.bot;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JokeCommandHandler implements CommandRunner{

    @Override
    public boolean valid(String command) {
        return "!joke".equalsIgnoreCase(command);
    }

    @Override
    public Mono<Message> execute(Mono<MessageChannel> messageChannel) {
        return WebClient.create("https://icanhazdadjoke.com/")
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(JokeResponse.class))
                .flatMap(jokeResponse -> messageChannel.flatMap(channel -> channel.createMessage(jokeResponse.joke())));
    }
}
