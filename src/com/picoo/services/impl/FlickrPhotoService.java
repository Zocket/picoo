package com.picoo.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

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
	private int pageSize;
	private String requestMethod;
	private static final String DEFAULTMETHOD = "flickr.photos.getRecent";
	private static final int DEFAULTPAGESIZE = 50;
	private ServiceHandler sh;
	private JsonParser jp;
	private Gson gson;

	public FlickrPhotoService() {
		sh = new ServiceHandler();
		jp = new JsonParser();
		gson = new Gson();
		pageSize = DEFAULTPAGESIZE;
		requestMethod = DEFAULTMETHOD;
	}

	@Override
	public InputStreamReader getStreamFromService(HttpServletRequest req) {
		// TODO Auto-generated method stub
		// retrieve all parameters from http request
		/*
		 * Allowed query strings: method=recent or method=search
		 * flickr.method.recent=flickr.photos.getRecent
		 * flickr.method.search=flickr.photos.search
		 * 
		 * page=, extras = , and etc flickr.param.page=page
		 * flickr.param.extras=extras flickr.param.pagesize=per_page
		 * flickr.param.uploadfromdate=min_upload_date
		 * flickr.param.uploadtodate=max_upload_date
		 * flickr.param.takenfromdate=min_taken_date
		 * flickr.param.takentodate=max_taken_date flickr.param.sort=sort
		 */
		boolean hasMethod = false;
		boolean hasPageSize = false;
		Map<String, String> pmap = FlickrPhotoServiceFactory.getParamMap();
		@SuppressWarnings("unchecked")
		Set<String> kset = (Set<String>) req.getParameterMap().keySet();
		String url = FlickrPhotoServiceFactory.getBaseUrl();
		String urlsuffix = "";	

		for (String k : kset) {
			if (k.equals("method")) {
				if (pmap.containsKey("method."+req.getParameter("method"))) {
					urlsuffix += "&method="
							+ pmap.get("method." + req.getParameter("method"));
					hasMethod = true;
				}
			} else {
				if(pmap.containsKey("param."+k)){
					urlsuffix+="&"+pmap.get("param."+k);
					urlsuffix+="="+req.getParameter(k);
				}
			}
			if (k.equals("pagesize")) {
				hasPageSize = true;
			}
		}

		if (!hasMethod) {
			urlsuffix += "&method=" + this.requestMethod;
		}

		if (!hasPageSize) {
			urlsuffix += "&per_page=" + this.pageSize;
		}
		/*try {
			url+=URLEncoder.encode(urlsuffix,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		url+=urlsuffix;

		return sh.invokeGet(url);
	}

	@Override
	public FlickrPhotoList getPhotoListFromStream(InputStreamReader isr) {
		JsonReader jr = new JsonReader(isr);
		FlickrPhotoList fpl = null;
		try {
			jr.setLenient(true);
			JsonElement je = jp.parse(jr).getAsJsonObject()
					.get(FlickrPhotoServiceFactory.getRootNodeName());
			fpl = gson.fromJson(je, FlickrPhotoList.class);
			// in.close();
		} finally {
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
		FlickrPhotoList fpl = (FlickrPhotoList) pl;
		// TODO Auto-generated method stub
		return EntityConverter.convertFlickrToPicooList(fpl);
	}

	@Override
	public PicooPhotoList getPicooPhotoListFromService(HttpServletRequest req) {
		// TODO Auto-generated method stub
		InputStreamReader isr = this.getStreamFromService(req);
		FlickrPhotoList fpl = this.getPhotoListFromStream(isr);
		PicooPhotoList ppl = this.getPicooPhotoList(fpl);
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

}
