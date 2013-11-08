package com.picoo.services.itfc;

import java.io.InputStreamReader;

import com.picoo.model.PicooPhotoList;
import com.picoo.model.itfc.PhotoList;

public interface PhotoService {
	public InputStreamReader invokeRESTService();
	public PhotoList getPhotoListFromStream(InputStreamReader isr);
	public PicooPhotoList getPicooPhotoList(PhotoList pl);
}
