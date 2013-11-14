package com.picoo.services.itfc;

import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.picoo.model.PicooPhotoList;
import com.picoo.model.itfc.PhotoList;

public interface PhotoService {
	public InputStreamReader getStreamFromService(HttpServletRequest req);
	public PhotoList getPhotoListFromStream(InputStreamReader isr);
	public PicooPhotoList getPicooPhotoList(PhotoList pl);
	public PicooPhotoList getPicooPhotoListFromService(HttpServletRequest req);
}
