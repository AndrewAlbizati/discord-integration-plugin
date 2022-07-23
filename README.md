# Discord Integration Plugin
This plugin integrates Discord servers with Minecraft servers, and creates a bidirectional chat system between the two.

## How to Use
1. Run the following commands in a command prompt or terminal
    1. If on Windows:
        1. `gradlew clean`
        2. `gradlew build`

    2. If on macOS/Linux
        1. `chmod +x gradlew`
        2. `./gradlew clean`
        3. `./gradlew build`
2. The JAR file for the plugin will be located in `build\libs\`. Move the JAR file into the plugins folder.
3. Create a file titled `discord-integration-config.properties` in the same folder as the server's JAR file.
4. Add the following to the file:
```
# Discord Integration Plugin Properties
token={INSERT DISCORD BOT TOKEN HERE}
channel-id={INSERT MINECRAFT DISCORD CHANNEL ID HERE}
minecraft-server-name={INSERT PREFERRED MINECRAFT SERVER NAME HERE}
```
4. Start the server, the bot will run automatically upon startup.

## Dependencies
- Spigot (https://www.spigotmc.org/)
- Javacord 3.5.0 (https://github.com/Javacord/Javacord)