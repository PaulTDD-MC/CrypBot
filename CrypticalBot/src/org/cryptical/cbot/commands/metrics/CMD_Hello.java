package org.cryptical.cbot.commands.metrics;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Hello {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "hello"))) return;

    	String reply = "Hello " + event.getAuthor().getAsMention() + ", I'm at your service!";
    	event.getChannel().sendMessage(reply).queue();
	}
}
