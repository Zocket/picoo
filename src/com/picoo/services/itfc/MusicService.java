package com.picoo.services.itfc;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.picoo.model.PicooSongList;
import com.picoo.model.itfc.SongList;

public interface MusicService {
	public InputStream getStreamFromService(HttpServletRequest req);
	public SongList getSongListFromStream(InputStream is);
	public PicooSongList getPicooSongList(SongList pl);
	public PicooSongList getPicooSongListFromService(HttpServletRequest req);
}
