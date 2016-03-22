package com.example.admin.estate_android_app.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.api.HttpManager;
import com.example.admin.estate_android_app.api.HttpRequestHandler;
import com.example.admin.estate_android_app.api.ig.json.Ig_Json_Model;
import com.example.admin.estate_android_app.bean.Results;
import com.example.admin.estate_android_app.constant.Constant;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.example.admin.estate_android_app.ui.adapter.BulletinListAdapter;
import com.example.admin.estate_android_app.ui.widget.SwipeRefreshLayout;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;


/**
 * @author king
 * @ClassName: BulletinActivity
 * @Description: TODO公告栏的信息
 * @date 2014年5月7日 上午9:43:48
 */
public class BulletinActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {


    private static final String TAG = "BulletinActivity";
    /**
     * 返回按钮 *
     */
    private ImageView backImageView;
    /**
     * 标题Text *
     */
    private TextView titleText;

    LinearLayoutManager layoutManager;

    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;
    /**
     * 适配器*
     */
    private BulletinListAdapter bulletinListAdapter;
    /**
     * 编辑框*
     */
    private EditText search;
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_list);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.left_image_btn);
        titleText = (TextView) findViewById(R.id.title_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();


        titleText.setText(getString(R.string.bulletin_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bulletinListAdapter = new BulletinListAdapter(this);
        recyclerView.setAdapter(bulletinListAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    @Override
    public void onLoad() {

    }

    @Override
    public void onRefresh() {

    }


    /**
     * 获取数据*
     */
    private void getData(String search) {
        String sql = null;
        if (search.equals("")) {
            sql = null;
        } else {

            sql = " and (subject like '%" + search
                    + "%' or message like '%" + search
                    + "%')";
        }
        HttpManager.getDataPagingInfo(this, Constant.BULLETIN_TABLE, null, sql, true, page, "10", SystemSharedPreferences.getUserId(BulletinActivity.this), new HttpRequestHandler<Results>() {


            @Override
            public void onSuccess(Results results) {

            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                Log.i(TAG, "results=" + results.getCboset());

                ArrayList<BulletinBoard> items = null;
                try {
                    if(results.getCboset()==null){
                        refresh_layout.setRefreshing(false);
                        nodatalayout.setVisibility(View.VISIBLE);
                    }else{
                    items = Ig_Json_Model.parseBulletinBoardString(results.getCboset());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            bulletinListAdapter = new BulletinListAdapter(BulletinActivity.this);
                            recyclerView.setAdapter(bulletinListAdapter);
                        }
                        if (totalPages == page) {
                            bulletinListAdapter.adddate(items);
                        }
                    }

                }} catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    private void setSearchEdit() {
        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    BulletinActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    bulletinListAdapter.removeAllData();
                    nodatalayout.setVisibility(View.GONE);
                    refresh_layout.setRefreshing(true);
                    page = 1;
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }


}
