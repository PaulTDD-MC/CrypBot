package org.cryptical.cbot.commands.privatechannel;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Private {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "private"))) return;
		
    	Category cat = event.getGuild().getCategoryById(Constants.privateCategoryId);
    	Core.utils.createVoiceChannel(event.getAuthor(), cat, (event.getAuthor().getName() + "'s_channel"), 0);
    	
    	Core.utils.sendMessage(event.getAuthor(), "You have created a private channel (" + (event.getAuthor().getName() + "'s_channel") +")");
	}
}
