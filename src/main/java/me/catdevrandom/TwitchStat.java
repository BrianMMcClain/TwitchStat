package me.catdevrandom;

import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

/**
 * Hello world!
 *
 */
public class TwitchStat 
{
	private static PircBotX bot;
	private static TwitchAPI twitch;
	
    public static void main( String[] args )
    {
    	if (args.length == 0)
    	{
    		System.out.println("Channel name required");
    		System.exit(-1);
    	}
    	
    	String channelName = args[0];
    	
    	twitch = new TwitchAPI();
    	
    	TwitchChannel channel = new TwitchChannel(channelName);
    	boolean isLive = channel.isLive();
    	
    	System.out.println("Name: " + channel.name);
    	System.out.println("Display: " + channel.displayName);
    	System.out.println("Live: " + isLive);
    	System.out.println("Game: " + channel.game);
    	System.out.println("Status: " + channel.status);
    	System.out.println("Views: " + channel.views);
    	System.out.println("Followers: " + channel.followers);
    	
    	if (isLive)
    	{
	    	TwitchStream stream = channel.getStream();
	    	System.out.println("Viewers: " + stream.viewers);
	    	System.out.println("FPS: " + stream.fps);
	    	System.out.println("Delay: " + stream.delay);
	    	System.out.println("Resolution: " + stream.videoHeight);
    	}
    	
    	Configuration listenerConfig = new Configuration.Builder()
		.setName("justinfan12345")
		.addServer("irc.chat.twitch.tv", 6667)
		.addAutoJoinChannel("#" + channelName)
		.addListener(new ChatListener())
		.buildConfiguration();
	
    	bot = new PircBotX(listenerConfig);

    	try {
			bot.startBot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        while(true) {
        	try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("tick");
        }
    }
}
