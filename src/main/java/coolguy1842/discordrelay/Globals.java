package coolguy1842.discordrelay;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import club.minnced.discord.webhook.WebhookClient;
import net.dv8tion.jda.api.JDA;

public class Globals {
    public static JavaPlugin plugin;
    public static JDA jda;
    public static FileConfiguration config;
    
    public static String webhookURL;
    public static WebhookClient webhook;
}
