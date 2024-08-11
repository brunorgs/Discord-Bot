package com.discord.bot;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.MessageCreateMono;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CommandHandler {

    private final CommandRunner commandRunner;

    public CommandHandler(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

    public Mono<Message> handleCommand(String command, Mono<MessageChannel> messageChannel) {

        if(commandRunner.valid(command)) return commandRunner.execute(messageChannel);

        return MessageCreateMono.empty();
    }
}
