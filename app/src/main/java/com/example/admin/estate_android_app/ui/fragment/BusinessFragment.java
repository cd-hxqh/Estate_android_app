package com.example.admin.estate_android_app.ui.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.admin.estate_android_app.model.Workorder;
import com.example.admin.estate_android_app.ui.adapter.WorkorderAdapter;
import com.example.admin.estate_android_app.ui.widget.SwipeRefreshLayout;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 工单fragment
 */
public class BusinessFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener{


    private static final String TAG="BusinessFragment";

    /**
     * 返回按钮 *
     */
    private ImageView backImageView;
    /**
     * 标题 *
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
    private WorkorderAdapter workorderAdapter;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container,
                false);

        findByIdView(view);
        initView();
        return view;
    }


    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {

        backImageView = (ImageView)view. findViewById(R.id.left_image_btn);
        titleText = (TextView) view.findViewById(R.id.title_text);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        search = (EditText) view.findViewById(R.id.search_edit);
    }


    /**
     * 设置事件监听*
     */
    private void initView() {

        backImageView.setVisibility(View.GONE);
        titleText.setText(getActivity().getString(R.string.work_title));


        setSearchEdit();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        workorderAdapter = new WorkorderAdapter(getActivity());
        recyclerView.setAdapter(workorderAdapter);
        refresh_layout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        getData(searchText);
    }

    @Override
    public void onLoad() {
        page ++;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page=1;
        getData(searchText);
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
                                    getActivity().getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    workorderAdapter.removeAllData();
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


    /**
     * 获取数据*
     */
    private void getData(String search) {
        String sqlAll=null;
        String sql = "istask=0 and historyflag=0 and parent is null";
        String sql1 = " order by workorderid desc ";

        String sql0 = " and (wonum like '%" + search
                + "%' or location like '%" + search
                + "%' or wodescription like '%" + search + "%')";
        if(search.equals("")){

            sqlAll=sql+sql1;
        }else if (search.indexOf("=") != -1) { // 判断获取的字符串中是否有“=”
                search = search.replace("=", "");
                String sql2 = " and (wonum ='" + search + "'"
                        + "or location ='" + search
                        + "' or wodescription ='" + search + "')";
                sqlAll = sql + sql2;
            } else {

                sqlAll = sql + sql0 + sql1;
            }

        HttpManager.getDataPagingInfo(getActivity(), Constant.WORKORDER_TABLE, Constant.WORKORDER_LIST, sqlAll, true, page, "10", SystemSharedPreferences.getUserId(getActivity()), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                ArrayList<Workorder> items = null;
                try {
                    items = Ig_Json_Model.parseWorkorderString(results.getCboset());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        if (workorderAdapter.getItemCount() != 0) {
                            workorderAdapter.removeAllData();
                        }
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            workorderAdapter = new WorkorderAdapter(getActivity());
                            recyclerView.setAdapter(workorderAdapter);
                        }
                        if (totalPages == page) {
                            workorderAdapter.adddate(items);
                        }
                    }

                } catch (IOException e) {
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











}
