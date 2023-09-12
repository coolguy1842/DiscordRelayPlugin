package coolguy1842.discordrelay;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import club.minnced.discord.webhook.WebhookClient;
import coolguy1842.discordrelay.Util.DiscordLogging;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public final class DiscordRelay extends JavaPlugin implements Listener {
    MessageListener listener;

    @Override
    public void onEnable() {
        Globals.plugin = this;

        Globals.plugin.saveDefaultConfig();
        Globals.config = Globals.plugin.getConfig();

        Globals.jda = JDABuilder.createDefault(Globals.config.getString("token"))
                        .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS)
                        .build();

        Globals.webhook = WebhookClient.withUrl(Globals.config.getString("webhookurl"));


        DiscordLogging.info("Started");

        listener = new MessageListener();
        registerEvents();
    }

    @Override
    public void onDisable() {
        Globals.jda.removeEventListener(listener);
        if(Globals.jda != null) Globals.jda.shutdownNow();
        
        Globals.webhook.close();
        Globals.webhook = null;

        Globals.config = null;
        Globals.plugin = null;
    }

    void registerEvents() {
        Globals.jda.addEventListener(listener);
        
        getServer().getPluginManager().registerEvents(listener, Globals.plugin);
    }
}
