package org.cryptical.cbot.commands.perms.role;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms_Role_Remove {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args != 4) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		
		Role role = event.getGuild().getRoleById(msg[3]);
		if (role == null) { event.getChannel().sendMessage("Role not found with id " + msg[3]).queue(); return; }
		
		role.delete().queue();
		event.getChannel().sendMessage("You have removed role (" + role.getName() + ")").queue();
	}
}
