package com.picoo.services.itfc;


//import com.picoo.services.impl.FlickrPhotoServiceFactory;

public abstract class MusicServiceFactory {
	public static final int SERVICEECNEST=0;

	public abstract MusicService getMusicService();
	public abstract AuthService getAuthService();
	/*public abstract String getRootNodeName();
	public abstract String getBaseUrl();
	public abstract Map<String,String> getParamMap();*/
}
