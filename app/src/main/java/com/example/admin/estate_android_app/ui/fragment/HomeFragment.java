
package com.example.admin.estate_android_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.activity.BulletinActivity;


/**
 * @ClassName: HomeFragment
 * @Description: TODO 消息界面
 * @author liyangquan
 * @date 2014年4月16日 下午1:08:22
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG="HomeFragment";
    private View view;

    /** 标题 **/
    private TextView titleText;

    /**任务栏**/
    private RelativeLayout messageRelativeLayout;
    /**公告栏**/
    private RelativeLayout bulletinRelativeLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        findViewById();
        initView();
        return view;
    }

    /**界面组件**/
    private void findViewById() {
        titleText = (TextView) view.findViewById(R.id.title_text);

        messageRelativeLayout=(RelativeLayout)view.findViewById(R.id.task_relativelayout_id);
        bulletinRelativeLayout=(RelativeLayout)view.findViewById(R.id.bulletin_relativelayout_id);
    }


    /** 初始化界面组件 **/
    private void initView() {
        titleText.setText(getResources().getString(R.string.tab_home));
        messageRelativeLayout.setOnClickListener( messageOnClickListener);
        bulletinRelativeLayout.setOnClickListener(bulletinOnClickListener);
    }

    private OnClickListener messageOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i(TAG,"sssss");
        }
    };
    private OnClickListener bulletinOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i(TAG,"sssaaaaa");
            jumpBulletinActivity();
        }
    };


//    /** 跳转至任务栏界面 **/
//    private  void jumpTaskActivity() {
//        Intent intent = new Intent(getActivity(), InboxListActivity.class);
//        startActivity(intent);
//    }

    /** 跳转至公告栏界面 **/
    public  void jumpBulletinActivity() {
        Intent intent = new Intent(getActivity(), BulletinActivity.class);

        startActivity(intent);
    }



}
