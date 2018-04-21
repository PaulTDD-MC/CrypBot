package org.cryptical.cbot.events;

import org.cryptical.cbot.commands.CMD_Help;
import org.cryptical.cbot.commands.metrics.CMD_8ball;
import org.cryptical.cbot.commands.metrics.CMD_Dice;
import org.cryptical.cbot.commands.metrics.CMD_Fact;
import org.cryptical.cbot.commands.metrics.CMD_Hello;
import org.cryptical.cbot.commands.metrics.CMD_Lastseen;
import org.cryptical.cbot.commands.modutils.CMD_Broadcast;
import org.cryptical.cbot.commands.modutils.CMD_Clear;
import org.cryptical.cbot.commands.modutils.CMD_Perms;
import org.cryptical.cbot.commands.modutils.CMD_Stop;
import org.cryptical.cbot.commands.music.CMD_queue;
import org.cryptical.cbot.commands.music.CMD_setup;
import org.cryptical.cbot.commands.privatechannel.CMD_Add;
import org.cryptical.cbot.commands.privatechannel.CMD_Dispose;
import org.cryptical.cbot.commands.privatechannel.CMD_Private;
import org.cryptical.cbot.commands.privatechannel.CMD_Remove;
import org.cryptical.cbot.utils.Constants;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] msg = message.split(" ");
        int args = msg.length;
        
        if (event.getAuthor().isBot()) return;

		CMD_Help.command(event, args, msg, message);
    	CMD_Clear.command(event, args, msg, message);
        CMD_Hello.command(event, args, msg, message);
        CMD_Private.command(event, args, msg, message);
        CMD_Add.command(event, args, msg, message);
        CMD_Remove.command(event, args, msg, message);
        CMD_Dispose.command(event, args, msg, message);
        CMD_Stop.command(event, args, msg, message);
		CMD_Dice.command(event, args, msg, message);
		CMD_Fact.command(event, args, msg, message);	
		CMD_8ball.command(event, args, msg, message);
		CMD_Lastseen.command(event, args, msg, message);
		CMD_Broadcast.command(event, args, msg, message);
		CMD_setup.command(event, args, msg, message);
		CMD_queue.command(event, args, msg, message);
        
        // /perm
        if (message.startsWith(Constants.botPrefix + "perms")) {
        	CMD_Perms.command(event, args, msg, message);
        }
	}
}
