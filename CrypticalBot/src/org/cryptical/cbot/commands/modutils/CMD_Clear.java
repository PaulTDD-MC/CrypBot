package org.cryptical.cbot.commands.modutils;

import java.util.concurrent.TimeUnit;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory.MessageRetrieveAction;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Clear {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "clear"))) return;
		
		if (args < 2) {
    		event.getChannel().sendMessage("Please specify the number of messages to delete").queue();
    		return;
    	}
    	int size = 0;
    	try { size = Integer.valueOf(msg[1]);
    	} catch (NumberFormatException e) {
    		event.getChannel().sendMessage(msg[1] + " is not a valid number").queue();
    		return; }
    	if (size < 2) {
    		event.getChannel().sendMessage("You must clear more than 2 messages").queue();
    		return;
    	}
    	if (size > 100) {
    		event.getChannel().sendMessage("You can not clear more than 100 messages").queue();
    		return;
    	}
    	
    	MessageRetrieveAction list = event.getTextChannel().getHistoryBefore(event.getMessage(), size);
    	if (list.complete().size() < 3) return;
    	event.getTextChannel().deleteMessages(list.complete().getRetrievedHistory()).queue();
    	event.getMessage().delete().queue();
    	Message last = event.getChannel().sendMessage("Deleted " + size + " messages").complete();
    	last.delete().queueAfter(5, TimeUnit.SECONDS);		
	}
}
