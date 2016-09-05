package me.catdevrandom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class TwitchAPI {
	
	private String baseURL = "https://api.twitch.tv/kraken";
	private Gson gson;
	private HttpClient httpClient;
	
	public TwitchAPI() {
		this.gson = new Gson();
		httpClient = HttpClientBuilder.create().build();
	}
	
	private Map apiRequest(String url)
	{
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/vnd.twitchtv.v3+json");
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuffer body = new StringBuffer();
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				body.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Gson().fromJson(body.toString(), Map.class);
	}
	
	public boolean channelOnline(String channel)
	{
		String url = String.format("%s/streams/%s", this.baseURL, channel);
		Map jMap = this.apiRequest(url);

		return jMap.get("stream") != null;
	}
	
	public String channelGame(String channel)
	{
		String url = String.format("%s/streams/%s", this.baseURL, channel);
		Map jMap = this.apiRequest(url);
		
		if (jMap.get("stream") != null)
		{
			return (String) ((Map)jMap.get("stream")).get("game");
		}
		
		return null;
	}
	
	public double channelViewers(String channel)
	{
		String url = String.format("%s/streams/%s", this.baseURL, channel);
		Map jMap = this.apiRequest(url);
		
		if (jMap.get("stream") != null)
		{
			return (Double) ((Map)jMap.get("stream")).get("viewers");
		}
		
		return -1;
	}
	
	public Map getChannelMap(String channel)
	{
		String url = String.format("%s/channels/%s", this.baseURL, channel);
		return (Map) this.apiRequest(url);
	}
	
	public Map getStreamMap(String channel)
	{
		String url = String.format("%s/streams/%s", this.baseURL, channel);
		return (Map) this.apiRequest(url).get("stream");
	}
	

}
