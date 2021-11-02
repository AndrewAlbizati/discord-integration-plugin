package com.github.AndrewAlbizati;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;

public class Bot {
    private final DiscordApi api;
    public DiscordApi getApi() {
        return api;
    }

    private final ServerTextChannel messageChannel;
    public ServerTextChannel getMessageChannel() {
        return messageChannel;
    }

    private final String minecraftServerName;
    public String getMinecraftServerName() {
        return minecraftServerName;
    }

    public Bot (final String token, final long messageChannelId, final String minecraftServerName) {
        this.minecraftServerName = minecraftServerName;

        api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in as " + api.getYourself().getDiscriminatedName());

        this.messageChannel = api.getServerTextChannelById(messageChannelId).orElse(null);

        if (messageChannel == null)
            throw new NullPointerException("Message channel not found");

        api.addMessageCreateListener(new DiscordMessageListener(this));
    }
}
