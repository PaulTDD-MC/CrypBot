package org.cryptical.cbot.commands.perms.role;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms_Role_Update {

	// /perm role update <roleId> [c:color] [m:mentionable] [p:permission;t/f] [h:hoisted]
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args < 4) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		if (args < 5) {
			event.getChannel().sendMessage("Add a argument to update").queue();
			return;
		}
		
		Role role = event.getGuild().getRoleById(msg[3]);
		if (role == null) {
			event.getChannel().sendMessage("No role found with the id " + msg[3]).queue();
			return;
		}
		
		Color color = role.getColor();
		boolean mentionable = role.isMentionable();
		boolean hoisted = role.isHoisted();
		List<Permission> perms = new ArrayList<>();
		for (Permission p : role.getPermissions()) perms.add(p);
		
		String c = "";
		String m = ""+mentionable;
		String p = "";
		String h = ""+hoisted;
		for (String s : message.split(" ")) {
			if (s.contains("c:"))
				c = s.split(":")[1];
			if (s.contains("m:"))
				m = s.split(":")[1];
			if (s.contains("p:"))
				p = s.split(":")[1];
			if (s.contains("h:"))
				h = s.split(":")[1];
		}
		if (!(c.equals("")) && Utils.toColor(c) == null) { event.getChannel().sendMessage(c + " is not a valid color").queue(); return; }
		if (!(p.equals(""))) { 
			try { Permission.valueOf(p.split(";")[0]);
			} catch (Exception e) {
			event.getChannel().sendMessage(p.split(";")[0] + " is not a valid permission").queue(); return; }
		}
		
		color = Utils.toColor(c);
		mentionable = Boolean.valueOf(m);
		hoisted = Boolean.valueOf(h);
		Permission perm = Permission.valueOf(p.split(";")[0]);
		boolean add = Boolean.valueOf(p.split(";")[1]);
		
		if (add) {
			if (perms.isEmpty() || !perms.contains(perm)) { perms.add(perm); }
		} if (!add) {
			if (perms.contains(perm)) { perms.remove(perm); }
		}
		
		role.getManager().setColor(color).queue();
		role.getManager().setMentionable(mentionable).queue();
		role.getManager().setPermissions(perms).queue();
		role.getManager().setHoisted(hoisted).queue();
		
		event.getChannel().sendMessage("You have updated role " + role.getName()).queue();
	}
}