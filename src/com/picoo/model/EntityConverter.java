package com.picoo.model;

import java.util.ArrayList;
import java.util.List;

import com.picoo.model.flickr.FlickrPhoto;
import com.picoo.model.flickr.FlickrPhotoList;

public class EntityConverter {
	public static PicooPhoto convertFlickrToPicoo(FlickrPhoto fp) {
		PicooPhoto p = new PicooPhoto();
		p.setTitle(fp.getTitle());
		p.setDescription(fp.getDescription());
		Owner o=new Owner();
		o.setId(fp.getOwner());
		p.setOwner(o);

		p.setUrl("http://farm"+fp.getFarm()+".staticflickr.com/"+
		 fp.getServer()+"/"+
		 fp.getId()+"_"+fp.getSecret()+"_b.jpg");
		return p;
	}
	

	public static PicooPhotoList convertFlickrToPicooList(FlickrPhotoList fps) {
		PicooPhotoList pl=new PicooPhotoList();
		List<PicooPhoto> ps=new ArrayList<PicooPhoto>();
		for (FlickrPhoto fp: fps.getPhotos()){
			ps.add(convertFlickrToPicoo(fp));
		}
		pl.setPhotos(ps);
		return pl;
	}
}
