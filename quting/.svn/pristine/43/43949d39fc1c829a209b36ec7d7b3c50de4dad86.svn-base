package com.example.quting.utils;

import com.example.quting.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ViewOnClickedListener implements OnClickListener {
	private Context context;
	private int id;
	
	public ViewOnClickedListener(Context context,int id){
		this.context = context;
		this.id = id;
	}

	@Override
	public void onClick(View v) {
	  switch (v.getId()) {
		case R.id.play_state:
			playWhich(id);
			break;
	
		default:
			break;
	  }
	}
	
	public void playWhich(int id){
		switch (id) {
			case 0:  // 这个是主界面 收藏的类别 id
				Toast.makeText(context, "播放"+id, 3).show();
				break;
	
			default:
				break;
		}
	}

}
