package org.cryptical.cbot.commands.modutils;

import java.awt.Color;

import org.cryptical.cbot.utils.Constants;
import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Broadcast {
	
	// /broadcast <channel;> [e:t/f] <message>
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!message.startsWith(Constants.botPrefix + "broadcast")) return;
		
		String send = "";
		String[] words = message.split(" ");
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.CYAN);
		
		int start = 2;
		boolean e = false;
		for (String s : words) {
			if (s.contains("e:")) {
				e = Boolean.valueOf(s.split(":")[1]);
				start = 3;
			}
		}
		
		for (int i = start; i < words.length; i++)
			send += " " + words[i];
		
		builder.addField("Broadcast", send, false);

		String[] channels = msg[1].split(";");
		for (String s : channels) {
			TextChannel chan = event.getGuild().getTextChannelById(Utils.toId(s));
			if (!e) chan.sendMessage(send).queue();
			if (e) chan.sendMessage(builder.build()).queue();
		}
		
	}

}
