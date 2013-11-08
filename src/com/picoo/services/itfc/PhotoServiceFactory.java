package com.picoo.services.itfc;

import javax.servlet.ServletContext;

public interface PhotoServiceFactory {
	public static int SERVICEFLICKR=0;
	public static int SERVICEGIMAGE=1;
	public static int SERVICEFB=2;
	public PhotoServiceFactory getPhotoServiceFactory(ServletContext sc);
	public PhotoServiceFactory getPhotoServiceFactory(ServletContext sc,int ServiceSource);
	public PhotoService getPhotoService();
	public AuthService getAuthService();
}
