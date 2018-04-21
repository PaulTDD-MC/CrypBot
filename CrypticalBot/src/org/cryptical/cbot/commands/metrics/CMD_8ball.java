package org.cryptical.cbot.commands.metrics;

import java.util.Random;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_8ball {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "8ball"))) return;

    	if (args == 1) {
    		event.getChannel().sendMessage("You should ask me a question").queue();
    		return;
    	}
    	Random random = new Random();
    	int num = random.nextInt( (Constants.ball.size()-1));
    	String result = Constants.ball.get(num);
    	
    	event.getChannel().sendMessage(result).queue();
    }
}
