package com.example.quting;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

import com.example.quting.utils.CrashHandler;

/**
 *  全局
 * */
public class QuTingApplication extends Application{
	private ArrayList<Activity> activityList = new ArrayList<Activity>();
	@Override
	public void onCreate() {
		super.onCreate();
		/**
		 * 判断首次登录，异常退出也要修改，留作以后接口
		 * */
		/*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Editor pEdit = prefs.edit();
        pEdit.putBoolean("first_time", true);
        pEdit.commit();*/
		CrashHandler crashHandler = CrashHandler.getInstance() ;
		crashHandler.init(this) ;
	}
	public void closeAllActivity() {
		for (Activity activity : activityList) {
			if (activity != null) {
				activity.finish();
			}
		}
		System.exit(0);
	}
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
}
