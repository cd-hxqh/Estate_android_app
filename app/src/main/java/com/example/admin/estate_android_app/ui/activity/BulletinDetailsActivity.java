package com.example.admin.estate_android_app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.model.BulletinBoard;


/**
 * @author king
 * @ClassName: BulletinDetailsActivity
 * @Description: TODO公告栏详情
 * @date 2014年5月7日 下午2:34:28
 */
public class BulletinDetailsActivity extends BaseActivity {
    /**
     * 返回按钮 *
     */
    private ImageView backImageView;
    /**
     * 标题 *
     */
    private TextView titleText;
    /**
     * 主题 *
     */
    private TextView themeText;
    /**
     * 消息 *
     */
    private TextView messageText;
    /**
     * 负责人 *
     */
    private TextView principalText;
    /**
     * 发布日期 *
     */
    private TextView startDate;
    /**
     * 截止日期 *
     */
    private TextView endDate;

    private BulletinBoard bulletinBoard;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.bulletin_detail);
        initDAO();
        findViewById();
        initView();
    }

    private void initDAO() {
        bulletinBoard = (BulletinBoard) getIntent().getSerializableExtra("bulletinBoard");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.left_image_btn);
        titleText = (TextView) findViewById(R.id.title_text);

        themeText = (TextView) findViewById(R.id.bulletin_detail_theme_text);
        messageText = (TextView) findViewById(R.id.bulletin_detail_message_text);
        principalText = (TextView) findViewById(R.id.bulletin_detail_principal_text);
        startDate = (TextView) findViewById(R.id.bulletin_detail_start_date_text);
        endDate = (TextView) findViewById(R.id.bulletin_detail_end_date_text);
    }

    @Override
    protected void initView() {
        setEventListener();
    }


    /**
     * 设置事件监听 *
     */
    private void setEventListener() {
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setOnClickListener(backOnClickListener);
        titleText.setText(getResources().getString(
                R.string.bulletin_detail_title));
        themeText.setText(bulletinBoard.subject);
        messageText.setText(bulletinBoard.message);
        principalText.setText(bulletinBoard.postby);
        startDate.setText(bulletinBoard.postdate);
        endDate.setText(bulletinBoard.expiredate);
    }

    /**
     * 返回事件 *
     */
    private OnClickListener backOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
