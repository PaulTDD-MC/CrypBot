package org.cryptical.cbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

public class AudioUtils {
	
	private static AudioPlayerManager playerManager;
	private static AudioPlayer audioPlayer;
	private static TrackSchedular schedular;
	
	public static void setup() {
		playerManager = new DefaultAudioPlayerManager();
		AudioSourceManagers.registerRemoteSources(playerManager);
		audioPlayer = playerManager.createPlayer();
		
		schedular = new TrackSchedular();
		audioPlayer.addListener(schedular);
	}
	
	public static AudioPlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public static AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}
	
	public static TrackSchedular getSchedular() {
		return schedular;
	}

}
