package com.example.quting;

import com.example.quting.SegmentBar.OnSegmentBarChangedListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MyFavActivity extends Activity {
	
	 private ListView fav_list;
	 private MyFavAdapter adapter;  // ���Ե� adapter
	 private SegmentBar segmentBar;
	
     protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 setContentView(R.layout.activity_myfav);
    	 
    	 fav_list = (ListView)findViewById(R.id.fav_listview);
    	 adapter = new MyFavAdapter(getApplicationContext());
    	 fav_list.setAdapter(adapter);
    	 
    	 segmentBar = (SegmentBar)findViewById(R.id.segment_bar);
    	 segmentBar.setValue(MyFavActivity.this, new String[]{"", ""});
//         segmentBar.setTextSize(16);
//         segmentBar.setTextColor(Color.parseColor("#707070"));
         segmentBar.setDefaultBarItem(0);
         segmentBar.setOnSegmentBarChangedListener(new OnSegmentBarChangedListener(){
 			@Override
 			public void onBarItemChanged(int segmentItemIndex){
 				Log.i("onBarItemChangedonBarItemChanged",segmentItemIndex+""); // 设置 adapter  切换数据
 				if (segmentItemIndex==0) {
 					fav_list.setAdapter(adapter);
				}else {
					fav_list.setAdapter(new MyRecnAdapter(MyFavActivity.this));
				}
 			}
 		});
     };
     
     public void onBackMainClicked(View view){
 		Intent intent = new Intent(this,MainActivity.class);
 		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
 		startActivity(intent);
     	if(version >= 5) {   
     		overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
     	}
     }
}
