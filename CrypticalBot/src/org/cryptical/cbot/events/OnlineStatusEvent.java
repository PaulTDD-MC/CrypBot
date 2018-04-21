package org.cryptical.cbot.events;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class OnlineStatusEvent extends ListenerAdapter {
	
	private List<Member> offlineMembers = new ArrayList<>();
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
		Member member = event.getMember();
		OnlineStatus status = event.getCurrentOnlineStatus();

		if (status == OnlineStatus.OFFLINE) {
			if (!offlineMembers.contains(member)) offlineMembers.add(member);
			Core.sdata.addLastSeen(member, new Date());
		}
		else {
			Core.sdata.removeLastSeen(member);
			if (!offlineMembers.contains(member) || Core.mentions.getMentions(member) == null) return;
			offlineMembers.remove(member);
			TextChannel chan = event.getGuild().getTextChannelById(Constants.botChannelId);
			
			String times = (Core.mentions.getMentions(member).size() > 1) ? "times" : "time";
			EmbedBuilder message = new EmbedBuilder().setColor(Color.CYAN).setTitle("**" + event.getUser().getName() + ", while you were offline you were mentioned " + Core.mentions.getMentions(member).size() + times + "**");
			for (Message m : Core.mentions.getMentions(member)) {
				message.addBlankField(false);
				message.addField(m.getAuthor().getName(), m.getContentRaw(), false);
			}

			
			if (!Core.mentions.getMentions(member).isEmpty()) {
				Message one = chan.sendMessage(event.getMember().getAsMention()).complete();
				Message m = chan.sendMessage(message.build()).complete();
				one.delete().queueAfter(30, TimeUnit.SECONDS);
				m.delete().queueAfter(30, TimeUnit.SECONDS);
			}
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		
		Member member = null;
		
		if (message.contains("@")) {
			for (Member m : offlineMembers) {
				if (message.contains(m.getAsMention())) {
					member = m;
				}
			}
		} 
		if (member == null) return;
		Core.mentions.addMention(member, event.getMessage());
	}
}
