package org.cryptical.cbot.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;

public class ServerData {
	
	private HashMap<Member, Date> lastSeen = new HashMap<>();
	
	public void addLastSeen(Member member, Date date) {
		if (!lastSeen.containsKey(member)) lastSeen.put(member, date);
	}
	
	public void removeLastSeen(Member member) {
		if (lastSeen.containsKey(member)) lastSeen.remove(member);
	}

	public List<Member> getLast(int minutes) {
		Date target = DateUtils.add(new Date(), 0, 0, 0, 0, -minutes);
		List<Member> members = new ArrayList<>();
		for (Member m : lastSeen.keySet()) {
			Date d = lastSeen.get(m);
			if (d.before(target)) continue;
			members.add(m);
		}
		return members;
	}
	
	public HashMap<Member, Date> lastSeenList() {
		return lastSeen;
	}
}
