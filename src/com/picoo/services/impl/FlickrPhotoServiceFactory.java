package com.picoo.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.picoo.services.itfc.AuthService;
import com.picoo.services.itfc.PhotoServiceFactory;

public class FlickrPhotoServiceFactory extends PhotoServiceFactory {
	static String urlProperty="flickr.rest.url";
	static String apiKeyProperty="flickr.api.key";
	static String rootNodeProperty="flickr.json.rootnode";
	static String protocolProperty="flickr.service.protocol";

	static String serviceUrl;
	static String apiKey;
	static String rootNodeName;
	static String protocol;
	static String urlString;
	static Map<String,String> paramMap;
	@SuppressWarnings("unused")
	private static final Logger logger=Logger.getLogger(FlickrPhotoServiceFactory.class.getName());
	static {
		Properties props = new Properties();		 
    	try {
               //load a properties file
    		//props.load(new FileInputStream("picoo.properties"));
    		//props.load(FlickrPhotoServiceFactory.class.getClassLoader().getResourceAsStream("picoo.properties"));
    		props.load(FlickrPhotoServiceFactory.class.getResourceAsStream("/picoo.properties"));
    		serviceUrl=props.getProperty(urlProperty);
    		apiKey=props.getProperty(apiKeyProperty);
    		rootNodeName=props.getProperty(rootNodeProperty);
    		protocol=props.getProperty(protocolProperty);
    		urlString=serviceUrl+"&"+protocol+"&api_key=" + apiKey;
    		paramMap=new HashMap<String,String>();
    		String k;
    		int pflen="flickr".length()+1;
    		for (Object ko : props.keySet()){
    			k=(String)ko;
    			if (k.startsWith("flickr.method")||k.startsWith("flickr.param")){
    				paramMap.put(k.substring(pflen), props.getProperty(k));
    			}
    		}
    		
    		/*logger.info("Service Url: "+serviceUrl);
    		logger.info("API key: "+apiKey);
    		logger.info("Root node: "+rootNodeName);*/
    		
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}

	
	@Override
	public FlickrPhotoService getPhotoService() {
		// TODO Auto-generated method stub
		return new FlickrPhotoService();
	}

	@Override
	public AuthService getAuthService() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getRootNodeName() {
		// TODO Auto-generated method stub
		return rootNodeName;
	}


	public static String getBaseUrl() {
		// TODO Auto-generated method stub
		return urlString;
	}


	public static Map<String, String> getParamMap() {
		// TODO Auto-generated method stub
		return paramMap;
	}


}
