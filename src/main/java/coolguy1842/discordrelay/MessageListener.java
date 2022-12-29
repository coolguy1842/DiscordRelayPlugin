package coolguy1842.discordrelay;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
        //if(event.getChannel().getId() != Globals.config.getString("channelid")) return;
        if(member == null) return;
        else if(member.getUser().isBot()) return;

        Role role = null;
        if(member.getRoles().size() > 0) role = member.getRoles().get(0);

        Component roleMessage = Component.empty();

        if(role != null) {
            roleMessage = Component.text("[").color(TextColor.color(150, 150, 150))
                                    .append(Component.text(role.getName()).color(TextColor.color(role.getColorRaw())))
                                    .append(Component.text("] "));
        }

        Bukkit.broadcast(roleMessage.append(Component.text(member.getEffectiveName() + ": " + event.getMessage().getContentDisplay()).color(TextColor.color(255, 255, 255))));
    }

    @EventHandler
    private void onSendToDiscord(SendToDiscordEvent e) {
        DiscordUtil.sendMessage(Globals.webhook, e.getContents(), e.getUsername(), e.getAvatar());
    }
}