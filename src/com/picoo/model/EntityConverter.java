package com.picoo.model;

import com.picoo.flickr.FlickrPhoto;

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
		 fp.getId()+"_"+fp.getSecret()+".jpg");
		return p;
	}
}
