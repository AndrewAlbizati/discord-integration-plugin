package com.github.AndrewAlbizati;

import org.bukkit.Bukkit;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class DiscordMessageListener implements MessageCreateListener {
    private final Bot bot;

    public DiscordMessageListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        ServerTextChannel channel = event.getServerTextChannel().orElse(null);

        if (channel == null)
            return;

        // Messages sent by bots
        if (event.getMessageAuthor().isBotUser())
            return;


        // Messages sent in other channels
        if (channel.getId() != bot.getMessageChannel().getId())
            return;

        // If any message is sent to the MSG_CHANNEL, it will broadcast to the server
        String message = "Discord: <" + event.getMessageAuthor().getName() + "> " + event.getReadableMessageContent();
        Bukkit.broadcastMessage(message);
    }
}