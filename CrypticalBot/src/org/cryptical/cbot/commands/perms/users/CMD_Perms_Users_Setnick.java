package org.cryptical.cbot.commands.perms.users;

import java.util.concurrent.TimeUnit;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.HierarchyException;

public class CMD_Perms_Users_Setnick {
	
	// /perms users setnick <mention> <nickname>
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args < 5) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		
		String uid = Utils.toId(msg[3]);
		Member member = event.getGuild().getMemberById(uid);
		if (member == null) {
			event.getChannel().sendMessage("Member not valid").queue();
			return;
		}
		
		String nickname = msg[4];
		try {
			member.getGuild().getController().setNickname(member, nickname).queue();
		} catch (HierarchyException e) {
			Message m = event.getChannel().sendMessage("You cannot change this member's nickname").complete();
			m.delete().queueAfter(20, TimeUnit.SECONDS);
		}
		Message m = event.getChannel().sendMessage(member.getUser().getName() + "'s nickname has been updated to " + nickname).complete();
		m.delete().queueAfter(20, TimeUnit.SECONDS);
	}
}
