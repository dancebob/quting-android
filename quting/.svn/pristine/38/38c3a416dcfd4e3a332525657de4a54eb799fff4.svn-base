package com.example.quting.database;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quting.database.inf.IDatabaseConnection;

public class AndroidConnection implements IDatabaseConnection{
	/**
	 * sqlDatabase	安卓数据库实例实例
	 * databasePath	安卓数据库路径地址
	 */
	private static SQLiteDatabase sqlDatabase;
	private final String databasePath = "/sdcard/quting" ;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public SQLiteDatabase open( String databaseName ) throws Exception {
		//sqlDatabase = SQLiteDatabase.openDatabase( databasePath + File.separator + databaseName + ".db" , null , SQLiteDatabase.OPEN_READWRITE );
		sqlDatabase = SQLiteDatabase.openOrCreateDatabase( databasePath + File.separator + databaseName + ".db" , null );
		return sqlDatabase;
	}
	@Override
	public void close() throws Exception {
		if ( sqlDatabase != null )
			sqlDatabase.close();
	}
	@Override
	public void beginTransaction() throws Exception {
		if ( sqlDatabase != null )
			sqlDatabase.beginTransaction();
	}
	@Override
	public void commit() throws Exception {
		if ( sqlDatabase != null ){
			sqlDatabase.setTransactionSuccessful();
		}
	}
	@Override
	public void endTransation() throws Exception {
		if ( sqlDatabase != null ){
			sqlDatabase.endTransaction();
		}
	}
	@Override
	public long insert( String table,
			HashMap<String, Object> columAndValue) throws Exception {
		if ( sqlDatabase == null )
			return -1l;
		else if ( columAndValue != null &&  columAndValue.size() > 0 ){
			ContentValues cv = new ContentValues();
			for ( String keyObject : columAndValue.keySet() ){
				Object obj = columAndValue.get(keyObject);
				if ( obj instanceof Long )
						cv.put( keyObject , (Long) columAndValue.get(keyObject));
				else if ( obj instanceof String )
						cv.put( keyObject , (String) columAndValue.get(keyObject));
				else if ( obj instanceof Short )
						cv.put( keyObject , (Short) columAndValue.get(keyObject));
				else if ( obj instanceof Integer )
						cv.put( keyObject , (Integer) columAndValue.get(keyObject));
				else if ( obj instanceof Boolean )
						cv.put( keyObject , ( Boolean ) columAndValue.get(keyObject));
				else if ( obj instanceof Date )
						cv.put( keyObject , (String) columAndValue.get(keyObject));
				else if ( obj instanceof Double )
						cv.put( keyObject , (Double) columAndValue.get(keyObject));
				else if ( obj instanceof Float )
					cv.put( keyObject , (Float) columAndValue.get(keyObject));
				else if ( obj instanceof Byte )
					cv.put( keyObject , (Byte) columAndValue.get(keyObject));
				
			}
			long id = sqlDatabase.insert(table, null, cv);
			return id; 
		}
		return -1l;
	}
	@Override
	public long delete( String table, String idName , long id)
			throws Exception {
		if ( sqlDatabase == null )
			return -1l;
		else if ( id > 0 ){
			return sqlDatabase.delete(table, idName + "=?", new String[]{String.valueOf( id )});
		}
		return -1l;
	}
	@Override
	public List<Map<String, Object>> query( String table, boolean distinct , String[] columns , String where , String[] whereArgs ,
			String groupBy , String having , String orderBy , String limit ) throws Exception {
		if ( sqlDatabase == null )
			return null;
		else {
			Cursor cursor = sqlDatabase.query(distinct, table, columns , where , whereArgs, groupBy, having, orderBy, limit);
			if ( cursor != null && cursor.getCount() > 0){
				ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				while ( cursor.moveToNext() ){
					HashMap< String , Object > hashMap = new HashMap< String , Object >();
					for ( int i = 0 ; i < cursor.getColumnCount() ; i++ ){
						String columnName = cursor.getColumnName(i);
						hashMap.put( columnName, cursor.getString(i) );
					}
					mapList.add(hashMap);
				}
				return mapList;
			}
		}
		return null;
	}
	@Override
	public long update( String table, String idName , Long id,
			HashMap<String, Object> columAndValue ) throws Exception {
		if ( sqlDatabase == null )
			return -1l;
		else if ( id > 0){
			ContentValues cv = new ContentValues();
			for ( String keyObject : columAndValue.keySet() ){
				Object obj = columAndValue.get(keyObject);
				if ( obj instanceof Long )
						cv.put( keyObject , (Long) columAndValue.get(keyObject));
				else if ( obj instanceof String )
						cv.put( keyObject , (String) columAndValue.get(keyObject));
				else if ( obj instanceof Short )
						cv.put( keyObject , (Short) columAndValue.get(keyObject));
				else if ( obj instanceof Integer )
						cv.put( keyObject , (Integer) columAndValue.get(keyObject));
				else if ( obj instanceof Boolean )
						cv.put( keyObject , ( Boolean ) columAndValue.get(keyObject));
				else if ( obj instanceof Date )
						cv.put( keyObject , (String) columAndValue.get(keyObject));
				else if ( obj instanceof Double )
						cv.put( keyObject , (Double) columAndValue.get(keyObject));
				else if ( obj instanceof Float )
					cv.put( keyObject , (Float) columAndValue.get(keyObject));
				else if ( obj instanceof Byte )
					cv.put( keyObject , (Byte) columAndValue.get(keyObject));
				
			}
			return sqlDatabase.update(table, cv , idName + "=?" , new String[]{ String.valueOf(id)});
		}
		return -1l;
	}

	@Override
	public void rollBack() throws Exception {
		
	}

	@Override
	public void execSQL(String sql) throws Exception {
		sqlDatabase.execSQL(sql);
		
	}

}
