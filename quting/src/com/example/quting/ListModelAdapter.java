package com.example.quting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListModelAdapter extends BaseAdapter {
	
	 private LayoutInflater mInflater;                 
	 private Context context;                           
	 
	 public ListModelAdapter(Context context){
		 this.context = context;
		 
		 mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	 // 测试数据
     private Integer[] favImgs = {  
        R.drawable.unfav2,  
        R.drawable.fav2,  
        R.drawable.unfav2,  
        R.drawable.fav2
     }; 
     
	 // 类别
     private Integer[] listModels = {  
        R.string.listmodel1,  
        R.string.listmodel1,  
        R.string.listmodel1,  
        R.string.listmodel1
     }; 
     
	 // 段子
     private Integer[] listDuans = {  
        R.string.listduanzi1,  
        R.string.listduanzi1,  
        R.string.listduanzi1,  
        R.string.listduanzi1
     }; 
     
	 // 时间
     private Integer[] listTime = {  
        R.string.listtime1,  
        R.string.listtime1,  
        R.string.listtime1,  
        R.string.listtime1
     }; 
	 
	@Override
	public int getCount() {
		return favImgs.length;
	}

	@Override
	public Object getItem(int position) {
		return favImgs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(R.layout.list_model_item, null);
        }
        
        ImageView iv_header = (ImageView) view.findViewById(R.id.fav_icon);
        iv_header.setImageResource(favImgs[position]);
        TextView tv_lei = (TextView) view.findViewById(R.id.leibie);
        tv_lei.setText(listModels[position]);
        TextView tv_duan = (TextView) view.findViewById(R.id.duanzi);
        tv_duan.setText(listDuans[position]);
        TextView tv_time = (TextView) view.findViewById(R.id.alltime);
        tv_time.setText(listTime[position]);
        
		return view;
	}

}
