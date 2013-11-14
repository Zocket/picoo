package com.picoo.services.itfc;


//import com.picoo.services.impl.FlickrPhotoServiceFactory;

public abstract class PhotoServiceFactory {
	public static final int SERVICEFLICKR=0;
	public static final int SERVICEGIMAGE=1;
	public static final int SERVICEFB=2;

	public static Class<?> getPhotoServiceFactory(int serviceSource){
		Class<?> psf=null;
		try{
		switch(serviceSource){
			case SERVICEFLICKR:
				psf=Class.forName("com.picoo.services.impl.FlickrPhotoServiceFactory");
				break;
			case SERVICEGIMAGE:
				break;
			case SERVICEFB:
				break;
			default:
				psf=Class.forName("com.picoo.services.impl.FlickrPhotoServiceFactory");
				break;
		}}catch(ClassNotFoundException e){
			System.out.println(e.getStackTrace());
		}
		return psf;
	}
	
	public static Class<?> getPhotoServiceFactory(){
		return getPhotoServiceFactory(SERVICEFLICKR);
	}
	

	public abstract PhotoService getPhotoService();
	public abstract AuthService getAuthService();
}
