package com.github.AndrewAlbizati;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Plugin extends JavaPlugin {
    private Bot bot;

    @Override
    public void onEnable() {
        try {
            File config = new File("discord-integration-config.properties");
            if (config.createNewFile()) {
                System.out.println(config.getName() + " created");
            }

            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream("discord-integration-config.properties");
            prop.load(ip);

            String token = prop.getProperty("token");
            long channelID = Long.parseLong(prop.getProperty("channel-id"));

            this.bot = new Bot(token, channelID);

            ip.close();

            getServer().getPluginManager().registerEvents(new MinecraftMessageListener(bot), this);

            bot.getMessageChannel().sendMessage("Server has started.");
        } catch (Exception e) {
            // Bot failed to start
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            bot.getApi().disconnect();
        } catch (NullPointerException e) {
            // Bot failed to start
        }
    }
}
