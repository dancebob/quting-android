package com.example.quting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfigAdapter extends BaseAdapter {
	
	 private LayoutInflater mInflater;                  
	 private Context context;                          
	 
	 public ConfigAdapter(Context context){
		 this.context = context;
		 
		 mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	
    private Integer[] mImageIds = {  
        R.drawable.help2,  
        R.drawable.info2,  
        R.drawable.config2,  
        R.drawable.check2
    }; 
    
    private Integer[] tv_names = {  
        R.string.help,
        R.string.about,
        R.string.set,
        R.string.check
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
            view = mInflater.inflate(R.layout.config_item, null);
        }
        
        ImageView iv_header = (ImageView) view.findViewById(R.id.cofig_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.config_name);
        iv_header.setImageResource(mImageIds[position]);
        tv_name.setText(tv_names[position]);
		return view;
	}

}
