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

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.api.HttpManager;
import com.example.admin.estate_android_app.api.HttpRequestHandler;
import com.example.admin.estate_android_app.api.ig.json.Ig_Json_Model;
import com.example.admin.estate_android_app.bean.Results;
import com.example.admin.estate_android_app.constant.Constant;
import com.example.admin.estate_android_app.model.DiyInvuse;
import com.example.admin.estate_android_app.model.Toolitem;
import com.example.admin.estate_android_app.ui.adapter.DiyInvuseListAdapter;
import com.example.admin.estate_android_app.ui.adapter.ToolitemListAdapter;
import com.example.admin.estate_android_app.ui.widget.SwipeRefreshLayout;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author king
 * @ClassName: ToolitemActivity
 * @Description: 工具列表
 * @date 2014年5月7日 上午9:43:48
 */
public class ToolitemActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {


    private static final String TAG = "ToolitemActivity";
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
    private ToolitemListAdapter toolitemListAdapter;
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


        titleText.setText(getString(R.string.toolitem_title));
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setOnClickListener(backImageViewOnClickListener);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        toolitemListAdapter = new ToolitemListAdapter(this);
        recyclerView.setAdapter(toolitemListAdapter);
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
        page++;
        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getData(searchText);
    }


    /**
     * 获取数据*
     */
    private void getData(String search) {
        String sql0 = " order by itemid desc";
        String sql1 = "itemnum like '%" + search
                + "%' or description like '%" + search + "%'";
        String sql2 = "1=1";
        String sqlAll = null;
        if (search.equals("")) {
            sqlAll = sql2 + sql0;
        } else {

            sqlAll = sql1 + sql0;
        }
        HttpManager.getDataPagingInfo(this, Constant.TOOLITEM_TABLE, Constant.TOOLITEM_LIST, sqlAll, true, page, "10", SystemSharedPreferences.getUserId(ToolitemActivity.this), new HttpRequestHandler<Results>() {


            @Override
            public void onSuccess(Results results) {

            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                Log.i(TAG, "results=" + results.getCboset());

                ArrayList<Toolitem> items = null;
                try {
                    if(results.getCboset()==null){
                        refresh_layout.setRefreshing(false);
                        nodatalayout.setVisibility(View.VISIBLE);
                    }else{
                    items = Ig_Json_Model.parseToolitemString(results.getCboset());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            toolitemListAdapter = new ToolitemListAdapter(ToolitemActivity.this);
                            recyclerView.setAdapter(toolitemListAdapter);
                        }
                        if (totalPages == page) {
                            toolitemListAdapter.adddate(items);
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
                                    ToolitemActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    toolitemListAdapter.removeAllData();
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
