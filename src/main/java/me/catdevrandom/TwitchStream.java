package me.catdevrandom;

import java.util.Map;

public class TwitchStream {

	public TwitchChannel channel;
	public int viewers;
	public double fps;
	public int videoHeight;
	public double delay;
	
	private TwitchAPI api;
	
	public TwitchStream(TwitchChannel channel)
	{
		this.api = new TwitchAPI();
		this.channel = channel;
		parseStreamMap(api.getStreamMap(channel.name));
	}
	
	private void parseStreamMap(Map jMap)
	{
		this.viewers = ((Double) jMap.get("viewers")).intValue();
		this.fps = (Double) jMap.get("average_fps");
		this.videoHeight = ((Double) jMap.get("video_height")).intValue();
		this.delay = (Double) jMap.get("delay");
	}
	
}
