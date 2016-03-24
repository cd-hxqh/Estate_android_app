/**
 * @Title: SearchFragment.java
 * @Package com.jumbo.app.fragment
 * @Description: TODO
 * @author liyangquan
 * @date 2014年4月16日 下午1:12:55 
 * @version V1.0
 */
package com.example.admin.estate_android_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.activity.InvuseActivity;
import com.example.admin.estate_android_app.ui.activity.ToolitemActivity;
import com.example.admin.estate_android_app.unit.Rotate3d;


/**
 * @ClassName: SearchFragment
 * @Description: 功能选项
 * @author liyangquan
 * @date 2014年4月16日 下午1:12:55
 */

public class SearchFragment extends BaseFragment implements OnClickListener {

    private View view;
    /** 标题 **/
    private TextView titleTextView;
    /** 切换按钮 **/
    private Button switchButton;

    /** 第一页--常用功能 **/
    private LinearLayout main_block_first = null;
    /** 第二页--查询功能 **/
    private LinearLayout main_block_second = null;

    /** 容器布局 **/
    private FrameLayout fl_block = null;

    /** 第一页布局组件,运行抄表,库存管理,使用情况,工具管理,采购管理,承接查验,值班记录 **/
    private TextView readmeterTextView, inventoryTextView, stockTextView,
            toolitemTextView, purchaseTextView, checkTextView,
            udondutylogTextView;
    /** 第二页布局组件, 大中修项目,安全检查单,资产查询,位置查询,预案演练单,合同查询,人员查询,工种查询 **/
    private TextView udprojectTextView, udcheckbillTextView, assetTextView,
            locationsTextView, uddrillrecordTextView, contTextView,
            pensonTextView, work_typeTextView;
    private final int TAG_FIRST = 1;
    private final int TAG_SECOND = 2;

    private int flag = 1;// 标志当前是第几页 1,2
    private String tag = "SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        initView();

        return view;
    }

    /** 初始化界面组件 **/
    private void initView() {
        titleTextView = (TextView) view.findViewById(R.id.title_text);
        titleTextView.setText(getResources().getString(R.string.tab_search));
        switchButton = (Button) view.findViewById(R.id.right_image_btn_1);
        switchButton.setVisibility(View.VISIBLE);

        main_block_first = (LinearLayout) view
                .findViewById(R.id.main_block_first);
        main_block_second = (LinearLayout) view
                .findViewById(R.id.main_block_second);
        fl_block = (FrameLayout) view.findViewById(R.id.fl_block);
        initFirstView();
        initSecondView();
        setEventListener();

    }

    /** 初始化常用功能组件 **/
    private void initFirstView() {
        readmeterTextView = (TextView) view
                .findViewById(R.id.readmeter_text_id);
        inventoryTextView = (TextView) view
                .findViewById(R.id.inventory_text_id);
        stockTextView = (TextView) view.findViewById(R.id.stock_text_id);
        toolitemTextView = (TextView) view.findViewById(R.id.toolitem_text_id);
        purchaseTextView = (TextView) view.findViewById(R.id.purchase_text_id);
        checkTextView = (TextView) view.findViewById(R.id.check_text_id);
        udondutylogTextView = (TextView) view
                .findViewById(R.id.udondutylog_text_id);
        readmeterTextView.setOnClickListener(this);
        inventoryTextView.setOnClickListener(this);
        stockTextView.setOnClickListener(this);
        toolitemTextView.setOnClickListener(this);
        purchaseTextView.setOnClickListener(this);
        checkTextView.setOnClickListener(this);
        udondutylogTextView.setOnClickListener(this);

    }

    /** 初始化查询功能组件 **/
    private void initSecondView() {
        udprojectTextView = (TextView) view
                .findViewById(R.id.udproject_text_id);
        udcheckbillTextView = (TextView) view
                .findViewById(R.id.udcheckbill_text_id);
        assetTextView = (TextView) view.findViewById(R.id.asset_text_id);
        locationsTextView = (TextView) view
                .findViewById(R.id.locations_text_id);
        uddrillrecordTextView = (TextView) view
                .findViewById(R.id.uddrillrecord_text_id);
        contTextView = (TextView) view.findViewById(R.id.cont_text_id);
        pensonTextView = (TextView) view.findViewById(R.id.penson_text_id);
        work_typeTextView = (TextView) view
                .findViewById(R.id.work_type_text_id);
        udprojectTextView.setOnClickListener(this);
        udcheckbillTextView.setOnClickListener(this);
        assetTextView.setOnClickListener(this);
        locationsTextView.setOnClickListener(this);
        uddrillrecordTextView.setOnClickListener(this);
        contTextView.setOnClickListener(this);
        pensonTextView.setOnClickListener(this);
        work_typeTextView.setOnClickListener(this);
    }

    /** 设置事件监听 **/
    private void setEventListener() {
        titleTextView.setText(view.getContext().getText(R.string.tab_search));
        switchButton.setText("1/2");
        switchButton.setOnClickListener(this);

    }


    /*
     * 翻转3D效果
     */
    private void applyRotation(int tag, float start, float end) {

        final View layout;

        layout = fl_block;

        final float centerX = layout.getWidth() / 2.0f;
        final float centerY = layout.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3d rotation = new Rotate3d(start, end, centerX, centerY,
                310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(tag));

        // layout.startAnimation(rotation);
        fl_block.startAnimation(rotation);

    }

    private final class DisplayNextView implements Animation.AnimationListener {

        private final int tag;

        private DisplayNextView(int tag) {
            this.tag = tag;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            fl_block.post(new SwapViews(tag));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    // 视图切换是在一个线程中实现的
    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int tag;

        public SwapViews(int position) {
            tag = position;
        }

        public void run() {
            float centerX;
            float centerY;
            Rotate3d rotation;
            View layout;

            if (tag == TAG_FIRST) {
                layout = main_block_second;
                centerX = layout.getWidth() / 2.0f;
                centerY = layout.getHeight() / 2.0f;
                if (centerX == 0 || centerY == 0) {
                    layout.measure(MeasureSpec.UNSPECIFIED,
                            MeasureSpec.UNSPECIFIED);
                    centerX = layout.getMeasuredWidth() / 2.0f;
                    centerY = layout.getMeasuredHeight() / 2.0f;
                }

                main_block_first.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);

                rotation = new Rotate3d(270, 360, centerX, centerY, 310.0f,
                        false);
                rotation.setDuration(500);
                rotation.setFillAfter(true);
                rotation.setInterpolator(new DecelerateInterpolator());

                fl_block.startAnimation(rotation);
                flag = TAG_SECOND;

            } else if (tag == TAG_SECOND) {
                layout = main_block_first;
                centerX = layout.getWidth() / 2.0f;
                centerY = layout.getHeight() / 2.0f;
                if (centerX == 0 || centerY == 0) {
                    layout.measure(MeasureSpec.UNSPECIFIED,
                            MeasureSpec.UNSPECIFIED);
                    centerX = layout.getMeasuredWidth() / 2.0f;
                    centerY = layout.getMeasuredHeight() / 2.0f;
                }

                main_block_second.setVisibility(View.INVISIBLE);

                layout.setVisibility(View.VISIBLE);

                rotation = new Rotate3d(90, 0, centerX, centerY, 310.0f, false);
                rotation.setDuration(500);
                rotation.setFillAfter(true);
                rotation.setInterpolator(new DecelerateInterpolator());
                fl_block.startAnimation(rotation);
                flag = TAG_FIRST;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            // 点击页面右下角 翻页
            case R.id.right_image_btn_1:
                if (flag == TAG_FIRST) {
                    applyRotation(TAG_FIRST, 0, 90);

                    setCurPage("2/2");
                } else if (flag == TAG_SECOND) {
                    applyRotation(TAG_SECOND, 360, 270);

                    setCurPage("1/2");
                }
                break;
//            case R.id.readmeter_text_id: // 运行抄表
//                startActivity(new Intent(getActivity(), ReadMeterActivity.class));
//                break;
//            case R.id.inventory_text_id: // 库存管理
//                startActivity(new Intent(getActivity(), InventoryActivity.class));
//                break;
            case R.id.stock_text_id: // 使用情况
                startActivity(new Intent(getActivity(), InvuseActivity.class));
                break;
            case R.id.toolitem_text_id: // 工具管理
                startActivity(new Intent(getActivity(), ToolitemActivity.class));
                break;
//            case R.id.purchase_text_id: // 采购管理
//                startActivity(new Intent(getActivity(), PurchaseManageAct.class));
//                break;
//            case R.id.check_text_id: // 承接查验
//                startActivity(new Intent(getActivity(), UdudtkCheckSiteAct.class));
//                break;
//            case R.id.udondutylog_text_id: // 值班记录
//                startActivity(new Intent(getActivity(), UdondutylogAct.class));
//                break;
//            case R.id.udproject_text_id: // 大中修项目
//                startActivity(new Intent(getActivity(), UdprojectListAct.class));
//                break;
//            case R.id.udcheckbill_text_id: // 安全检查单
//                startActivity(new Intent(getActivity(), UdcheckbillListAct.class));
//                break;
//            case R.id.asset_text_id:// 资产查询
//                startActivity(new Intent(getActivity(), AssetListAct.class));
//                break;
//            case R.id.locations_text_id:// 位置查询
//                startActivity(new Intent(getActivity(), LocationAct.class));
//                break;
//            case R.id.uddrillrecord_text_id:// 预案演练单
//                startActivity(new Intent(getActivity(), UddrillrecordAct.class));
//                break;
//            case R.id.cont_text_id:// 合同查询
//                startActivity(new Intent(getActivity(), AgreementManagerAct.class));
//                break;
//            case R.id.penson_text_id:
//                // 人员查询
//                startActivity(new Intent(getActivity(), PersonAct.class));
//                break;
//            case R.id.work_type_text_id:
//                // 工种查询
//                startActivity(new Intent(getActivity(), CraftListAct.class));
//                break;
            default:
                break;
        }

    }

    public void setCurPage(final String s) {
        Animation a = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.scale_in);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                switchButton.setText(s);
                switchButton.startAnimation(AnimationUtils.loadAnimation(
                        view.getContext(), R.anim.scale_out));
            }
        });
        switchButton.startAnimation(a);

    }

}
