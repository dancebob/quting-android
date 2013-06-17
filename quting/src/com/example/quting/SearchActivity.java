package com.example.quting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SearchActivity extends Activity {
	
	 private ListView search_list;
	 private SearchAdapter adapter;
	
     protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 setContentView(R.layout.activity_search);
    	 
    	 search_list = (ListView)findViewById(R.id.search_listview);
    	 adapter = new SearchAdapter(getApplicationContext());
    	 search_list.setAdapter(adapter);
     };
     
     public void onBackMainClicked(View view){
 		Intent intent = new Intent(this,MainActivity.class);
 		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
 		startActivity(intent);
     	if(version >= 5) {   
     	     overridePendingTransition(R.anim.slid_up, R.anim.slid_down);
     	}
     }
}
