package com.example.admin.estate_android_app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.fragment.BusinessFragment;
import com.example.admin.estate_android_app.ui.fragment.HomeFragment;
import com.example.admin.estate_android_app.ui.fragment.ScaningFragment;
import com.example.admin.estate_android_app.ui.fragment.SearchFragment;
import com.example.admin.estate_android_app.ui.fragment.SettingFragment;

/**
 * MainActivity
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 消息布局*
     */
    private LinearLayout infoLayout;
    private ImageView infoImage;
    private TextView infoText;


    /**
     * 工单管理*
     */
    private LinearLayout businessLayout;
    private ImageView businessImage;
    private TextView businessText;
    /**
     * 功能管理*
     */
    private LinearLayout searchLayout;
    private ImageView searchImage;
    private TextView searchText;
    /**
     * 设置管理*
     */
    private LinearLayout settingLayout;
    private ImageView settingImage;
    private TextView settingText;
    /**
     * 二维码扫描*
     */
    private ImageView scanningImage;

    private FragmentManager fragmentManager;

    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        infoLayout = (LinearLayout) findViewById(R.id.information_linear);
        businessLayout = (LinearLayout) findViewById(R.id.business_linear);
        searchLayout = (LinearLayout) findViewById(R.id.search_linear);
        settingLayout = (LinearLayout) findViewById(R.id.setting_linear);


        infoImage = (ImageView) findViewById(R.id.information_image);
        businessImage = (ImageView) findViewById(R.id.business_image);
        scanningImage = (ImageView) findViewById(R.id.scanning_image);
        searchImage = (ImageView) findViewById(R.id.search_image);
        settingImage = (ImageView) findViewById(R.id.setting_iamge);


        infoText = (TextView) findViewById(R.id.information_text);
        businessText = (TextView) findViewById(R.id.business_text);
        searchText = (TextView) findViewById(R.id.search_text);
        settingText = (TextView) findViewById(R.id.setting_text);
    }

    @Override
    protected void initView() {
        infoLayout.setOnClickListener(this);
        businessLayout.setOnClickListener(this);
        scanningImage.setOnClickListener(this);
        searchLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        selectStatus(R.id.information_linear);
        setMainData(fragment);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.information_linear:
                fragment = new HomeFragment();
                selectStatus(R.id.information_linear);
                break;
            case R.id.business_linear:
                fragment = new BusinessFragment();
                selectStatus(R.id.business_linear);
                break;
            case R.id.scanning_image:
                fragment = new ScaningFragment();
                selectStatus(R.id.scanning_image);
                break;
            case R.id.search_linear:
                fragment = new SearchFragment();
                selectStatus(R.id.search_linear);
                break;
            case R.id.setting_linear:
                fragment = new SettingFragment();
                selectStatus(R.id.setting_linear);
                break;
        }
        setMainData(fragment);
    }


    private void selectStatus(int code) {
        switch (code) {
            case R.id.information_linear:
                infoLayout.setBackgroundResource(R.drawable.bottom_bg);
                businessLayout.setBackgroundResource(R.drawable.ic_bottombg);
                searchLayout.setBackgroundResource(R.drawable.ic_bottombg);
                settingLayout.setBackgroundResource(R.drawable.ic_bottombg);

                infoImage.setImageResource(R.drawable.ic_information_on);
                businessImage.setImageResource(R.drawable.ic_workorder);
                scanningImage.setBackgroundResource(R.drawable.ic_tdcbg);
                scanningImage.setImageResource(R.drawable.ic_tdc);
                searchImage.setImageResource(R.drawable.ic_business);
                settingImage.setImageResource(R.drawable.ic_set);

                infoText.setTextColor(getResources().getColor(R.color.white_color));
                businessText.setTextColor(getResources().getColor(R.color.black_color));
                searchText.setTextColor(getResources().getColor(R.color.black_color));
                settingText.setTextColor(getResources().getColor(R.color.black_color));

                infoLayout.setEnabled(false);
                businessLayout.setEnabled(true);
                scanningImage.setEnabled(true);
                searchLayout.setEnabled(true);
                settingLayout.setEnabled(true);
                break;
            case R.id.business_linear:
                infoLayout.setBackgroundResource(R.drawable.ic_bottombg);
                businessLayout.setBackgroundResource(R.drawable.bottom_bg);
                searchLayout.setBackgroundResource(R.drawable.ic_bottombg);
                settingLayout.setBackgroundResource(R.drawable.ic_bottombg);

                infoImage.setImageResource(R.drawable.ic_information);
                businessImage.setImageResource(R.drawable.ic_workorder_on);
                scanningImage.setBackgroundResource(R.drawable.ic_tdcbg);
                scanningImage.setImageResource(R.drawable.ic_tdc);
                searchImage.setImageResource(R.drawable.ic_business);
                settingImage.setImageResource(R.drawable.ic_set);

                infoText.setTextColor(Color.parseColor("#000000"));
                businessText.setTextColor(Color.parseColor("#ffffffff"));
                searchText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#000000"));

                infoLayout.setEnabled(true);
                businessLayout.setEnabled(false);
                scanningImage.setEnabled(true);
                searchLayout.setEnabled(true);
                settingLayout.setEnabled(true);
                break;
            case R.id.scanning_image:
                infoLayout.setBackgroundResource(R.drawable.ic_bottombg);
                businessLayout.setBackgroundResource(R.drawable.ic_bottombg);
                searchLayout.setBackgroundResource(R.drawable.ic_bottombg);
                settingLayout.setBackgroundResource(R.drawable.ic_bottombg);

                infoImage.setImageResource(R.drawable.ic_information);
                businessImage.setImageResource(R.drawable.ic_workorder);
                scanningImage.setBackgroundResource(R.drawable.ic_tdcbg_on);
                scanningImage.setImageResource(R.drawable.ic_tdc_on);
                searchImage.setImageResource(R.drawable.ic_business);
                settingImage.setImageResource(R.drawable.ic_set);

                infoText.setTextColor(Color.parseColor("#000000"));
                businessText.setTextColor(Color.parseColor("#000000"));
                searchText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#000000"));

                infoLayout.setEnabled(true);
                businessLayout.setEnabled(true);
                scanningImage.setEnabled(false);
                searchLayout.setEnabled(true);
                settingLayout.setEnabled(true);
                break;
            case R.id.search_linear:
                infoLayout.setBackgroundResource(R.drawable.ic_bottombg);
                businessLayout.setBackgroundResource(R.drawable.ic_bottombg);
                searchLayout.setBackgroundResource(R.drawable.bottom_bg);
                settingLayout.setBackgroundResource(R.drawable.ic_bottombg);

                infoImage.setImageResource(R.drawable.ic_information);
                businessImage.setImageResource(R.drawable.ic_workorder);
                scanningImage.setBackgroundResource(R.drawable.ic_tdcbg);
                scanningImage.setImageResource(R.drawable.ic_tdc);
                searchImage.setImageResource(R.drawable.ic_business_on);
                settingImage.setImageResource(R.drawable.ic_set);

                infoText.setTextColor(Color.parseColor("#000000"));
                businessText.setTextColor(Color.parseColor("#000000"));
                searchText.setTextColor(Color.parseColor("#ffffffff"));
                settingText.setTextColor(Color.parseColor("#000000"));

                infoLayout.setEnabled(true);
                businessLayout.setEnabled(true);
                scanningImage.setEnabled(true);
                searchLayout.setEnabled(false);
                settingLayout.setEnabled(true);
                break;
            case R.id.setting_linear:
                infoLayout.setBackgroundResource(R.drawable.ic_bottombg);
                businessLayout.setBackgroundResource(R.drawable.ic_bottombg);
                searchLayout.setBackgroundResource(R.drawable.ic_bottombg);
                settingLayout.setBackgroundResource(R.drawable.bottom_bg);

                infoImage.setImageResource(R.drawable.ic_information);
                businessImage.setImageResource(R.drawable.ic_workorder);
                scanningImage.setBackgroundResource(R.drawable.ic_tdcbg);
                scanningImage.setImageResource(R.drawable.ic_tdc);
                searchImage.setImageResource(R.drawable.ic_business);
                settingImage.setImageResource(R.drawable.ic_set_on);

                infoText.setTextColor(Color.parseColor("#000000"));
                businessText.setTextColor(Color.parseColor("#000000"));
                searchText.setTextColor(Color.parseColor("#000000"));
                settingText.setTextColor(Color.parseColor("#ffffffff"));

                infoLayout.setEnabled(true);
                businessLayout.setEnabled(true);
                scanningImage.setEnabled(true);
                searchLayout.setEnabled(true);
                settingLayout.setEnabled(false);
                break;
        }
    }


    private void setMainData(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.realtabcontent, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
