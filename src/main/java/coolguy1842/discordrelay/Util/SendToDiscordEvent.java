package coolguy1842.discordrelay.Util;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import coolguy1842.discordrelay.Globals;

public class SendToDiscordEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final String username;
    private final String avatar;
    private final String contents;
    private final String webhook;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public SendToDiscordEvent(String username, String avatar, String contents, String webhook) {
        super(true);
        
        this.username = username;
        this.avatar = avatar;
        
        this.contents = contents;

        this.webhook = webhook;
    }
    
    public SendToDiscordEvent(String username, String avatar, String contents) {
        super(true);
        
        this.username = username;
        this.avatar = avatar;
        
        this.contents = contents;
        
        this.webhook = Globals.config.getString("webhookurl");
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatar() {
        return this.avatar;
    }
    
    public String getContents() {
        return this.contents;
    }
    
    public String getWebhook() {
        return this.webhook;
    }
}