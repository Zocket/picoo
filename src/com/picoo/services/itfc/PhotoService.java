package com.picoo.services.itfc;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.picoo.model.PicooPhotoList;
import com.picoo.model.itfc.PhotoList;

public interface PhotoService {
	public InputStream getStreamFromService(HttpServletRequest req);
	public PhotoList getPhotoListFromStream(InputStream is);
	public PicooPhotoList getPicooPhotoList(PhotoList pl);
	public PicooPhotoList getPicooPhotoListFromService(HttpServletRequest req);
}
