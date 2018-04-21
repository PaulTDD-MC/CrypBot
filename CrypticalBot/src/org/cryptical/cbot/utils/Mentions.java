package org.cryptical.cbot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

public class Mentions {
	
	private HashMap<Member, List<Message>> map;
	
	public Mentions() {
		map = new HashMap<>();
	}
	
	public void addMention(Member member, Message mention) {
		if (map.containsKey(member)) {
			List<Message> list = map.get(member);
			list.add(mention);
			map.replace(member, list);
		}
		else {
			List<Message> list = new ArrayList<>();
			list.add(mention);
			map.put(member, list);
		}
	}
	
	public List<Message> getMentions(Member member) {
		return map.get(member);
	}
	
	public void resetMentions(Member member) {
		map.remove(member);
	}
}
