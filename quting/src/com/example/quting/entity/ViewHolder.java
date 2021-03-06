package com.example.quting.entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 
 */

public class ViewHolder {
	public static String canshu = "canshu";
	public static final String DATABASE = "quting_datebase";
	public static final String MYVIDEO_TABLE_NAME = "mylikes";
	public static String baseUrl = "http://t.pamakids.com/api/";
	public static final String mediaUrl = baseUrl + "media?page=";
	
	public static final String mp3Url = baseUrl+"mfiles?medium_id=";
	public static final String searchPageUrl = "&pg=";
	public static final String guestUrl = baseUrl+"guests";
	public static final String likesUrl = baseUrl+"likes";
	public static final String dlikeUrl = baseUrl+"likes?medium_id="+canshu+"&guest_id=";
	public static final String likedeleteUrl = baseUrl+"likes/2";
	public static final String buysUrl = baseUrl+"buys?medium_id="+canshu+"&guest_id=";
	public static final String dbuytUrl = baseUrl+"buys?guest_id=";


	
	public static final String BASE_SONG_CACHE_DIR = "/quting/mp3/";

	public static final String BASE_LRC_CACHE_DIR = "/quting/lrc/";

	public static final String BASE_INFO_CACHE_DIR = "/quting/info/";
}
