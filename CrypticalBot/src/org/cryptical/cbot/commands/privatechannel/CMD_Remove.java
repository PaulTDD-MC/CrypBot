package org.cryptical.cbot.commands.privatechannel;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Remove {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "remove"))) return;

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
    	vc.getPermissionOverride(m).delete().queue();
    	if (vc.getMembers().contains(m)) {
    		vc.getGuild().getController().moveVoiceMember(m, vc.getGuild().getVoiceChannelById(Constants.generalVoiceId)).queue();
    	}

    	Core.utils.sendMessage(event.getAuthor(), "You have removed " + m.getAsMention() + " to your channel");
    	Core.utils.sendMessage(m.getUser(), "You have been removed from " + event.getMember().getAsMention() + "'s channel");
    }
}
