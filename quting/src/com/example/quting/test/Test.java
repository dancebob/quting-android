package com.example.quting.test;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.test.AndroidTestCase;

import com.example.quting.database.DatabaseHelper;
import com.example.quting.database.SqlLiteUtil;
import com.example.quting.entity.ViewHolder;
import com.example.quting.entity.category.CategoryEntity;
import com.example.quting.entity.media.MediaBaseEntity;
import com.example.quting.entity.media.MediaEntity;
import com.example.quting.entity.mp3.Mp3BaseEntity;
import com.example.quting.entity.mp3.Mp3Entity;
import com.example.quting.image.ApacheUtility;
import com.example.quting.media.MyMediaPlayer;
import com.example.quting.resource.CategoryInfo;
import com.example.quting.resource.MediaInfo;
import com.example.quting.resource.Mp3Info;

public class Test extends AndroidTestCase {
	/**
	 * 获取mediaList
	 * */
	public void testLoadMediaList() throws Throwable{
		try{
			String myurl = "http://t.pamakids.com/api/media?page=1";
			MediaEntity entity = MediaInfo.getInstance().findMediaListFromNet(myurl);
			// 返回一个list集合 System.out.println(entity.getMedia());
		}catch(Exception e){
			e.toString();
		}
	}
	/**
	 * 异步加载图片
	 * */
	public void testLoadMediaImage() throws Throwable{
		try{
			final String myurl = "http://www.huaxiazi.com/ProductImages/20091211110917.jpg";
			  new Thread(){
				  	@Override
				    public void run(){
				      Bitmap drawable=ApacheUtility.GetBitmapByUrl(myurl); // 调用前面 ApacheUtility 类的方法
				      System.out.println("AAAA "+drawable);
				    }
				  }.start();
				   
		}catch(Exception e){
			e.toString();
		}
	}
	
	/**
	 * 获取mp3info list
	 * */
	public void testLoadMp3InfoList() throws Throwable{
		try{
			String myurl ="http://t.pamakids.com/api/mfiles?medium_id=5";
			Mp3Entity entity = Mp3Info.getInstance().findMp3ListFromNet(myurl);
			System.out.println("mp3info  "+entity.getMfiles().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取分类列表
	 * */
	public void testLoadMediaCategray() throws Throwable{
		String myurl = "http://t.pamakids.com/api/media?page=1";
		MediaInfo.getInstance().findMediaListFromNet(myurl);
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(0).size());
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(1).size());
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(2).size());
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(3).size());
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(4).size());
		System.out.println("categray size "+MediaInfo.getInstance().swichMediaBaseEntity(5).size());
	}
	
	public void testPlayMp3() throws Throwable{
		String myurl = "http://t.pamakids.com/api/mfiles?medium_id=4";
		//http://t.pamakids.com/mp3/1.mp3
		List<Mp3BaseEntity> list = Mp3Info.getInstance().findMp3ListFromNet(myurl).getMfiles();
		List<String> listUrl = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			String str = list.get(i).getUrl().substring(6, list.get(i).getUrl().length());
			listUrl.add("http://t.pamakids.com"+str);
		}
		MyMediaPlayer player = new MyMediaPlayer(listUrl);
		player.start();
	}
	
	public void testDB() throws Throwable{
		SqlLiteUtil.getMainSQLiteDatabase(getContext());
		MediaBaseEntity model = new MediaBaseEntity();
		model.setZuiai(1);
		model.setId(3+"");
		model.setAuthor("CC");
		model.setImagepath("AAAA");
		/**
		 * 增加订阅  setDingyue(1)
		 * 增加最爱  setZuiai(1);
		 * 增加最近  setZuijin(1);
		 * */
		SqlLiteUtil.insertMediaBaseEntity(model);
		System.out.println("add  "+model.getId());
		//删//改
		/**
		 * 删除订阅  setDingyue(0)
		 * 删除最爱  setZuiai(0);
		 * 删除最近  setZuijin(0);
		 * 
		 * 没有需要修改的东西，删除就是修改
		 * */
		model.setAuthor("DD");
		SqlLiteUtil.updateMediaBaseEntity(model);
		System.out.println("update  "+model.getId());
		
		
		//查询订阅
		ArrayList<MediaBaseEntity> list = SqlLiteUtil.getDingyue();
		//查询最近
		SqlLiteUtil.getZuijin();
		//查询最爱
		list = SqlLiteUtil.getZuiai();
		for(int i=0;i<list.size();i++){
			System.out.println("select  "+list.get(i).getId());
		}
		/**
		 * 1已经增加了订阅
		 * 还是这个Media,也需要增加到最爱
		 * */
		if(model.getId() != null){
			MediaBaseEntity entity = SqlLiteUtil.getOneMediaBaseEntity(Integer.parseInt(model.getId()));
			if(entity != null){
				SqlLiteUtil.updateMediaBaseEntity(model);
			}
		}
	}
	
	/**
	 * 获取category list
	 * */
	public void testLoadCategoryList() throws Throwable{
		try{
			String myurl ="http://t.pamakids.com/api/categories";
			CategoryEntity entity = CategoryInfo.getInstance().findCategoryFromNet(myurl);
			System.out.println("categoryList size()  "+entity.getCategories().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
