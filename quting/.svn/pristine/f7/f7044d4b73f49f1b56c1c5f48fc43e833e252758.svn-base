package com.example.quting.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.example.quting.entity.guest.User;
import com.example.quting.entity.guest.UserRegisterEntity;
import com.example.quting.entity.media.MediaBaseEntity;

public class UserRegistrationInfo {
public List<MediaBaseEntity> list = new ArrayList<MediaBaseEntity>();
	
	private static UserRegistrationInfo instance;
	private static Object lock_object = new Object();
	public static UserRegistrationInfo getInstance(){
		if(instance == null){
			synchronized (lock_object) {
				instance = new UserRegistrationInfo();
			}
		}
		return instance;
	}
	private UserRegistrationInfo(){
	}
	/**
	 * 获取mediaList
	 * */
	public UserRegisterEntity findUserInfoFromNet(String myurl) throws Throwable{
		if(list != null && list.size()>0){
			list.clear();
		}
		String JsonString = null;
		JSONObject title = null;
		URL url = new URL(myurl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buffer = new BufferedReader(in);
		JsonString = buffer.readLine();
		urlConn.disconnect();
		
		title = new JSONObject(JsonString);
		UserRegisterEntity entity = new UserRegisterEntity();
		User user = new User();
		title = title.getJSONObject("guest");
		if(title != null){
			user.setId(""+title.getInt("id"));
			user.setCreated_at(title.getString("created_at"));
			user.setDevice_token(title.getString("device_token"));
			user.setUpdated_at(title.getString("updated_at"));
		}
		entity.setGuest(user);
		return entity;
	}
}
