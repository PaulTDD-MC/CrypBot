package org.cryptical.cbot.commands;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Help {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "help"))) return;

    	EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setTitle("CrypBot help");
    	for (String s : Constants.help) {
    		String[] cmd = s.split(" - ");
    		builder.addField(cmd[0], cmd[1], false);
    	}
    	Message m = event.getChannel().sendMessage(builder.build()).complete();
    	m.delete().queueAfter(30, TimeUnit.SECONDS);
    }
}
