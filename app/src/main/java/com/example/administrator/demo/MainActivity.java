package com.example.administrator.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
public class MainActivity extends Activity implements OnClickListener{
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private List<View> mViews = new ArrayList<View>();//保存微信，朋友，通讯录，设置4个界面的view
    //底部的四个tab按钮，微信，朋友，通讯录，设置
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
    }

    private void initEvents() {
        //给底部的4个LinearLayout即4个导航控件添加点击事件
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
        //viewpager滑动事件
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {//当viewpager滑动时，对应的底部导航按钮的图片要变化
                int currentItem = mViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void initView() {//初始化所有的view
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        //tabs
        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);
        //imagebutton
        mWeixinImg = (ImageButton)findViewById(R.id.id_tab_weixin_img);
        mFrdImg = (ImageButton)findViewById(R.id.id_tab_frd_img);
        mAddressImg = (ImageButton)findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton)findViewById(R.id.id_tab_setting_img);
        //通过LayoutInflater引入布局，并将布局转化为view
        LayoutInflater mInflater = LayoutInflater.from(this);//创建一个LayoutInflater对象
        View tab01 = mInflater.inflate(R.layout.top1, null);//通过inflate方法动态加载一个布局文件
        View tab02 = mInflater.inflate(R.layout.top2, null);
        View tab03 = mInflater.inflate(R.layout.top3, null);
        View tab04 = mInflater.inflate(R.layout.top4, null);
        mViews.add(top1);
        mViews.add(top2);
        mViews.add(top3);
        mViews.add(top4);
        //初始化PagerAdapter
        mAdapter = new PagerAdapter() {


            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);//从mViews这个list中根据位置取出我们需要的view
                container.addView(view);//将上述的view添加到ViewGroup中
                return view;
            }

            /*
             * isViewFromObject用来判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表
             * 的同一个视图(即它俩是否是对应的，对应的表示同一个View),如果对应的是同一个View，返回True，否则返回False
             * */
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // 返回PagerView包含的view的个数
                return mViews.size();
            }
        };
        //为ViewPager设置adapter
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        resetImg();//点击哪个tab,对应的颜色要变亮，因此，在点击具体的tab之前先将所有的图片都重置为未点击的状态，即暗色图片
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                mViewPager.setCurrentItem(0);//如果点击的是微信，就将viewpager的当前item设置为0，及切换到微信聊天的viewpager界面
                mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);//并将按钮颜色点亮
                break;
            case R.id.id_tab_frd:
                mViewPager.setCurrentItem(1);
                mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case R.id.id_tab_address:
                mViewPager.setCurrentItem(2);
                mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                break;
            case R.id.id_tab_setting:
                mViewPager.setCurrentItem(3);
                mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
                break;

            default:
                break;
        }

    }
    /*
     * 将所有的图片设置为暗色
     * */
    private void resetImg() {
        mWeixinImg.setImageResource(R.drawable.tab_weixin_normal);
        mFrdImg.setImageResource(R.drawable.tab_find_frd_normal);
        mAddressImg.setImageResource(R.drawable.tab_address_normal);
        mSettingImg.setImageResource(R.drawable.tab_settings_normal);

    }
}