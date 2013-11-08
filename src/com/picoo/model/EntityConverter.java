package com.picoo.model;

import java.util.ArrayList;
import java.util.List;

import com.picoo.model.flickr.FlickrPhoto;
import com.picoo.model.flickr.FlickrPhotoList;

public class EntityConverter {
	public static Photo convertFPToPhoto(FlickrPhoto fp) {
		Photo p = new Photo();
		p.setTitle(fp.getTitle());
		p.setDescription(fp.getDescription());
		Owner o=new Owner();
		o.setId(fp.getOwner());
		p.setOwner(o);
		/*
		 * Flickr photo source url
		 * http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
		 * or
		 * http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
		 * or
		 * http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
		 */
		p.setUrl("http://farm"+fp.getFarm()+".staticflickr.com/"+
		 fp.getServer()+"/"+
		 fp.getId()+"_"+fp.getSecret()+"_b.jpg");
		return p;
	}
	
	public static List<Photo> convertFPSToPhotos(FlickrPhotoList fps) {
		List<Photo> ps=new ArrayList<Photo>();
		for (FlickrPhoto fp: fps.getPhotos()){
			ps.add(convertFPToPhoto(fp));
		}
		return ps;
	}
	
	public static PhotoList convertFPSToPL(FlickrPhotoList fps) {
		PhotoList pl=new PhotoList();
		pl.setPhotos(convertFPSToPhotos(fps));
		return pl;
	}
}
