package org.cryptical.cbot.commands.modutils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.cryptical.cbot.commands.perms.role.CMD_Perms_Role_Create;
import org.cryptical.cbot.commands.perms.role.CMD_Perms_Role_Remove;
import org.cryptical.cbot.commands.perms.role.CMD_Perms_Role_Show;
import org.cryptical.cbot.commands.perms.role.CMD_Perms_Role_Update;
import org.cryptical.cbot.commands.perms.users.CMD_Perms_Users_Info;
import org.cryptical.cbot.commands.perms.users.CMD_Perms_Users_Removerole;
import org.cryptical.cbot.commands.perms.users.CMD_Perms_Users_Setnick;
import org.cryptical.cbot.commands.perms.users.CMD_Perms_Users_Addrole;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Perms {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "perms"))) return;
		
		if (args == 1) {
    		EmbedBuilder builder = new EmbedBuilder().setColor(Color.RED);
    		builder.setTitle("</perms> sub commands");
    		builder.addField("Role", "Manager roles", false);
    		builder.addField("Users", "Manage users", false);
    		builder.addField("Permissions", "List all permissions", false);
    		event.getChannel().sendMessage(builder.build()).queue();
    		return;
    	}
    	
    	if (msg[1].equalsIgnoreCase("role")) {
    		if (args == 2) {
    			EmbedBuilder builder = new EmbedBuilder().setColor(Color.red);
    			builder.setTitle("</perms role> usage");
    			builder.addField("Create", "/perms role create <role> [c:color] [m:mentionable] [h:hoisted]", false);
    			builder.addField("Remove", "/perms role remove <roleId>", false);
    			builder.addField("Add", "/perms role add <roleId> <permission>", false);
    			builder.addField("Show", "/perms role show [roleId]", false);
    			builder.addField("Update", "/perms role update <roleId> [c:color] [m:mentionable] [p:permission;value] [h:hoisted]", false);
    			event.getChannel().sendMessage(builder.build()).queue();
    			return;
    		}
    		
    		// /perm role create <name> [c:color] [m:mentinable]
    		if (msg[2].equalsIgnoreCase("create")) {
    			CMD_Perms_Role_Create.command(event, args, msg, message);
    		}
    		
    		// /perm role remove <roleId>
    		else if (msg[2].equalsIgnoreCase("remove")) {
    			CMD_Perms_Role_Remove.command(event, args, msg, message);
    		}
    		
    		// /perm role show [roleId]
    		else if (msg[2].equalsIgnoreCase("show")) {
    			CMD_Perms_Role_Show.command(event, args, msg, message);
    		}
    		
    		// /perm role update <roleId> [c:color] [m:mentionable] [p:permission;t/f] [h:hoisted]
    		else if (msg[2].equalsIgnoreCase("update")) {
    			CMD_Perms_Role_Update.command(event, args, msg, message);
    		} 

    		else {
    			event.getChannel().sendMessage("Subcommand " + msg[2] + " not found").queue();
    		}
    	}
    	
    	
    	// /perms users
    	else if (msg[1].equalsIgnoreCase("users")) {
    		if (args == 2) {
    			EmbedBuilder builder = new EmbedBuilder().setColor(Color.red);
    			builder.setTitle("</perms users> usage");
    			builder.addField("Info", "/perms users info <mention>", false);
    			builder.addField("Addrole", "/perms users addrole <mention> <roleId>", false);
    			builder.addField("Removerole", "/perms users removerole <mention> <roleId>", false);
    			builder.addField("Setnick", "/perms users setnick <mention> <newname>", false);
    			event.getChannel().sendMessage(builder.build()).queue();
    			return;
    		}
    		
    		// /perms users info <mention>
    		else if (msg[2].equalsIgnoreCase("info")) {
    			CMD_Perms_Users_Info.command(event, args, msg, message);
    		}
    		
    		// /perms users addrole <mention> <roleId>
    		else if (msg[2].equalsIgnoreCase("addrole")) {
    			CMD_Perms_Users_Addrole.command(event, args, msg, message);
    		}
    		
    		// /perms users removerole <mention> <roleId>
    		else if (msg[2].equalsIgnoreCase("removerole")) {
    			CMD_Perms_Users_Removerole.command(event, args, msg, message);
    		}
    		
    		// /permms users setnick <mention> <nickname>
    		else if (msg[2].equalsIgnoreCase("setnick")) {
    			CMD_Perms_Users_Setnick.command(event, args, msg, message);
    		}
    	}
    	
    	
		// /perm permissions
		else if (msg[1].equalsIgnoreCase("permissions")) {
			List<String> perms = new ArrayList<>();
			for (Permission p : Permission.values()){
				perms.add(p.name());
			}
			event.getChannel().sendMessage(perms.toString().replace("[", "").replace("]", "")).queue();
		
		}
	}
}
