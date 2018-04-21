package org.cryptical.cbot.commands.perms.role;

import java.awt.Color;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.restaction.RoleAction;

public class CMD_Perms_Role_Create {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args < 4) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		Color color = Color.GRAY;
		boolean mentionable = false;
		boolean hoisted = false;
		if (args > 4) {
			String c = "";
			String m = "false";
			String h = "false";
			for (String s : message.split(" ")) {
				if (s.contains("c:"))
					c = s.split(":")[1];
				if (s.contains("m:"))
					m = s.split(":")[1];
				if (s.contains("h:"))
					h = s.split(":")[1];
			}
			
			color = Utils.toColor(c);
			if (color == null) {
				event.getChannel().sendMessage("Not a valid color").queue();
				return;
			}
			mentionable = Boolean.valueOf(m);
			hoisted = Boolean.valueOf(h);
		}
		if (color == null) {
			event.getChannel().sendMessage("Color is not valid").queue();
			return;
		}
		RoleAction role = event.getGuild().getController().createRole();
		role.setColor(color);
		role.setName(msg[3]);
		role.setMentionable(mentionable);
		role.setHoisted(hoisted);
		role.queue();
		
		EmbedBuilder builder = new EmbedBuilder().setColor(color)
				.addField("Name", msg[3], false)
				.addField("Color", Utils.fromColor(color), false)
				.addField("Mentionable", mentionable+"", false)
				.addField("Hoisted", hoisted+"", false)
				;
		event.getChannel().sendMessage(builder.build()).queue();
	}
	
}
