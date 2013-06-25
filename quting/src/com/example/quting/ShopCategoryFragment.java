package com.example.quting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.quting.database.SqlLiteUtil;
import com.example.quting.entity.media.MediaBaseEntity;
import com.example.quting.resource.MediaInfo;
import com.example.quting.utils.BitmapTool;
import com.example.quting.utils.SDCardUtil;
import com.example.quting.utils.SDCardUtils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

// 适配器中的每一项
@SuppressLint("ValidFragment")
public class ShopCategoryFragment extends Fragment{

    private String text;
	private GridView gridView;
	private ShopAdapter adapter;
	
	private Context context;
	
	private List<MediaBaseEntity> mediaList;
	private MediaBaseEntity mediaBaseEntity;
	
    public ShopCategoryFragment(Context context,ShopAdapter adapter){
        super();
        this.context = context;
        this.adapter = adapter;
		mediaList = MediaInfo.getInstance().swichMediaBaseEntity(adapter.getFlag());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_view_category, container, false);
        gridView = (GridView)v.findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {  
			@Override
			public void onItemClick(AdapterView parent, View view, int position, long id) {
				mediaBaseEntity = mediaList.get(position);  // 获取实体类
				//Log.i("mediaBaseEntitymediaBaseEntity", ""+mediaBaseEntity.getName());
				showItemDialog(mediaBaseEntity.getMtype(),mediaBaseEntity.getName(),mediaBaseEntity.getDescription());
			}  
		});
        return v;
    }
    
    public void showItemDialog(String imgUrl,String titleStr,String inStr){
    	final AlertDialog dlg = new AlertDialog.Builder(context).create();
    	dlg.show();
    	Window window = dlg.getWindow();
    	window.setContentView(R.layout.shop_item_dialog);
    	ImageView order = (ImageView) window.findViewById(R.id.img_order);
    	OnClickListener listener = new OnClickListener(){
			public void onClick(View view){  // 添加订阅功能
	        	 switch(view.getId()){
		        	 case R.id.img_order: // 把图片保存到sdcard
		        		 if (isOrder(mediaBaseEntity.getId())) {
		        			 if (SDCardUtil.checkStorageState()) {
			        			 Bitmap bitmap = BitmapTool.zoomBitmap(BitmapTool.getNetBitmap(mediaBaseEntity.getMtype()),190,190);
			        			 String fileDateName = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date()).toString();
			        			 SDCardUtils.getInstance().saveBmpToSd(bitmap,fileDateName+".jpg","quting");
			        			 mediaBaseEntity.setImagepath(SDCardUtil.defaultPath()+"/quting/"+fileDateName+".jpg");
			        			 dlg.dismiss();
							 }
			        		 mediaBaseEntity.setDingyue(1);
			        		 SqlLiteUtil.getMainSQLiteDatabase(context);
			        		 SqlLiteUtil.insertMediaBaseEntity(mediaBaseEntity);
						 }else {
							Toast.makeText(context, "已经订阅过了", 3).show();
						}
		             break;				        	 
	        	 }
	         }
		};
		order.setOnClickListener(listener);
		ImageView circleImageView = (ImageView)window.findViewById(R.id.img_circle);
		circleImageView.setImageBitmap(BitmapTool.getNetBitmap(imgUrl));
		
		TextView tite = (TextView)window.findViewById(R.id.title);
		tite.setText(titleStr);
		
		TextView info = (TextView)window.findViewById(R.id.info);
		info.setText(inStr);
		info.setMovementMethod(ScrollingMovementMethod.getInstance());  //设置一个滚动实例
    }    
    
    // 是否已经订阅过
    public boolean isOrder(String id){
    	SqlLiteUtil.getMainSQLiteDatabase(context);
    	ArrayList<MediaBaseEntity> list = SqlLiteUtil.getDingyue(); // 查询订阅
    	for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {  // 比较media 的id
				return false;
			}
		}
    	return true;
    }
// 	public void onShopPlayStateClicked(View view){
// 		Log.i("onPlayStateClicked", "dddd");
// 	}
}
