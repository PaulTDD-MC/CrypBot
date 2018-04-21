package org.cryptical.cbot.commands.perms.role;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms_Role_Show {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args == 3) {
			// show all roles
			EmbedBuilder builder = new EmbedBuilder().setColor(Color.GRAY).setTitle("All roles");
			for (Role r : event.getGuild().getRoles()) {
				String c = (r.getColor() == null) ? "none" : Utils.fromColor(r.getColor());
				builder.addField(r.getName(), "roleId: " + r.getId() + "\nColor: " + c, false);
			}
			event.getChannel().sendMessage(builder.build()).queue();
		}
		else if (args == 4) {
			// show 1 role

			Role role = event.getGuild().getRoleById(msg[3]);
			if (role == null) { event.getChannel().sendMessage("No role found with id " + msg[3]).queue(); return; }
			
			List<String> members = new ArrayList<>();
			for (User u : event.getJDA().getUsers()) {
				Member m = event.getGuild().getMember(u);
				if (m.getRoles().contains(role)) members.add(u.getName());
			}
			
			String mem = (members.isEmpty()) ? "None" : members.toString().replace("[", "").replace("]", "");
			EmbedBuilder builder = new EmbedBuilder().setColor(Color.GRAY).setTitle(role.getName());
			builder.addField("Role id", role.getId(), false);
			builder.addField("Users", mem, false);
			builder.addField("Color", (role.getColor() == null) ? "None" : Utils.fromColor(role.getColor()), false);
			builder.addField("Permissions", role.getPermissions().toString().replace("[", "").replace("]", ""), false);
			event.getChannel().sendMessage(builder.build()).queue();
		}
		else {
			event.getChannel().sendMessage("Not enough arguments!").queue();
		}
	}
}
