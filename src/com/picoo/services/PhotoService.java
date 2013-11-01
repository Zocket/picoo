package com.picoo.services;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PhotoService extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783312361170990980L;
	private PhotoServiceHelper psh;
	private static final Logger logger=Logger.getLogger(PhotoService.class.getName());
	public void init(ServletConfig sc) throws ServletException {
	    super.init(sc);
	    psh=new PhotoServiceHelper(sc.getServletContext().getInitParameter("flickr_rest_url"),
	    		sc.getServletContext().getInitParameter("flickr_api_key"));
	  }
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{		
		resp.getWriter().println(retrieveFlickrPhotos(
				new Date(Long.parseLong(req.getParameter("fromTime"))),
				new Date(Long.parseLong(req.getParameter("toTime")))));
	}
	
	
	public StringBuffer retrieveFlickrPhotos(Date fromTime, Date toTime) throws IOException{
		long fromtimesec=fromTime.getTime()/1000;
		long totimesec=toTime.getTime()/1000;
		//extras=date_upload>1383316118,date_upload<1383316823
		//method=flickr.photos.getRecent
		String mString="method=flickr.photos.getRecent";
		String qString="extras=date_upload>="+fromtimesec+",date_upload<"+totimesec;
		
		StringBuffer resp=psh.doGet(mString, qString);
		logger.info(resp.toString());
		return resp;
	}
	
	
}
