package com.example.admin.estate_android_app.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.adapter.ViewPagerAdapter;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

import java.util.ArrayList;

public class GuidanceActvity extends BaseActivity {
    // 定义ViewPager对象
    private ViewPager viewPager;

    // 定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;

    // 定义一个ArrayList来存放View
    private ArrayList<View> views;

    // 定义各个界面View对象
    private View view1, view2, view3, view4;

    // 定义底部小点图片
    private ImageView pointImage0, pointImage1, pointImage2, pointImage3;

    // //定义开始按钮对象
    private Button startBt;

    // 当前的位置索引值
    private int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance_actvity);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        // 实例化各个界面的布局对象
        LayoutInflater mLi = LayoutInflater.from(this);
        view1 = mLi.inflate(R.layout.guide_view01, null);
        view2 = mLi.inflate(R.layout.guide_view02, null);
        view3 = mLi.inflate(R.layout.guide_view03, null);
        view4 = mLi.inflate(R.layout.guide_view04, null);

        // 实例化ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // 实例化ArrayList对象
        views = new ArrayList<View>();

        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(views);

        // 实例化底部小点图片对象
        pointImage0 = (ImageView) findViewById(R.id.page0);
        pointImage1 = (ImageView) findViewById(R.id.page1);
        pointImage2 = (ImageView) findViewById(R.id.page2);
        pointImage3 = (ImageView) findViewById(R.id.page3);

        // 实例化开始按钮
        startBt = (Button) view4.findViewById(R.id.startBtn);
    }

    @Override
    protected void initView() {
        // 将要分页显示的View装入数组中
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        // 设置监听
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        // 设置适配器数据
        viewPager.setAdapter(vpAdapter);

        // 给开始按钮设置监听
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startbutton();
            }
        });
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    pointImage0.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_focused));
                    pointImage1.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    break;
                case 1:
                    pointImage1.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_focused));
                    pointImage0.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    pointImage2.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    break;
                case 2:
                    pointImage2.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_focused));
                    pointImage1.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    pointImage3.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    break;
                case 3:
                    pointImage3.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_focused));
                    pointImage2.setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
                    break;

            }
            currIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     * 相应按钮点击事件
     */
    private void startbutton() {
        SystemSharedPreferences.setGuidance(GuidanceActvity.this, true);
        Intent intent = new Intent();
        intent.setClass(GuidanceActvity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
