package com.discord.bot;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Mono;

public interface CommandRunner {

    boolean valid(String command);

    Mono<Message> execute(Mono<MessageChannel> messageChannel);
}
