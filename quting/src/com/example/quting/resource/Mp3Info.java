package com.example.quting.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.quting.entity.mp3.Mp3BaseEntity;
import com.example.quting.entity.mp3.Mp3Entity;

public class Mp3Info {
public List<Mp3BaseEntity> list = new ArrayList<Mp3BaseEntity>();
	
	private static Mp3Info instance;
	private static Object lock_object = new Object();
	public static Mp3Info getInstance(){
		if(instance == null){
			synchronized (lock_object) {
				instance = new Mp3Info();
			}
		}
		return instance;
	}
	private Mp3Info(){
	}
	/**
	 * 获取mediaList
	 * */
	public Mp3Entity findMp3ListFromNet(String myurl) throws Throwable{
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
		
		Mp3Entity mp3_entity = new Mp3Entity();
		JSONArray jsonArray = title.getJSONArray("mfiles");
		if(jsonArray != null && jsonArray.length()>0){
			for(int i=0;i<jsonArray.length();i++){
				Mp3BaseEntity entity = new Mp3BaseEntity();
				JSONObject jsonValue = jsonArray.getJSONObject(i);
				entity.setId(jsonValue.getInt("id"));
				entity.setCreated_at(jsonValue.getString("created_at"));
				entity.setMedium_id(jsonValue.getInt("medium_id"));
				entity.setName(jsonValue.getString("name"));
				entity.setTime(jsonValue.getString("time"));
				entity.setUpdated_at(jsonValue.getString("updated_at"));
				entity.setUrl(jsonValue.getString("url"));
				
				list.add(entity);
			}
		}
		mp3_entity.setMfiles(list);
		return mp3_entity;
	}
	
}
