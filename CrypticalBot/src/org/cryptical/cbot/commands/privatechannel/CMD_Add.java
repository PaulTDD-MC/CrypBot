package org.cryptical.cbot.commands.privatechannel;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Add {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "add"))) return;

    	if (args < 2) {
    		event.getChannel().sendMessage("Please specify a user to add").queue();
    		return;
    	}
    	VoiceChannel vc = Core.utils.getChannelByUser(event.getAuthor());
    	if (vc == null) {
    		event.getChannel().sendMessage("You don't have a channel, use /private").queue();
    		return;
    	}
    	
    	Member m = event.getGuild().getMemberById(msg[1].replace("@", "").replace("<", "").replace(">", ""));
    	vc.createPermissionOverride(m).setAllow(Permission.VOICE_CONNECT).queue();
    	
    	Core.utils.sendMessage(event.getAuthor(), "You have added " + m.getAsMention() + " to your channel");
    	Core.utils.sendMessage(m.getUser(), "You have been added to " + event.getMember().getAsMention() + "'s channel");
    }
}
