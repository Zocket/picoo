package com.picoo.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import com.google.gson.*;
public class PhotoServiceHelper {
	private String url;
	private String apiKey;
	private String formatString;
	private final static String FORMATSTR="format=json&nojsoncallback=1";
	private Gson gson=new Gson();
	private JsonParser jp=new JsonParser();
	private static final Logger logger=Logger.getLogger(PhotoServiceHelper.class.getName());
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getFormatString() {
		return formatString;
	}
	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}
	public PhotoServiceHelper(String u,String a,String f){
		this.url=u;
		this.apiKey=a;
		this.formatString=f;
	}
	public PhotoServiceHelper(String u, String a){
		this(u,a,FORMATSTR);
	}
	
	public StringBuilder flickrGet(String mString,String qString) throws IOException{
		//add query string to the url
		//&api_key=7a9b50d4c46b4de08e4a61cf0ec4043c
		String fullUrl=url+mString+"&"+FORMATSTR+"&"+URLEncoder.encode(qString,"UTF-8")+"&api_key="+apiKey;
	 
		URL obj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + fullUrl);
		logger.info("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
		return response;		
	}	
	
	public InputStreamReader getFlickrJsonStream(String mString,String qString) throws IOException{
		//add query string to the url
		//&api_key=7a9b50d4c46b4de08e4a61cf0ec4043c
		String fullUrl=url+mString+"&"+FORMATSTR+"&"+URLEncoder.encode(qString,"UTF-8")+"&api_key="+apiKey;
	 
		URL obj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + fullUrl);
		logger.info("Response Code : " + responseCode);
 
		return new InputStreamReader(con.getInputStream());		
	}	
	
	public <T> T transform(InputStreamReader is, Class<T> type,String nodeName){
		/*
		JsonElement je=jp.parse(sb.toString()).getAsJsonObject().get(nodeName);
		T t=gson.fromJson(je, type);
		*/
		
		Json
		return t;
	}
	
		
}
