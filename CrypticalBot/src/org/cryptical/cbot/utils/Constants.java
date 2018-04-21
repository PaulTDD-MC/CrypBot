package org.cryptical.cbot.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final String token = "NDExNDM5MzM3MjY4MzE0MTEy.DV8DlQ.YBgvT46NUt8o4f-5CjMGVDdQZM0";
	public static final String botPrefix = "/";
	public static final String botChannelId = "411462502648381453";
	public static final String privateCategoryId = "412870826682220548";
	public static final String memberRoleId = "412875689440575488";
	public static final String generalVoiceId = "411462502648381455";
	public static final String musicChannelId = "418053549121994752";
	public static final boolean statusMessages = true;
	
	public static final String host = "localhost";
	public static final String port = "3306";
	public static final String database = "botdb";
	public static final String user = "root";
	public static final String password = "password";
	public static final String userTable = "crypbot_users";
	
	public static final int maxMessagesPerMinute = 5;
	
	public static final List<String> facts = Arrays.asList(
			"Banging your head against a wall burns 150 calories an hour.",
			"A flock of crows is known as a murder.",
			"The person who invented the frisbee, was cremated and made into a frisbee after he died!",
			"An eagle can kill a young deer, and fly away with it.",
			"If you constantly fart for six years & nine months, enough gas is produced to create the energy of an atomic bomb!",
			"A baby octopus is about the size of a flea when it is born.",
			"About 8,000 Americans are injured by music instruments, every year.",
			"A small child could swim through the veins of a blue whale.",
			"An arctophile is a person who collects, or is very fond of teddy bears.",
			"If you leave everything to the last minute, it will only take a minute.",
			"Dying is illegal in the Houses of Parliaments.",
			"Slugs have four noses.",
			"The 20th of March is known as Snowman Burning Day!"
			);
	
	public static final List<String> ball = Arrays.asList(
			"It is certain",
			"It is decided so",
			"Without a doubt",
			"Yes - definitely",
			"You may rely on it",
			"As i see it, yes",
			"Most likely",
			"Outlook good",
			"Yes",
			"Signs points to yes",
			"Reply hazy, try again",
			"Ask again later",
			"Better not tell you now",
			"Cannot predict now",
			"Concentrate and ask again",
			"Don't count on it",
			"My reply is no",
			"My sources say no",
			"Outlook not so good",
			"Very doubtful"
			);
	
	public static final List<String> help = Arrays.asList(
			"/hello - Reply with a hello message",
			"/clear <amount> - Clear the chat",
			"/private - Create a private channel",
			"/add <mention> - Add someone to your private channel",
			"/remove <mention> - Remove someone from your private channel",
			"/dispose - Remove your private channel",
			"/stop - Stop the bot",
			"/dice <amount> - Roll a dice",
			"/fact - Summon a random fact",
			"/8ball <question> - Let your question be answered (yes/no)",
			"/perms - help menu for permissions",
			"/lastseen <minutes> - Get past online players",
			"/broadcast <channel;> [e:t/f] <message> - Broadcast a message to channels" 
			);
}
