package com.example.quting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	
	 private LayoutInflater mInflater;                 
	 private Context context;                           
	 
	 public GridAdapter(Context context){
		 this.context = context;
		 
		 mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	    private Integer[] mImageIds = {  
	        R.drawable.img1,  
	        R.drawable.img2,  
	        R.drawable.img3,  
	        R.drawable.img4,  
	        R.drawable.img5,  
	        R.drawable.img6,
	        R.drawable.img1,  
	        R.drawable.img2,  
	        R.drawable.img3,  
	        R.drawable.img4,  
	        R.drawable.img5,  
	        R.drawable.img6
	    };  
	 
	@Override
	public int getCount() {
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mImageIds[position];
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
            view = mInflater.inflate(R.layout.grid_item, null);
        }
        
        ImageView iv_header = (ImageView) view.findViewById(R.id.iv_header);
        //TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        iv_header.setImageResource(mImageIds[position]);
        
		return view;
	}

}
