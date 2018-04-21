package org.cryptical.cbot.commands.music;

import org.cryptical.cbot.music.AudioUtils;
	import org.cryptical.cbot.utils.Constants;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_queue {
	
	// /queue <link>
	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (message.startsWith(Constants.botPrefix + "queue")) return;
		if (args != 2) {
			event.getChannel().sendMessage("Not enough arguments!").queue();
			return;
		}
		
		String link = msg[1];
		AudioUtils.getPlayerManager().loadItem(link, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {
				AudioUtils.getSchedular().queue(track);
				event.getChannel().sendMessage("Queued track").queue();
			}
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				for (AudioTrack t : playlist.getTracks()) {
					AudioUtils.getSchedular().queue(t);
				}
				event.getChannel().sendMessage("Queued tracks").queue();
			}
			@Override
			public void noMatches() {
				event.getChannel().sendMessage("No track found!").queue();
			}
			@Override
			public void loadFailed(FriendlyException throwable) {
				event.getChannel().sendMessage("Failed to load track").queue();
			}
		});
	}
}
