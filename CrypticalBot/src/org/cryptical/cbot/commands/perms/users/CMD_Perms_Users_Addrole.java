package org.cryptical.cbot.commands.perms.users;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms_Users_Addrole {
	
	// /perms users addrole <mention> <roleId>
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args < 5) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		
		String uid = Utils.toId(msg[3]);
		String roleid = msg[4];
		
		Member member = event.getGuild().getMemberById(uid);
		if (member == null) {
			event.getChannel().sendMessage("Member not valid").queue();
			return;
		}
		Role role = event.getGuild().getRoleById(roleid);
		if (role == null) {
			event.getChannel().sendMessage("Role with id " +roleid + " not valid").queue();
			return;
		}
		
		event.getGuild().getController().addRolesToMember(member, role).queue();
		event.getChannel().sendMessage("Added role to user").queue();
	}
}
