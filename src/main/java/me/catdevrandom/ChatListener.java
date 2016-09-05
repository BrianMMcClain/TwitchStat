package me.catdevrandom;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class ChatListener extends ListenerAdapter {
	
	@Override
    public void onMessage(MessageEvent event) {
		StringBuilder b = new StringBuilder()
			.append(event.getUser().getNick())
			.append((": "))
			.append(event.getMessage());
		
		System.out.println(b.toString());
    }
	
}
