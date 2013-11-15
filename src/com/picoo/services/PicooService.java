package com.picoo.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.picoo.services.impl.FlickrPhotoServiceFactory;
import com.picoo.services.itfc.PhotoService;

public class PicooService extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783312361170990980L;
	private FlickrPhotoServiceFactory fpsf;
	/*private static final String FLICKRRECENT = "flickr.photos.getRecent";
	private static final String FLICKRPOPULAR = "flickr.stats.getPopularPhotos";

	private PhotoServiceHelper psh;*/
	@SuppressWarnings("unused")
	private static final Logger logger=Logger.getLogger(PicooService.class.getName());
	public void init(ServletConfig sc) throws ServletException{
		fpsf=new FlickrPhotoServiceFactory();
	}
	
	
	/*public void init(ServletConfig sc) throws ServletException {
	    super.init(sc);
	    psh=new PhotoServiceHelper(sc.getServletContext().getInitParameter("flickr_rest_url"),
	    		sc.getServletContext().getInitParameter("flickr_api_key"),
	    		sc.getServletContext().getInitParameter("flickr_service_protocol"),
	    		sc.getServletContext().getInitParameter("flickr_json_rootnode"));
	  }
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{	
		Calendar cal=Calendar.getInstance();
		long totimestamp=cal.getTimeInMillis();
		cal.add(Calendar.MONTH, -6);
		long fromtimestamp=cal.getTimeInMillis();
			
		PrintWriter respWriter=resp.getWriter();
		respWriter.println(retrieveFlickrRecent(new Date(fromtimestamp),
				new Date(totimestamp)));
		
	}*/
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{	
		/*Calendar cal=Calendar.getInstance();
		long totimestamp=cal.getTimeInMillis();
		cal.add(Calendar.MONTH, -6);
		long fromtimestamp=cal.getTimeInMillis();*/	
		resp.setContentType("application/json");
		PrintWriter respWriter=resp.getWriter();
		PhotoService ps=fpsf.getPhotoService();
		respWriter.println(ps.getPicooPhotoListFromService(req).toString());		
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
	
	// this method can only be called by authenticated users.
/*	@SuppressWarnings("unused")
	private String retrieveFlickrPopular() throws UnsupportedEncodingException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String mString="method="+FLICKRPOPULAR;
		String qString="";
		FlickrPhotoList fpl=psh.getJsonFlickrPhotos(mString, qString);
		return psh.getJsonPhotoList(fpl);
	}
	
	public String retrieveFlickrRecent(Date fromTime, Date toTime) throws IOException{
		long fromtimesec=fromTime.getTime()/1000;
		long totimesec=toTime.getTime()/1000;
		//extras=date_upload>1383316118,date_upload<1383316823
		//method=flickr.photos.getRecent
		String mString="method="+FLICKRRECENT;
		String qString="extras=date_upload>="+fromtimesec+",date_upload<"+totimesec;
		FlickrPhotoList fpl=psh.getJsonFlickrPhotos(mString, qString);
		//return psh.getJsonPhotosFlickr(psh.transform(psh.getJsonFlickrStream(mString, qString),FlickrPhotoList.class));
		return psh.getJsonPhotoList(fpl);
	}	*/
	
	
}
