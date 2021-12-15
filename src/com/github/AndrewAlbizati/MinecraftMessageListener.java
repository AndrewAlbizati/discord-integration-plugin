package com.github.AndrewAlbizati;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class MinecraftMessageListener implements Listener {
    private final Bot bot;

    public MinecraftMessageListener(Bot bot) {
        this.bot = bot;
    }

    @EventHandler
    public void onSendMessage(AsyncPlayerChatEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(bot.minecraftEmbedColor);
        eb.setDescription("**<" + username + ">** " + event.getMessage());

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void onJoinMessage(PlayerJoinEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(bot.minecraftEmbedColor);
        eb.setDescription( "**" + username + "** joined the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void onQuitMessage(PlayerQuitEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(bot.minecraftEmbedColor);
        eb.setDescription("**" + username + "** left the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void onPlayerDeathMessage(PlayerDeathEvent event) {
        String username = event.getDeathMessage().split(" ")[0];
        String deathMessage = event.getDeathMessage().substring(username.length() + 1);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(bot.minecraftEmbedColor);
        eb.setDescription("**" + username + "** " + deathMessage);

        bot.getMessageChannel().sendMessage(eb);
    }
}
