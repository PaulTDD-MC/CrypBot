package org.cryptical.cbot.commands.metrics;

import java.util.Random;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Fact {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "fact"))) return;

    	Random random = new Random();
    	int num = random.nextInt( (Constants.facts.size()-1));
    	String fact = Constants.facts.get(num);
    	
    	event.getChannel().sendMessage("Random fact: **" + fact + "**").queue();	
	}
}
