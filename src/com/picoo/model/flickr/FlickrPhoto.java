package com.picoo.model.flickr;

import com.picoo.model.itfc.Photo;

public class FlickrPhoto extends Photo{

	private String owner;
	private String secret;
	private String server;

	private int farm;

	private int ispublic;
	private int isfriend;
	private int isfamily;

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}

	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}

	public int getIspublic() {
		return ispublic;
	}
	public void setIspublic(int ispublic) {
		this.ispublic = ispublic;
	}
	public int getIsfriend() {
		return isfriend;
	}
	public void setIsfriend(int isfriend) {
		this.isfriend = isfriend;
	}
	public int getIsfamily() {
		return isfamily;
	}
	public void setIsfamily(int isfamily) {
		this.isfamily = isfamily;
	}
}
