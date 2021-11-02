package com.github.AndrewAlbizati;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class MinecraftMessageListener implements Listener {
    private final Bot bot;
    private final Color minecraftEmbedColor = new Color(71, 122, 30);

    public MinecraftMessageListener(Bot bot) {
        this.bot = bot;
    }

    @EventHandler
    public void PlayerSendMessage(AsyncPlayerChatEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(minecraftEmbedColor);
        eb.setDescription("**<" + username + ">** " + event.getMessage());

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerJoinMessage(PlayerJoinEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(minecraftEmbedColor);
        eb.setDescription( "**" + username + "** joined the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerQuitMessage(PlayerQuitEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(minecraftEmbedColor);
        eb.setDescription("**" + username + "** left the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerDeathMessage(PlayerDeathEvent event) {
        String username = event.getDeathMessage().split(" ")[0];
        String deathMessage = event.getDeathMessage().substring(username.length() + 1);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(bot.getMinecraftServerName());
        eb.setColor(minecraftEmbedColor);
        eb.setDescription("**" + username + "** " + deathMessage);

        bot.getMessageChannel().sendMessage(eb);
    }
}
