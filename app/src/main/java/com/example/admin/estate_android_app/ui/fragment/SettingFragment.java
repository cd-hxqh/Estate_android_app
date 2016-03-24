package com.example.admin.estate_android_app.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.constant.Constant;
import com.example.admin.estate_android_app.unit.CreateFolderUtil;
import com.example.admin.estate_android_app.unit.MessageUtils;
import com.example.admin.estate_android_app.unit.SwitchButton;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;
import com.example.admin.estate_android_app.unit.Utils;

import java.io.File;
import java.lang.reflect.Method;


public class SettingFragment extends BaseFragment implements OnClickListener {
    private static final String TAG = "SettingFragment";
    private View view;
    /** 标题 **/
    private TextView titleText;
    private LinearLayout userInfoLayout, syncInfoLayout, networkInfoLayout,
            clearInfoLayout, newInfoLayout, proInfoLayout, aboutInfoLayout,
            editInfoLayout;
    /** 用户名 **/
    private TextView userText;
    /** 数据库的TextView **/
    private TextView dataTextView;
    /** SwitchButton **/
    private SwitchButton switchButton;
    /** 自定义进度条 **/
    private ProgressDialog progressDialog = null;

    private ConnectivityManager connManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, null);
        initView();
        return view;
    }

    /** 初始化界面组件 **/
    private void initView() {
        titleText = (TextView) view.findViewById(R.id.title_text);
        titleText.setText(getResources().getString(R.string.tab_setting));
        userInfoLayout = (LinearLayout) view.findViewById(R.id.userinfo_id);
        userText = (TextView) view.findViewById(R.id.user_name_title);
        switchButton = (SwitchButton) view.findViewById(R.id.switchButton_id);
        dataTextView = (TextView) view.findViewById(R.id.cache_size_id);

        syncInfoLayout = (LinearLayout) view.findViewById(R.id.syncinfo_id);
        networkInfoLayout = (LinearLayout) view.findViewById(R.id.network_id);
        clearInfoLayout = (LinearLayout) view.findViewById(R.id.clear_id);
        newInfoLayout = (LinearLayout) view.findViewById(R.id.new_id);
        proInfoLayout = (LinearLayout) view.findViewById(R.id.products_id);
        aboutInfoLayout = (LinearLayout) view.findViewById(R.id.about_id);
        editInfoLayout = (LinearLayout) view.findViewById(R.id.edit_id);
        userInfoLayout.setOnClickListener(this);
        syncInfoLayout.setOnClickListener(this);
        networkInfoLayout.setOnClickListener(this);
        clearInfoLayout.setOnClickListener(this);
        newInfoLayout.setOnClickListener(this);
        proInfoLayout.setOnClickListener(this);
        aboutInfoLayout.setOnClickListener(this);
        editInfoLayout.setOnClickListener(this);
        switchButton.setChecked(false);
        connManager = (ConnectivityManager)getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        setEventListener();

    }

    private void setEventListener() {
        userText.setText(SystemSharedPreferences.getUserName(view.getContext()));
        dataTextView.setText(getFileSize());
        switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.i(TAG, "isChecked="+isChecked);
                if (!isChecked) {
                    MessageUtils.showMiddleToast(getActivity(), "Wifi网络已打开");
                } else {
                    MessageUtils.showMiddleToast(getActivity(), "Wifi网络已关闭");
                }
                toggleWiFi(!isChecked);
                switchButton.setChecked(isChecked);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.userinfo_id:
//                startActivity(new Intent(getActivity(), UserActivity.class));
//                break;
//
//            case R.id.syncinfo_id: // 同步数据
//                startActivity(new Intent(getActivity(), SynchrodataAct.class));
//                break;
//            case R.id.network_id:
//                break;
//            case R.id.clear_id:
//                Toast.makeText(getActivity(), "正在清除", Toast.LENGTH_SHORT).show();
//
//                if (isSucceed()) {
//                    Toast.makeText(getActivity(), "正在成功", Toast.LENGTH_SHORT)
//                            .show();
//                    SystemSharedPreferences.setIfCache(getActivity(), false);
//                    dataTextView.setText(getFileSize());
//                } else {
//                    Toast.makeText(getActivity(), "正在失败", Toast.LENGTH_SHORT)
//                            .show();
//                }
//
//                break;
//            case R.id.new_id:
//                createProgressDialog("正在检测更新，请耐心等候...");
//                setForceUpdate();
//                break;
//            case R.id.products_id:
//                startActivity(new Intent(getActivity(), ProductInfoAct.class));
//                break;
//            case R.id.about_id:
//                startActivity(new Intent(getActivity(), AboutActivity.class));
//                break;
//            case R.id.edit_id: // 退出登录
//                UIHelper.exitDialog(getActivity());
//                break;

            default:
                break;
        }
    }

    /** 删除文件夹 **/
    private boolean isSucceed() {
        boolean isdelete;
        boolean b = Utils.isSdCard();
        if (b) {
            String fileName1 = Constant.PATH_DB
                    + getActivity().getPackageName();
            isdelete = CreateFolderUtil.delFolder(fileName1);

        } else {
            String fileName2 = Constant.NOT_SDCARD_PATH_DB
                    + getActivity().getPackageName();
            isdelete = CreateFolderUtil.delFolder(fileName2);
        }
        return isdelete;
    }

//    /**
//     * 手动强制更新
//     */
//    private void setForceUpdate() {
//        UmengUpdateAgent.setUpdateAutoPopup(false);
//        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
//
//            @Override
//            public void onUpdateReturned(int updateStatus,
//                                         UpdateResponse updateInfo) {
//                closeDialog();
//                switch (updateStatus) {
//                    case UpdateStatus.Yes: // has update
//                        UmengUpdateAgent
//                                .showUpdateDialog(getActivity(), updateInfo);
//                        break;
//                    case UpdateStatus.No: // has no update
//                        Toast.makeText(getActivity(), "未发现新版本，当前安装的已是最新版本",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.NoneWifi: // none wifi
//                        Toast.makeText(getActivity(), "没有wifi连接， 只在wifi下更新",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.Timeout: // time out
//                        Toast.makeText(getActivity(), "更新超时,请检查网络",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//            }
//        });
//        UmengUpdateAgent.forceUpdate(getActivity());
//    }

    /** 创建数据加载的对话框 **/
    private void createProgressDialog(String str) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(str);
        progressDialog.setProgressStyle(R.style.customProgressDialog);

        progressDialog.show();
    }

    /** 关闭对话框 **/

    private void closeDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

//    /**
//     * 定义handerler
//     */
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            closeDialog();
//            stopTask();
//            switch (msg.what) {
//                case AppConstant.OPERATE_RESULT.OPERATE_SUCCEED:
//                    UIHelper.ToastMessage(view.getContext(), getResources()
//                            .getString(R.string.data_load_succeely));
//                    break;
//                case AppConstant.OPERATE_RESULT.OPERATE_SUCCEED_1:
//                    break;
//                case AppConstant.OPERATE_RESULT.OPERATE_FAIL:
//                    break;
//                case AppConstant.INIT_DATA_SUCCESS: // 获取基础数据成功
//                    break;
//            }
//        }
//    };


    /** 获取文件大小 **/
    private String getFileSize() {
        boolean b = Utils.isSdCard();
        String fileSize = null;
        long size = 0;
        File f = null;
        if (b) {
            String fileName1 = Constant.PATH_DB
                    + getActivity().getPackageName();
            f = new File(fileName1);
        } else {
            String fileName2 = Constant.NOT_SDCARD_PATH_DB
                    + getActivity().getPackageName();
            f = new File(fileName2);
        }
        try {
            size = getFileSize(f);
            if (size == 0) {
                fileSize = "0KB";
            } else {
                fileSize = convertFileSize(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileSize;

    }

    /** 获取数据库文件大小 **/
    private long getFileSize(File f) throws Exception// 取得文件夹大小
    {
        long size = 0;
        if (f.length() != 0) {
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                if (flist[i].isDirectory()) {
                    size = size + getFileSize(flist[i]);
                } else {
                    size = size + flist[i].length();
                }
            }
        }
        return size;
    }

    /** 将文件大小转成成MB **/
    private String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "resultCode=" + resultCode);
        switch (resultCode) {
            case 998:
                dataTextView.setText(getFileSize());
                break;

            default:
                break;
        }
    }

    /**
     * WIFI网络开关
     *
     * @param enabled
     * @return 设置是否success
     */
    public boolean toggleWiFi(boolean enabled) {
        WifiManager wm = (WifiManager) getActivity().getSystemService(
                Context.WIFI_SERVICE);
        return wm.setWifiEnabled(enabled);

    }

    /**
     * GPRS网络开关 反射ConnectivityManager中hide的方法setMobileDataEnabled 可以开启和关闭GPRS网络
     *
     * @param isEnable
     * @throws Exception
     */
    public void toggleGprs(boolean isEnable) throws Exception {
        Class<?> cmClass = connManager.getClass();
        Class<?>[] argClasses = new Class[1];
        argClasses[0] = boolean.class;

        // 反射ConnectivityManager中hide的方法setMobileDataEnabled，可以开启和关闭GPRS网络
        Method method = cmClass.getMethod("setMobileDataEnabled", argClasses);
        method.invoke(connManager, isEnable);
    }

}
