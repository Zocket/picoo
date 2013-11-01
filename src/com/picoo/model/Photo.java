package com.picoo.model;

import java.util.Date;

public class Photo {
	private String title;
	private String description;
	private Date uploadDate;
	private Date takenDate;
	private String geoInfo;
	public String getGeoInfo() {
		return geoInfo;
	}
	public void setGeoInfo(String geoInfo) {
		this.geoInfo = geoInfo;
	}
	private Owner owner;
	private String genre;
	private String url;
	private boolean isPublic;
	private boolean isFriend;
	private boolean isFamily;
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public boolean isFriend() {
		return isFriend;
	}
	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	public boolean isFamily() {
		return isFamily;
	}
	public void setFamily(boolean isFamily) {
		this.isFamily = isFamily;
	}
	
}
