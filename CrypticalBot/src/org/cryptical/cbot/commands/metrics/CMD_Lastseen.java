package org.cryptical.cbot.commands.metrics;

import java.awt.Color;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;
import org.cryptical.cbot.utils.DateUtils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Lastseen {
	
	// /lastseen <minutes>
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!message.startsWith(Constants.botPrefix + "lastseen")) return;
		
		if (args != 2) {
			event.getChannel().sendMessage("Not enough arguments").queue();
			return;
		}
		
		int minutes = 60;
		try {
			minutes = Integer.valueOf(msg[1]);
		} catch (Exception e) {
			event.getChannel().sendMessage(msg[1] + " is not a valid number").queue();
			return;
		}
		
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.CYAN).setTitle("Players who have been online in the last " + minutes + " minutes are: ");
		for (Member m : Core.sdata.getLast(minutes)) {
			builder.addField("", m.getUser().getName() + " (" + DateUtils.format(Core.sdata.lastSeenList().get(m)) + ")", false);
		}
		event.getChannel().sendMessage(builder.build()).queue();
	}
}
