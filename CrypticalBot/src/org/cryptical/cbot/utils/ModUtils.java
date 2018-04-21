package org.cryptical.cbot.utils;

import java.util.HashMap;

import org.cryptical.cbot.Core;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class ModUtils {

	private Category cat;
	private HashMap<User, VoiceChannel> vchannels;
	
	public ModUtils() {
		cat = Core.discord.getCategoryById(Constants.privateCategoryId);
		vchannels = new HashMap<>();
	}
	
	public void sendMessage(User user, String message) {
    	user.openPrivateChannel().queue( (channel) -> {
    		channel.sendMessage(message).queue();
    	});
	}
	
	public VoiceChannel createVoiceChannel(User owner, Guild guild, String name, int userLimit) {
		if (vchannels.containsKey(owner)) {
			return vchannels.get(owner);
		}
		VoiceChannel c = (VoiceChannel) guild.getController().createVoiceChannel(name)
				.setUserlimit(userLimit)
				.complete();
		vchannels.put(owner, c);
		
		Role role = guild.getRoleById(Constants.memberRoleId);
		c.createPermissionOverride(role).setDeny(Permission.VOICE_CONNECT).queue();
		
		return c;
	}
	
	public VoiceChannel createVoiceChannel(User owner, Category cat, String name, int userLimit) {
		if (vchannels.containsKey(owner)) {
			return vchannels.get(owner);
		}
		VoiceChannel c = (VoiceChannel) cat.createVoiceChannel(name)
				.setUserlimit(userLimit)
				.complete();
		vchannels.put(owner, c);
		
		Role role = cat.getGuild().getRoleById(Constants.memberRoleId);
		c.createPermissionOverride(role).setDeny(Permission.VOICE_CONNECT).queue();
		
		return c;
	}
	
	public TextChannel createTextChannel(Guild guild, String name, String topic) {
		TextChannel c = (TextChannel) guild.getController().createTextChannel(name)
				.setTopic(topic)
				.complete();
		return c;
	}
	
	public TextChannel createTextChannel(Category cat, String name, String topic) {
		TextChannel c = (TextChannel) cat.createTextChannel(name)
				.setTopic(topic)
				.complete();
		return c;
	}
	
	public VoiceChannel getChannelByUser(User user) {
		if (vchannels.containsKey(user)) {
			return vchannels.get(user);
		} return null;
	}
	
	public void removeVoiceChannel(User user) {
		VoiceChannel c =getChannelByUser(user);
		if (c == null) return;
		vchannels.get(user).delete().queue();
		vchannels.remove(user);
	}
	
	public void removeAllPChannels() {
		for (VoiceChannel vc : cat.getVoiceChannels()) {
			vc.delete().queue();
		}
	}
}
