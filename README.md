# Discord Integration Plugin
This plugin integrates Discord serves with Minecraft servers, and creates a bidirectional chat system between the two.

## How to Use
1. Add `discord-integration-plugin.jar` to the server's plugins folder.
2. Create a file titled `discord-integration-config.properties` in the same folder as the server's JAR file.
3. Add the following to the file:
```
#Discord Integration Plugin Properties
token={INSERT DISCORD BOT TOKEN HERE}
channel-id={INSERT MINECRAFT DISCORD CHANNEL ID HERE}
minecraft-server-name={INSERT PREFERRED MINECRAFT SERVER NAME HERE}
```
4. Start the server, the bot will run automatically upon startup.

## Dependencies
- Spigot (https://www.spigotmc.org/)
- Javacord 3.3.2 (https://github.com/Javacord/Javacord)