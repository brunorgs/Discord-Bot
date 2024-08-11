package com.discord.bot;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PingCommandHandler implements CommandRunner{

    @Override
    public boolean valid(String command) {
        return "!ping".equalsIgnoreCase(command);
    }

    @Override
    public Mono<Message> execute(Mono<MessageChannel> messageChannel) {
        return messageChannel.flatMap(channel -> channel.createMessage("Pong!"));
    }
}
