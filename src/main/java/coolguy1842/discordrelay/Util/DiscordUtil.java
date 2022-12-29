package coolguy1842.discordrelay.Util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.AllowedMentions;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class DiscordUtil {
    public static void sendMessage(WebhookClient webhook, String message, String username, String avatar) {
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setAllowedMentions(AllowedMentions.none());

        if(username != null) builder.setUsername(username);
        if(avatar != null) builder.setAvatarUrl(avatar);

        builder.setContent(message);

        webhook.send(builder.build());
    }
}
