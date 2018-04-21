package org.cryptical.cbot;

import javax.security.auth.login.LoginException;

import org.cryptical.cbot.events.Commands;
import org.cryptical.cbot.events.OnlineStatusEvent;
import org.cryptical.cbot.events.SpamPrevention;
import org.cryptical.cbot.events.SystemEvents;
import org.cryptical.cbot.utils.Constants;
import org.cryptical.cbot.utils.Mentions;
import org.cryptical.cbot.utils.ModUtils;
import org.cryptical.cbot.utils.ServerData;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class Core {
	public static ModUtils utils;
	public static JDA discord = null;
	public static Mentions mentions;
	public static ServerData sdata;

	public static void main(String[] args) {
		try {
			discord = new JDABuilder(AccountType.BOT).setToken(Constants.token).setGame(Game.playing("play.cryptical.org"))
					.addEventListener(new SystemEvents())
					.addEventListener(new Commands())
					.addEventListener(new OnlineStatusEvent())
					.addEventListener(new SpamPrevention())
					.buildBlocking();;
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		utils = new ModUtils();
		mentions = new Mentions();
		sdata = new ServerData();
	}
}
