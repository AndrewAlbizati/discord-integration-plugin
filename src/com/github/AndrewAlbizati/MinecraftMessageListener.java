package com.github.AndrewAlbizati;

import org.bukkit.Bukkit;
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
        eb.setTitle("Message in " + Bukkit.getServer().getName());
        eb.setColor(minecraftEmbedColor);
        //eb.setThumbnail(Bukkit.getServerIcon());
        eb.setDescription("**<" + username + ">** " + event.getMessage());

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerJoinMessage(PlayerJoinEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Message in " + Bukkit.getServer().getName());
        eb.setColor(minecraftEmbedColor);
        //eb.setThumbnail(Bukkit.getServerIcon());
        eb.setDescription( "**" + username + "** joined the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerQuitMessage(PlayerQuitEvent event) {
        String username = event.getPlayer().getName();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Message in " + Bukkit.getServer().getName());
        eb.setColor(minecraftEmbedColor);
        //eb.setThumbnail(Bukkit.getServerIcon());
        eb.setDescription("**" + username + "** left the game");

        bot.getMessageChannel().sendMessage(eb);
    }

    @EventHandler
    public void PlayerDeathMessage(PlayerDeathEvent event) {
        String deathMessage = event.getDeathMessage();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Message in " + Bukkit.getServer().getName());
        eb.setColor(minecraftEmbedColor);
        //eb.setThumbnail(Bukkit.getServerIcon());
        eb.setDescription(deathMessage);

        bot.getMessageChannel().sendMessage(eb);
    }
}
