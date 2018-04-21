package org.cryptical.cbot.commands.privatechannel;

import org.cryptical.cbot.Core;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CMD_Dispose {

	public static void command(MessageReceivedEvent event, int args, String[] msg, String message) {
		if (!(message.toLowerCase().startsWith(Constants.botPrefix + "dispose"))) return;

    	Core.utils.removeVoiceChannel(event.getAuthor());
    }
}
