package org.cryptical.cbot.commands.perms.users;

import java.awt.Color;

import org.cryptical.cbot.utils.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms_Users_Info {
	
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (args < 4) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		
		String id = Utils.toId(msg[3]);
		Member user = event.getGuild().getMemberById(id);
		
		String roles = "";
		String nick = "None";
		for (Role r : user.getRoles()) {
			roles += ", " + r.getName();
		} roles = roles.replaceFirst(",", "");
		if (user.getNickname() != null) nick = user.getNickname();
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.ORANGE).setTitle(user.getUser().getName() + "'s info")
				.addField("id", user.getUser().getId(), false)
				.addField("roles", roles, false)
				.addField("nickname", nick, false)
				;
		event.getChannel().sendMessage(builder.build()).queue();
	}
}
