package coolguy1842.discordrelay;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import club.minnced.discord.webhook.WebhookClient;
import coolguy1842.discordrelay.Util.DiscordUtil;
import coolguy1842.discordrelay.Util.SendToDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MessageListener extends ListenerAdapter implements Listener {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {        
        if(!event.isFromType(ChannelType.TEXT)) return;
        Member member = event.getMember();

        if(member == null) return;
        else if(member.getUser().isBot()) return;
        
        if(!event.getChannel().getId().equals(Globals.config.getString("channelid"))) return;

        Role role = null;
        if(member.getRoles().size() > 0) role = member.getRoles().get(0);

        Component roleMessage = Component.empty();

        if(role != null) {
            roleMessage = Component.text("[").color(TextColor.color(150, 150, 150))
                                    .append(Component.text(role.getName()).color(TextColor.color(role.getColorRaw())))
                                    .append(Component.text("] "));
        }

        String username = member.getNickname();
        if(username == null || username.length() <= 0) username = member.getEffectiveName();

        Bukkit.broadcast(roleMessage.append(Component.text(username + ": " + event.getMessage().getContentDisplay()).color(TextColor.color(255, 255, 255))));
    }

    @EventHandler
    private void onSendToDiscord(SendToDiscordEvent e) {
        if(!e.getWebhook().equals(Globals.webhookURL)) {
            WebhookClient webhook = WebhookClient.withUrl(e.getWebhook());
            DiscordUtil.sendMessage(webhook, e.getContents(), e.getUsername(), e.getAvatar());

            webhook.close();

            return;
        }

        DiscordUtil.sendMessage(Globals.webhook, e.getContents(), e.getUsername(), e.getAvatar());
    }
}