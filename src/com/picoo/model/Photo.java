package com.picoo.model;

import java.util.Date;

public class Photo {
	private String title;
	private String description;
	private Date uploadDate;
	private Date takenDate;
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
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Date getTakenDate() {
		return takenDate;
	}
	public void setTakenDate(Date takenDate) {
		this.takenDate = takenDate;
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
