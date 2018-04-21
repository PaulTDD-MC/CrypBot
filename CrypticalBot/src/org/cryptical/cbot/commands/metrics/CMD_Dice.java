package org.cryptical.cbot.commands.metrics;

import java.util.Random;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Dice {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "dice"))) return;

    	int amount = 1;
    	if (args > 1) {
    		try { amount = Integer.valueOf(msg[1]);
    		} catch (NumberFormatException e) {
        		event.getChannel().sendMessage(msg[1] + " is not a valid number").queue();
        		return; }
    	}
    	
		Random random = new Random();
		int num =  random.nextInt(( (amount * 6) -1) + 1) + 1;
		event.getChannel().sendMessage(event.getAuthor().getAsMention() + " rolled " + amount + " dice with value: " + num).queue();
	}
}
