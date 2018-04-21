package org.cryptical.cbot.commands.music;

import org.cryptical.cbot.music.AudioPlayerSendHandler;
import org.cryptical.cbot.music.AudioUtils;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class CMD_setup {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!message.startsWith(Constants.botPrefix + "setupAudio")) return;
		
		Guild guild = event.getGuild();
		VoiceChannel channel = guild.getVoiceChannelById(Constants.musicChannelId);
		AudioManager manager = guild.getAudioManager();
		
		AudioUtils.setup();
		
		manager.setSendingHandler(new AudioPlayerSendHandler(AudioUtils.getAudioPlayer()));
		manager.openAudioConnection(channel);
		
		event.getChannel().sendMessage("Done!").queue();
	}
}
