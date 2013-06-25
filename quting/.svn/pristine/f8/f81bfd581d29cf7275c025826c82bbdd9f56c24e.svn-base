package com.example.quting.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.quting.entity.category.CategoryBaseEntity;
import com.example.quting.entity.category.CategoryEntity;
import com.example.quting.entity.media.MediaBaseEntity;

public class CategoryInfo {
	private static CategoryInfo instance;
	private static Object lock_object = new Object();
	public static CategoryInfo getInstance(){
		if(instance == null){
			synchronized (lock_object) {
				instance = new CategoryInfo();
			}
		}
		return instance;
	}
	
	private CategoryInfo(){
	}
	
	public CategoryEntity findCategoryFromNet(String myurl)  throws Throwable{
		List<CategoryBaseEntity> list = new ArrayList<CategoryBaseEntity>();
		String JsonString = null;
		JSONObject title = null;
		URL url = new URL(myurl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buffer = new BufferedReader(in);
		JsonString = buffer.readLine();
		urlConn.disconnect();
		
		title = new JSONObject(JsonString);
		
		CategoryEntity category_entity = new CategoryEntity();
		/**
		 * "created_at":"2013-06-20T05:59:55Z",
	     * "id":1,"name":"111","updated_at":"2013-06-20T05:59:55Z"}
		 * 
		 * */
		JSONArray jsonArray = title.getJSONArray("categories");
		if(jsonArray != null && jsonArray.length()>0){
			for(int i=0;i<jsonArray.length();i++){
				CategoryBaseEntity entity = new CategoryBaseEntity();
				JSONObject jsonValue = jsonArray.getJSONObject(i);
				entity.setId(jsonValue.getInt("id"));
				entity.setCreated_at(jsonValue.getString("created_at"));
				entity.setName(jsonValue.getString("name"));
				entity.setUpdated_at(jsonValue.getString("updated_at"));
				if(!list.contains(entity)){
					list.add(entity);
				}
			}
			category_entity.setCategories(list);
			return category_entity;
		}
		return null;
	}
}
