package com.picoo.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;


public class ServiceHandler {
	private String url;
	private static final Logger logger = Logger
			.getLogger(ServiceHandler.class.getName());
	public ServiceHandler(){
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public InputStreamReader invokeGet(){
		return this.invokeGet(url);
	}
	
	public InputStreamReader invokeGet(String url) {
		URL urlObj=null;
		try {
			logger.info("\nSending 'GET' request to URL : " + url);
			urlObj=new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.invokeGet(urlObj);
	}
	
	public InputStreamReader invokeGet(URL urlObj) {
		InputStreamReader is = null;
		
		try {
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + urlObj.toURI());
		//logger.info("\nSending 'GET' request to Path "+urlObj.getPath());
		logger.info("Response Code : " + responseCode);	
		
			is = new InputStreamReader(con.getInputStream(),"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
}
