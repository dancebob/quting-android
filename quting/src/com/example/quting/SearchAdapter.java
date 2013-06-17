package com.example.quting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {
	
	 private LayoutInflater mInflater;                   
	 private Context context;                           
	 
	 public SearchAdapter(Context context){
		 this.context = context;
		 
		 mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
    private Integer[] sNmae = {  
        R.string.search1,  
        R.string.search1,  
        R.string.search1,  
        R.string.search1
    };  
    
    private Integer[] sTime = {  
        R.string.search11,  
        R.string.search11,  
        R.string.search11,  
        R.string.search11
    };  
	 
	@Override
	public int getCount() {
		return sNmae.length;
	}

	@Override
	public Object getItem(int position) {
		return sNmae[position];
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
            view = mInflater.inflate(R.layout.search_list_item, null);
        }
        
        TextView tv_name = (TextView)view.findViewById(R.id.get_name);
        tv_name.setText(sNmae[position]);
        TextView tv_time = (TextView)view.findViewById(R.id.get_time);
        tv_time.setText(sTime[position]);
        
		return view;
	}

}
