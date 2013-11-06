package com.picoo.services;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.picoo.flickr.FlickrPhotoList;
import com.picoo.model.EntityConverter;

public class PhotoServiceHelper {
	private String url;
	private String apiKey;
	private String formatString;
	private String rootnodeString;
	private final static String FORMATSTR = "format=json&nojsoncallback=1";
	private final static String ROOTNODESTR = "photos";
	private static final int DEFAULTPAGESIZE = 50;
	private Gson gson = new Gson();
	private JsonParser jp = new JsonParser();
	private static final Logger logger = Logger
			.getLogger(PhotoServiceHelper.class.getName());

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

	public String getRootnodeString() {
		return rootnodeString;
	}

	public void setRootnodeString(String rootnodeString) {
		this.rootnodeString = rootnodeString;
	}

	public PhotoServiceHelper(String u, String a, String f, String n) {
		this.url = u;
		this.apiKey = a;
		this.formatString = f;
		this.setRootnodeString(n);
	}

	public PhotoServiceHelper(String u, String a) {
		this(u, a, FORMATSTR, ROOTNODESTR);
	}

	/*
	 * POC method, won't be used any more
	 * 
	 * public StringBuilder flickrGet(String mString, String qString) throws
	 * IOException { // add query string to the url //
	 * &api_key=7a9b50d4c46b4de08e4a61cf0ec4043c String fullUrl = url + mString
	 * + "&" + FORMATSTR + "&" + URLEncoder.encode(qString, "UTF-8") +
	 * "&per_page="+DEFAULTPAGESIZE+ "&api_key=" + apiKey;
	 * 
	 * URL obj = new URL(fullUrl); HttpURLConnection con = (HttpURLConnection)
	 * obj.openConnection();
	 * 
	 * // optional default is GET con.setRequestMethod("GET");
	 * 
	 * int responseCode = con.getResponseCode();
	 * logger.info("\nSending 'GET' request to URL : " + fullUrl);
	 * logger.info("Response Code : " + responseCode);
	 * 
	 * BufferedReader in = new BufferedReader(new InputStreamReader(
	 * con.getInputStream())); String inputLine; StringBuilder response = new
	 * StringBuilder();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine);
	 * } in.close();
	 * 
	 * // print result System.out.println(response.toString()); return response;
	 * }
	 */

	public InputStreamReader getJsonFlickrStream(String mString, String qString)
			throws IOException, UnsupportedEncodingException,
			MalformedURLException {
		// add query string to the url
		// &api_key=7a9b50d4c46b4de08e4a61cf0ec4043c
		InputStreamReader is = null;
		if (!qString.equals("")) {
			qString = "&" + URLEncoder.encode(qString, "UTF-8");
		}
		String fullUrl = url + mString + "&" + FORMATSTR + qString
				+ "&per_page=" + DEFAULTPAGESIZE + "&api_key=" + apiKey;

		URL obj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + fullUrl);
		logger.info("Response Code : " + responseCode);

		try {
			is = new InputStreamReader(con.getInputStream(),"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return is;
	}

	public <T> T transform(InputStreamReader is, Class<T> type, String nodeName) {
			 
		T t = null;
		//BufferedReader in = null;
		JsonReader jr=new JsonReader(is);
		try{
			jr.setLenient(true);		
			//in = new BufferedReader(is);			
			//JsonElement je = jp.parse(in).getAsJsonObject().get(nodeName);
			JsonElement je=jp.parse(jr).getAsJsonObject().get(nodeName);
			t = gson.fromJson(je, type);
			//in.close();
		}finally{
			try {
				jr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return t;
	}
	
	/* Use this method to debug the message received by GAE app from flickr.
	 * It seems flickr directs requests to "community guidelines" from time to time if the requestor is from GAE.
	 * If deployed locally, this problem won't occur.
	public <T> T transform(InputStreamReader is, Class<T> type, String nodeName) {
		
		T t = null;
		JsonReader jr=null;
		BufferedReader in = null;		
		in = new BufferedReader(is); 
		String inputLine; 
		StringBuilder response = new StringBuilder();
		try{
			while ((inputLine = in.readLine()) != null) { 
				response.append(inputLine);
					 } 
			in.close();
			logger.info(response.toString());
		
			InputStream stream = new ByteArrayInputStream(response.toString().getBytes("UTF-8"));
			jr=new JsonReader(new InputStreamReader(stream));
			jr.setLenient(true);		
			//in = new BufferedReader(is);			
			//JsonElement je = jp.parse(in).getAsJsonObject().get(nodeName);
			JsonElement je=jp.parse(jr).getAsJsonObject().get(nodeName);
			t = gson.fromJson(je, type);
			//in.close();
		}catch (IOException e){
			e.printStackTrace();
		} finally{
			try {
				jr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return t;
	}*/

	public <T> T transform(InputStreamReader is, Class<T> type)
			throws IOException {
		return transform(is, type, rootnodeString);
	}

	public FlickrPhotoList getJsonFlickrPhotos(String mString, String qString)
			throws UnsupportedEncodingException, MalformedURLException,
			IOException {
		InputStreamReader isr = this.getJsonFlickrStream(mString, qString);
		FlickrPhotoList fpl = this.transform(isr, FlickrPhotoList.class);
		return fpl;
	}

	public String getJsonPhotoList(FlickrPhotoList fpl) {
		if(fpl!=null){
			return gson.toJson(EntityConverter.convertFPSToPL(fpl));
		}else{
			logger.warning("FlickrPhotoList is null.");
			return "";
		}
	}

}
