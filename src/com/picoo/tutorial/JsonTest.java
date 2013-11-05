package com.picoo.tutorial;
import com.picoo.flickr.FlickrPhoto;
import com.picoo.flickr.FlickrPhotos;
import com.google.gson.*;

public class JsonTest {
	public static void main(String[] args){
		String tststr="{      \"page\": 1,      \"pages\": 10,      \"perpage\": 100,      \"total\": 1000,      \"photo\":       [                  {            \"id\": \"10669391155\",            \"owner\": \"22539613@N05\",            \"secret\": \"2830be6abe\",            \"server\": \"7313\",            \"farm\": 8,            \"title\": \"\",            \"ispublic\": 1,            \"isfriend\": 0,            \"isfamily\": 0         }           ]   }";
		FlickrPhotos fps=new Gson().fromJson(tststr, FlickrPhotos.class);
		System.out.println(fps.getPages());
	for (FlickrPhoto fp : fps.getPhotos()){
			System.out.println(fp.getId());
		}
	}
}
