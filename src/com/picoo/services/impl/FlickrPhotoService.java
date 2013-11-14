package com.picoo.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.picoo.model.EntityConverter;
import com.picoo.model.PicooPhotoList;
import com.picoo.model.flickr.FlickrPhotoList;
import com.picoo.model.itfc.PhotoList;
import com.picoo.services.itfc.PhotoService;

public class FlickrPhotoService implements PhotoService {
	private String baseRequestUrl;
	private String rootNodeName;
	private int pageSize;
	private String requestMethod;
	private static final String DEFAULTMETHOD="flickr.photos.getRecent";
	private static final int DEFAULTPAGESIZE = 50;
	private ServiceHandler sh;
	private JsonParser jp;
	private Gson gson;
	
	public FlickrPhotoService(String u,String r){
		sh=new ServiceHandler();
		jp = new JsonParser();
		gson = new Gson();
		baseRequestUrl=u;
		rootNodeName=r;
		pageSize=DEFAULTPAGESIZE;
		requestMethod=DEFAULTMETHOD;
	}
	
	public FlickrPhotoService(String u,String r,String m, int p){
		this(u,r);
		pageSize=p;
		requestMethod=m;
	}
	
	@Override
	public InputStreamReader getStreamFromService(HttpServletRequest req)  {
		// TODO Auto-generated method stub
		String url=this.baseRequestUrl+"&method="+this.requestMethod+"&per_page="+this.pageSize;
		return sh.invokeGet(url);
	}

	@Override
	public FlickrPhotoList getPhotoListFromStream(InputStreamReader isr) {
		JsonReader jr=new JsonReader(isr);
		FlickrPhotoList fpl=null;
		try{
			jr.setLenient(true);		
			JsonElement je=jp.parse(jr).getAsJsonObject().get(this.rootNodeName);
			fpl = gson.fromJson(je, FlickrPhotoList.class);
			//in.close();
		}finally{
			try {
				jr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return fpl;
	}

	@Override
	public PicooPhotoList getPicooPhotoList(PhotoList pl) {
		FlickrPhotoList fpl=(FlickrPhotoList)pl;
		// TODO Auto-generated method stub
		return EntityConverter.convertFlickrToPicooList(fpl);
	}

	@Override
	public PicooPhotoList getPicooPhotoListFromService(HttpServletRequest req) {
		// TODO Auto-generated method stub
		InputStreamReader isr=this.getStreamFromService(req);
		FlickrPhotoList fpl=this.getPhotoListFromStream(isr);
		PicooPhotoList ppl=this.getPicooPhotoList(fpl);
		return ppl;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRootNodeName() {
		return rootNodeName;
	}

	public void setRootNodeName(String rootNodeName) {
		this.rootNodeName = rootNodeName;
	}
	


}
