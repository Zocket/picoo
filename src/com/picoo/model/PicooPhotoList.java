package com.picoo.model;

import java.util.List;

import com.google.gson.Gson;

public class PicooPhotoList {
	private List<PicooPhoto> pphotos;

	public List<PicooPhoto> getPhotos() {
		return pphotos;
	}

	public void setPhotos(List<PicooPhoto> photos) {
		this.pphotos = photos;
	}
	
	public String toString(){
		Gson gson=new Gson();
		return gson.toJson(this);
	}
}
