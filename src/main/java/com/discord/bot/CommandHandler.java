package com.discord.bot;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.MessageCreateMono;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CommandHandler {

    private final List<CommandRunner> commandRunnerList;

    public CommandHandler(List<CommandRunner> commandRunnerList) {
        this.commandRunnerList = commandRunnerList;
    }

    public Mono<Message> handleCommand(String command, Mono<MessageChannel> messageChannel) {

        for (CommandRunner impl : commandRunnerList) {
            if(impl.valid(command)) return impl.execute(messageChannel);
        }

        return MessageCreateMono.empty();
    }
}
