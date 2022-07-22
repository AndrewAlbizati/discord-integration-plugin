package com.github.AndrewAlbizati;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class Plugin extends JavaPlugin {
    private Bot bot;

    @Override
    public void onEnable() {
        try {
            // Load config from discord-integration-config.properties in the server folder
            File config = new File("discord-integration-config.properties");
            if (config.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(config));
                writer.write("#Discord Integration Plugin Properties\ntoken=\nchannel-id=\nminecraft-server-name=");
                writer.close();

                System.out.println(config.getName() + " created");
                throw new NullPointerException("Please input your bot's token and the server's message channel id in discord-integration-config.properties");
            }

            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream("discord-integration-config.properties");
            prop.load(ip);
            ip.close();


            String token = prop.getProperty("token");
            long channelID = Long.parseLong(prop.getProperty("channel-id"));
            String minecraftServerName = prop.getProperty("minecraft-server-name");

            this.bot = new Bot(token, channelID, minecraftServerName);


            getServer().getPluginManager().registerEvents(new MinecraftMessageListener(bot), this);

            // Server start message
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(bot.getMinecraftServerName());
            eb.setColor(bot.minecraftEmbedColor);
            eb.setDescription("Server has started");

            bot.getMessageChannel().sendMessage(eb);
            changeStatus();
        } catch (Exception e) {
            // Bot failed to start
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(bot.getMinecraftServerName());
            eb.setColor(bot.minecraftEmbedColor);
            eb.setDescription("Server has stopped");

            bot.getMessageChannel().sendMessage(eb).join();

            bot.getApi().disconnect();
        } catch (NullPointerException e) {
            // Bot failed to start
        }
    }

    public void changeStatus() {
        AtomicInteger playerCount = new AtomicInteger(-1); // Impossible for the player count to be negative
        new BukkitRunnable() {
            public void run() {
                // Status only gets updates when the player count changes
                if (playerCount.get() != Bukkit.getServer().getOnlinePlayers().size()) {
                    playerCount.set(Bukkit.getServer().getOnlinePlayers().size());
                    bot.getApi().updateActivity(ActivityType.WATCHING,  + playerCount.get() + " player" + (playerCount.get() == 1 ? "" : "s") + " online");
                }
            }
        }.runTaskTimerAsynchronously(this, 0, 1200); // Run once every two minutes (60 seconds * 20 ticks)
    }
}