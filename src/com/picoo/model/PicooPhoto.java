package com.picoo.model;

import java.util.Date;

import com.picoo.model.itfc.Photo;

public class PicooPhoto extends Photo{
	private Date uploadDate;
	private String geoInfo;
	private Owner owner;
	private String url;
	private String[] tags;
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}	

	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGeoInfo() {
		return geoInfo;
	}
	public void setGeoInfo(String geoInfo) {
		this.geoInfo = geoInfo;
	}
}
