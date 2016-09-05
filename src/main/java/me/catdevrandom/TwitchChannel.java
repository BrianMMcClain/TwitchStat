package me.catdevrandom;

import java.util.Map;

public class TwitchChannel {
	
	public String name;
	public String game;
	public String status;
	public String displayName;
	public int followers;
	public int views;
	
	private TwitchAPI api;
	
	public TwitchChannel(String channelName)
	{
		this.api = new TwitchAPI();
		this.parseChannelMap(api.getChannelMap(channelName));
	}
	
	public boolean isLive()
	{
		return api.channelOnline(this.name);
	}
	
	private void parseChannelMap(Map jMap)
	{
		this.name = (String) jMap.get("name");
		this.game = (String) jMap.get("game");
		this.status = jMap.get("status").toString();
		this.displayName = (String) jMap.get("display_name");
		this.followers = ((Double) jMap.get("followers")).intValue();
		this.views = ((Double) jMap.get("views")).intValue();
	}
	
	public TwitchStream getStream()
	{
		return new TwitchStream(this);
	}

}
