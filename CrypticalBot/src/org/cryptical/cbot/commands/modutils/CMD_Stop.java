package org.cryptical.cbot.commands.modutils;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Stop {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "stop"))) return;
		
		TextChannel bot = event.getJDA().getTextChannelById(Constants.botChannelId);
		bot.sendMessage(":negative_squared_cross_mark: I just went offline").queue();
		Core.utils.removeAllPChannels();
    	event.getJDA().shutdown();
    }
}
