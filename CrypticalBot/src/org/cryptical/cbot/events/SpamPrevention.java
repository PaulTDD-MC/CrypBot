package org.cryptical.cbot.events;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.cryptical.cbot.utils.Constants;
import org.cryptical.cbot.utils.DateUtils;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SpamPrevention extends ListenerAdapter {
	
	private HashMap<User, HashMap<Date, Integer>> members = new HashMap<>(); 
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;
		if (event.getMessage().getContentRaw().startsWith(Constants.botPrefix)) return;
		int max = Constants.maxMessagesPerMinute;
		
		User user = event.getAuthor();
		HashMap<Date, Integer> map = members.get(user);
		if (map == null) map = new HashMap<>();
		
		Date date = new Date();
		date = DateUtils.round(date);
		int count = 0;
		if (map.containsKey(date)) count = map.get(date);
		count++;
		
		if (count > max) {
			event.getMessage().delete().queue();
			Message m = event.getChannel().sendMessage(event.getAuthor().getAsMention() + " you are not allowed to send another message, since you are sending to much messages per minute").complete();
			m.delete().queueAfter(10, TimeUnit.SECONDS);
		}
		
		if (map.containsKey(date))
			map.replace(date, count);
		if (!map.containsKey(date))
			map.put(date, count);
		if (members.containsKey(user))
			members.replace(user, map);
		if (!members.containsKey(user))
			members.put(user, map);
	}
}
