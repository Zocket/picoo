package com.picoo.services;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.picoo.flickr.FlickrPhotoList;

public class PhotoService extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783312361170990980L;
	private static final String FLICKRRECENT="flickr.photos.getRecent";
	private PhotoServiceHelper psh;
	@SuppressWarnings("unused")
	private static final Logger logger=Logger.getLogger(PhotoService.class.getName());
	public void init(ServletConfig sc) throws ServletException {
	    super.init(sc);
	    psh=new PhotoServiceHelper(sc.getServletContext().getInitParameter("flickr_rest_url"),
	    		sc.getServletContext().getInitParameter("flickr_api_key"),
	    		sc.getServletContext().getInitParameter("flickr_service_protocol"),
	    		sc.getServletContext().getInitParameter("flickr_json_rootnode"));
	  }
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{	
		/*StringBuilder sb=retrieveFlickrPhotos(
				new Date(Long.parseLong(req.getParameter("fromTime"))),
				new Date(Long.parseLong(req.getParameter("toTime"))));
		resp.getWriter().println(sb);*/
		resp.getWriter().println(retrieveJsonFlickrPhotos(
				new Date(Long.parseLong(req.getParameter("fromTime"))),
				new Date(Long.parseLong(req.getParameter("toTime")))));
		
	}
	
	
/*	POC method which won't be used any more.
 * 
 * public StringBuilder retrieveFlickrPhotos(Date fromTime, Date toTime) throws IOException{
		long fromtimesec=fromTime.getTime()/1000;
		long totimesec=toTime.getTime()/1000;
		//extras=date_upload>1383316118,date_upload<1383316823
		//method=flickr.photos.getRecent
		String mString="method=flickr.photos.getRecent";
		String qString="extras=date_upload>="+fromtimesec+",date_upload<"+totimesec;
		
		StringBuilder resp=psh.flickrGet(mString, qString);
		logger.info(resp.toString());
		return resp;
	}*/
	
	public String retrieveJsonFlickrPhotos(Date fromTime, Date toTime) throws IOException{
		long fromtimesec=fromTime.getTime()/1000;
		long totimesec=toTime.getTime()/1000;
		//extras=date_upload>1383316118,date_upload<1383316823
		//method=flickr.photos.getRecent
		String mString="method="+FLICKRRECENT;
		String qString="extras=date_upload>="+fromtimesec+",date_upload<"+totimesec;
		FlickrPhotoList fpl=psh.getJsonFlickrPhotos(mString, qString);
		//return psh.getJsonPhotosFlickr(psh.transform(psh.getJsonFlickrStream(mString, qString),FlickrPhotoList.class));
		return psh.getJsonPhotoList(fpl);
	}	
	
	
}
