
package com.example.admin.estate_android_app.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.application.AppManager;

/**
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author liyangquan
 * @date 2014年4月16日 下午1:06:37
 */

public abstract class BaseActivity extends FragmentActivity {
    protected AlertDialog mAlertDialog;
    private MotionEvent downEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        ViewConfiguration configuration = ViewConfiguration
                .get(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    protected void onStop() {
        super.onStop();
    };

    /**
     * 绑定控件id
     */
    protected abstract void findViewById();

    /**
     * 初始化控件
     */
    protected abstract void initView();



}
