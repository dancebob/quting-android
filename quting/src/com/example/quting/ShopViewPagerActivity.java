package com.example.quting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.quting.entity.media.MediaBaseEntity;
import com.example.quting.resource.MediaInfo;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShopViewPagerActivity extends FragmentActivity implements OnPageChangeListener{

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> titleList = new ArrayList<String>();
    
    private QuTingApplication application; //程序全局类
    
    protected static final int SUCCESS_GET_CONTACT = 0;
    
    private String myurl = "http://t.pamakids.com/api/media?page=1";
    private File cache;
    
    private ViewPager vp;
    
    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg) {
    		if(msg.what == SUCCESS_GET_CONTACT){
                titleList.add("少儿读物");
                titleList.add("文史经典");
                titleList.add("幽默搞笑");
                titleList.add("健康养生");
                titleList.add("教育管理");
                titleList.add("综艺娱乐");
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),0,cache)));
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),1,cache)));
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),2,cache)));
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),3,cache)));
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),4,cache)));
                fragmentList.add(new ShopCategoryFragment(ShopViewPagerActivity.this,new ShopAdapter(getApplicationContext(),5,cache)));
                
                vp.setAdapter(new myPagerAdapter(getSupportFragmentManager(),fragmentList, titleList));
                vp.setOnPageChangeListener(ShopViewPagerActivity.this);  // 设置监听器
            }
    	};
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_shop);
        
 		application = (QuTingApplication)getApplication();
 		application.addActivity(this);

        vp = (ViewPager)findViewById(R.id.viewPager);
        cache = new File(Environment.getExternalStorageDirectory(), "qutingcache");     //创建缓存目录，系统一运行就得创建缓存目录的，
        if(!cache.exists()){
            cache.mkdirs();
        }
      //获取数据，主UI线程是不能做耗时操作的，所以启动子线程来做
        new Thread(){
            public void run() {
                //子线程通过Message对象封装信息，并且用初始化好的，
                //Handler对象的sendMessage()方法把数据发送到主线程中，从而达到更新UI主线程的目的
                Message msg = new Message();
                try {
					MediaInfo.getInstance().findMediaListFromNet(myurl);
				} catch (Throwable e) {
					e.printStackTrace();
				}
                msg.what = SUCCESS_GET_CONTACT;
                mHandler.sendMessage(msg);
            };
        }.start();
        
    }

    //自定义适配器 FragmentPagerAdapter
    class myPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList;
        private List<String> titleList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList){
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
    
  //position ==1 正在滑动，position ==2滑动完毕了，position==0什么都没做，就是停在那。
	@Override
	public void onPageScrollStateChanged(int state) {
		//Log.i("onPageScrollStateChanged", ""+state);
	}

	//position页面滑动完毕的时辰调用的。
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//Log.i("onPageScrolled", ""+(position+1));
	}

	//点击标题时 跳转到的页面
	@Override
	public void onPageSelected(int position) {
		Log.i("onPageSelected", ""+position);
	}
	
	public void onBackMainClicked(View view){
		Intent intent = new Intent(this,MainActivity.class);
 		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
 		startActivity(intent);
     	if(version >= 5) {   
     	     overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
     	}
	}
	
    // 返回键 返回到主界面
 	@Override
 	public boolean onKeyDown(int keyCode, KeyEvent event) {
 		if (keyCode == KeyEvent.KEYCODE_BACK) {
 			onBackMainClicked(null);
 		}
 		return super.onKeyDown(keyCode, event);
 	}
 	
 	@Override
    protected void onDestroy() {
        super.onDestroy();
        //清空缓存
        File[] files = cache.listFiles();
        for(File file :files){
            file.delete();
        }
        cache.delete();
    }

}