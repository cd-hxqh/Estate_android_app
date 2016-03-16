package com.example.admin.estate_android_app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

public class LoadActicity extends BaseActivity {
    private static final String TAG = "LoadActicity";
    private ImageView mSplashItem_iv = null;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_load_acticity, null);
        setContentView(view);
        findViewById();
        initView();

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        Animation translate = AnimationUtils.loadAnimation(this,
                R.anim.alpha);
        translate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                 启动homeactivty，相当于Intent
                if (SystemSharedPreferences.getGuidance(LoadActicity.this)) {
                    jumpLoginActivity(LoadActicity.this);
                } else {
                    jumpGuidanceActvity(LoadActicity.this);
                }
                finish();
            }
        });
        view.startAnimation(translate);
    }

    /**
     * 跳转至引导界面
     */
    public void jumpGuidanceActvity(Context cont) {
        Intent intent = new Intent(cont, GuidanceActvity.class);

        startActivity(intent);
    }

    /**
     * 跳转至登陆界面
     */
    public void jumpLoginActivity(Context cont) {
        Intent intent = new Intent(cont, LoginActivity.class);

        startActivity(intent);
    }
}
