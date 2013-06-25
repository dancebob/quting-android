package com.example.quting.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quting.entity.ViewHolder;
import com.example.quting.entity.media.MediaBaseEntity;

public class SqlLiteUtil {
	static SQLiteDatabase sqLiteDatabase = null;
	static DatabaseHelper dbHelper = null;
	// 保存sqllite 的表字段
	private static Map<String, String[]> tableMap = new HashMap<String, String[]>();
	// 保存sqllite的表名称
	private static String[] databaseTable = new String[] {
			"MediaBaseZuiAiEntity", "MediaBaseDingYueEntity",
			"MediaBaseZuiJingEntity" };
	static {
		tableMap.put(databaseTable[0], new String[] { "author", "category",
				"created_at", "description", "id", "is_like", "jishu", "mtype",
				"name", "time", "updated_at", "updatetime", "url", "yanbo",
				"zuijin", "dingyue", "zuiai","imagepath" });
		tableMap.put(databaseTable[1], new String[] { "author", "category",
				"created_at", "description", "id", "is_like", "jishu", "mtype",
				"name", "time", "updated_at", "updatetime", "url", "yanbo",
				"zuijin", "dingyue", "zuiai" });
		tableMap.put(databaseTable[2], new String[] { "author", "category",
				"created_at", "description", "id", "is_like", "jishu", "mtype",
				"name", "time", "updated_at", "updatetime", "url", "yanbo",
				"zuijin", "dingyue", "zuiai" });
	}

	public static SQLiteDatabase getMainSQLiteDatabase(Context context) {
		if (null == sqLiteDatabase)
			try {
				dbHelper = new DatabaseHelper(context, ViewHolder.DATABASE);
				sqLiteDatabase = dbHelper.getWritableDatabase();
			} catch (Exception e) {
				return null;
			}
		return sqLiteDatabase;
	}

	/**
	 * 订阅保存,dingyue为1是订阅,最爱为1，最近为1，
	 * */
	public static long insertMediaBaseEntity(MediaBaseEntity model) {
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			ContentValues initialValues = new ContentValues();
			/**
			 * "author" ,
			 * "category","created_at","description","id","is_like","jishu"
			 * ,"mtype"
			 * ,"name","time","updated_at","updatetime","url","yanbo","zuijin"
			 * ,"dingyue","zuiai"
			 * */
			initialValues.put(tableMap.get(databaseTable[0])[0],
					model.getAuthor());
			initialValues.put(tableMap.get(databaseTable[0])[1],
					model.getCategory());
			initialValues.put(tableMap.get(databaseTable[0])[2],
					model.getCreated_at());
			initialValues.put(tableMap.get(databaseTable[0])[3],
					model.getDescription());
			initialValues.put(tableMap.get(databaseTable[0])[4], model.getId());
			initialValues.put(tableMap.get(databaseTable[0])[5],
					model.getIs_like());
			initialValues.put(tableMap.get(databaseTable[0])[6],
					model.getJishu());
			initialValues.put(tableMap.get(databaseTable[0])[7],
					model.getMtype());
			initialValues.put(tableMap.get(databaseTable[0])[8],
					model.getName());
			initialValues.put(tableMap.get(databaseTable[0])[9],
					model.getTime());
			initialValues.put(tableMap.get(databaseTable[0])[10],
					model.getUpdated_at());
			initialValues.put(tableMap.get(databaseTable[0])[11],
					model.getUpdatetime());
			initialValues.put(tableMap.get(databaseTable[0])[12],
					model.getUrl());
			initialValues.put(tableMap.get(databaseTable[0])[13],
					model.getYanbo());
			initialValues.put(tableMap.get(databaseTable[0])[14],
					model.getZuiijn());
			initialValues.put(tableMap.get(databaseTable[0])[15],
					model.getDingyue());
			initialValues.put(tableMap.get(databaseTable[0])[16],
					model.getZuiai() );
			initialValues.put(tableMap.get(databaseTable[0])[17],
					model.getImagepath() );
			Long result = db.insert(databaseTable[0], null, initialValues);

			//if(db.isOpen()){db.close();}
			return result;
		}
		return -1;
	}

	/**
	 *  暂时用不到
	 * */
	public static boolean removeDingyue(String id) {
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			int count = db.delete(databaseTable[0], "ID= ?", new String[] { id
					+ "" });
			if (count > 0) {
				return true;
			}
		}
		//if(db.isOpen()){db.close();}
		return false;
	}
	/**
	 *  订阅列表
	 * */
	public static ArrayList<MediaBaseEntity> getDingyue() {
		ArrayList<MediaBaseEntity> infoList = new ArrayList<MediaBaseEntity>();
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if(db != null && !db.isOpen()){
		}
		
		if (db != null) {
			Cursor cursor = db.query(databaseTable[0],
					tableMap.get(databaseTable[0]),
					tableMap.get(databaseTable[0])[15] + "=?",
					new String[] { String.valueOf(1 + "") }, null, null, "id");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					MediaBaseEntity info = new MediaBaseEntity();
					/**
					 * 
					 * "author" ,
					 * "category","created_at","description","id","is_like"
					 * ,"jishu"
					 * ,"mtype","name","time","updated_at","updatetime",
					 * "url","yanbo","zuijin","dingyue","zuiai"
					 * */
					info.setAuthor(cursor.getString(0));
					info.setCategory(cursor.getString(1));
					info.setCreated_at(cursor.getString(2));
					info.setDescription(cursor.getString(3));
					info.setId(cursor.getString(4));
					info.setIs_like(cursor.getString(5));
					info.setJishu(cursor.getString(6));
					info.setMtype(cursor.getString(7));
					info.setName(cursor.getString(8));
					info.setTime(cursor.getString(9));
					info.setUpdated_at(cursor.getString(10));
					info.setUpdatetime(cursor.getString(11));
					info.setUrl(cursor.getString(12));
					info.setYanbo(cursor.getString(13));
					info.setZuiijn(cursor.getString(14) != null?Integer.parseInt(cursor.getString(14)):0);
					info.setDingyue(cursor.getString(15) != null?Integer.parseInt(cursor.getString(15)):0);
					info.setZuiai(cursor.getString(16) != null?Integer.parseInt(cursor.getString(16)):0);
					info.setImagepath(cursor.getString(17));
					infoList.add(info);
				}
			}
			if (null != cursor)
				cursor.close();
			//if(db.isOpen()){db.close();}
		}
		return infoList;
	}
	/**
	 * 最近列表
	 * */
	public static ArrayList<MediaBaseEntity> getZuijin() {
		ArrayList<MediaBaseEntity> infoList = new ArrayList<MediaBaseEntity>();
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			Cursor cursor = db.query(databaseTable[0],
					tableMap.get(databaseTable[0]),
					tableMap.get(databaseTable[0])[14] + "=?",
					new String[] { String.valueOf(1 + "") }, null, null, "id");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					MediaBaseEntity info = new MediaBaseEntity();
					/**
					 * 
					 * "author" ,
					 * "category","created_at","description","id","is_like"
					 * ,"jishu"
					 * ,"mtype","name","time","updated_at","updatetime",
					 * "url","yanbo","zuijin","dingyue","zuiai"
					 * */
					info.setAuthor(cursor.getString(0));
					info.setCategory(cursor.getString(1));
					info.setCreated_at(cursor.getString(2));
					info.setDescription(cursor.getString(3));
					info.setId(cursor.getString(4));
					info.setIs_like(cursor.getString(5));
					info.setJishu(cursor.getString(6));
					info.setMtype(cursor.getString(7));
					info.setName(cursor.getString(8));
					info.setTime(cursor.getString(9));
					info.setUpdated_at(cursor.getString(10));
					info.setUpdatetime(cursor.getString(11));
					info.setUrl(cursor.getString(12));
					info.setYanbo(cursor.getString(13));
					info.setZuiijn(cursor.getString(14) != null?Integer.parseInt(cursor.getString(14)):0);
					info.setDingyue(cursor.getString(15) != null?Integer.parseInt(cursor.getString(15)):0);
					info.setZuiai(cursor.getString(16) != null?Integer.parseInt(cursor.getString(16)):0);
					info.setImagepath(cursor.getString(17));
					infoList.add(info);
				}
			}
			if (null != cursor)
				cursor.close();
			//if(db.isOpen()){db.close();}
		}
		return infoList;
	}
	/**
	 * 最爱列表
	 * */
	public static ArrayList<MediaBaseEntity> getZuiai() {
		ArrayList<MediaBaseEntity> infoList = new ArrayList<MediaBaseEntity>();
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			Cursor cursor = db.query(databaseTable[0],
					tableMap.get(databaseTable[0]),
					tableMap.get(databaseTable[0])[16] + "=?",
					new String[] { String.valueOf(1 + "") }, null, null, "id");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					MediaBaseEntity info = new MediaBaseEntity();
					/**
					 * 
					 * "author" ,
					 * "category","created_at","description","id","is_like"
					 * ,"jishu"
					 * ,"mtype","name","time","updated_at","updatetime",
					 * "url","yanbo","zuijin","dingyue","zuiai"
					 * */
					info.setAuthor(cursor.getString(0));
					info.setCategory(cursor.getString(1));
					info.setCreated_at(cursor.getString(2));
					info.setDescription(cursor.getString(3));
					info.setId(cursor.getString(4));
					info.setIs_like(cursor.getString(5));
					info.setJishu(cursor.getString(6));
					info.setMtype(cursor.getString(7));
					info.setName(cursor.getString(8));
					info.setTime(cursor.getString(9));
					info.setUpdated_at(cursor.getString(10));
					info.setUpdatetime(cursor.getString(11));
					info.setUrl(cursor.getString(12));
					info.setYanbo(cursor.getString(13));
					info.setZuiijn(cursor.getString(14) != null?Integer.parseInt(cursor.getString(14)):0);
					info.setDingyue(cursor.getString(15) != null?Integer.parseInt(cursor.getString(15)):0);
					info.setZuiai(cursor.getString(16) != null?Integer.parseInt(cursor.getString(16)):0);
					info.setImagepath(cursor.getString(17));
					infoList.add(info);
				}
			}
			if (null != cursor)
				cursor.close();
			//if(db.isOpen()){db.close();}
		}
		return infoList;
	}
	
	/**
	 * 返回一个MediaBaseEntity
	 * */
	public static MediaBaseEntity getOneMediaBaseEntity(int id) {
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			Cursor cursor = db.query(databaseTable[0],
					tableMap.get(databaseTable[0]),
					tableMap.get(databaseTable[0])[4] + "=?",
					new String[] { String.valueOf(id + "") }, null, null, "id");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					MediaBaseEntity info = new MediaBaseEntity();
					/**
					 * 
					 * "author" ,
					 * "category","created_at","description","id","is_like"
					 * ,"jishu"
					 * ,"mtype","name","time","updated_at","updatetime",
					 * "url","yanbo","zuijin","dingyue","zuiai"
					 * */
					info.setAuthor(cursor.getString(0));
					info.setCategory(cursor.getString(1));
					info.setCreated_at(cursor.getString(2));
					info.setDescription(cursor.getString(3));
					info.setId(cursor.getString(4));
					info.setIs_like(cursor.getString(5));
					info.setJishu(cursor.getString(6));
					info.setMtype(cursor.getString(7));
					info.setName(cursor.getString(8));
					info.setTime(cursor.getString(9));
					info.setUpdated_at(cursor.getString(10));
					info.setUpdatetime(cursor.getString(11));
					info.setUrl(cursor.getString(12));
					info.setYanbo(cursor.getString(13));
					info.setZuiijn(cursor.getString(14) != null?Integer.parseInt(cursor.getString(14)):0);
					info.setDingyue(cursor.getString(15) != null?Integer.parseInt(cursor.getString(15)):0);
					info.setZuiai(cursor.getString(16) != null?Integer.parseInt(cursor.getString(16)):0);
					info.setImagepath(cursor.getString(17));
					return info;
				}
			}
			if (null != cursor)
				cursor.close();
			//if(db.isOpen()){db.close();}
		}
		return null;
	}
	
	/**
	 * 更新
	 * */
	public static void updateMediaBaseEntity(MediaBaseEntity model) {
		SQLiteDatabase db = getMainSQLiteDatabase(null);
		if (db != null) {
			ContentValues initialValues = new ContentValues();
			initialValues.put(tableMap.get(databaseTable[0])[0],
					model.getAuthor());
			initialValues.put(tableMap.get(databaseTable[0])[1],
					model.getCategory());
			initialValues.put(tableMap.get(databaseTable[0])[2],
					model.getCreated_at());
			initialValues.put(tableMap.get(databaseTable[0])[3],
					model.getDescription());
			initialValues.put(tableMap.get(databaseTable[0])[4], model.getId());
			initialValues.put(tableMap.get(databaseTable[0])[5],
					model.getIs_like());
			initialValues.put(tableMap.get(databaseTable[0])[6],
					model.getJishu());
			initialValues.put(tableMap.get(databaseTable[0])[7],
					model.getMtype());
			initialValues.put(tableMap.get(databaseTable[0])[8],
					model.getName());
			initialValues.put(tableMap.get(databaseTable[0])[9],
					model.getTime());
			initialValues.put(tableMap.get(databaseTable[0])[10],
					model.getUpdated_at());
			initialValues.put(tableMap.get(databaseTable[0])[11],
					model.getUpdatetime());
			initialValues.put(tableMap.get(databaseTable[0])[12],
					model.getUrl());
			initialValues.put(tableMap.get(databaseTable[0])[13],
					model.getYanbo());
			initialValues.put(tableMap.get(databaseTable[0])[14],
					model.getZuiijn());
			initialValues.put(tableMap.get(databaseTable[0])[15],
					model.getDingyue());
			initialValues.put(tableMap.get(databaseTable[0])[16],
					model.getZuiai() );
			initialValues.put(tableMap.get(databaseTable[0])[17],
					model.getImagepath());
			int result = db.update(databaseTable[0], initialValues," id = ? ", new String[] { model.getId() });
			System.out.println("result  "+result);
			////if(db.isOpen()){db.close();}
		}
	}
}
