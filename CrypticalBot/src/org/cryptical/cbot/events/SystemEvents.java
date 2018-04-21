package org.cryptical.cbot.events;

import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SystemEvents extends ListenerAdapter {

	@Override
	public void onReady(ReadyEvent event) {
		TextChannel bot = event.getJDA().getTextChannelById(Constants.botChannelId);
		if (Constants.statusMessages)
			bot.sendMessage(":white_check_mark: I'm ready for usage!").queue();
	}
	
	@Override
	public void onReconnect(ReconnectedEvent event) {
		TextChannel bot = event.getJDA().getTextChannelById(Constants.botChannelId);
		if (Constants.statusMessages)
			bot.sendMessage(":white_check_mark: I'm ready for usage!").queue();
	}
}
