package com.picoo.model.flickr;

import java.util.List;

import com.google.gson.annotations.*;
import com.picoo.model.itfc.PhotoList;

public class FlickrPhotoList extends PhotoList {
	private int page;
	private int pages;
	private int perpage;
	private int total;
	private String stat;
	@SerializedName("photo")
	private List<FlickrPhoto> photos;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public List<FlickrPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<FlickrPhoto> photos) {
		this.photos = photos;
	}
}
