/**
 * 
 */
package com.example.quting.utils;

import java.io.File;

import com.example.quting.database.DatabaseHelper;
import com.example.quting.entity.ViewHolder;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
public class SDCardUtil {
	// 判断sdcard是否插入
	public static boolean checkStorageState() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String defaultPath(){
		if(checkStorageState()){
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}
}
