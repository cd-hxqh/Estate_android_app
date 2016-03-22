package com.example.admin.estate_android_app.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.api.HttpManager;
import com.example.admin.estate_android_app.api.HttpRequestHandler;
import com.example.admin.estate_android_app.application.AppManager;
import com.example.admin.estate_android_app.unit.MessageUtils;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    /**
     * 用户 *
     */
    private EditText editUserID;
    /**
     * 密码 *
     */
    private EditText editPwd;
    /**
     * 登陆按钮 *
     */
    private Button loginButton;
    /**
     * 删除用户名 *
     */
    private ImageView deleteUserImage;
    /**
     * 删除密码 *
     */
    private ImageView deletePasswordImage;
    /**
     * 记住密码 *
     */
    private CheckBox cbRememberPwd;
    /**
     * 记住密码表示 *
     */
    private boolean remember = false;
    private boolean cbResult = false;
    private SharedPreferences defaultSharedPreferences;


    private ProgressDialog mProgressDialog;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        initView();
        getCheckBoxState();
    }

    @Override
    protected void findViewById() {
        loginButton = (Button) findViewById(R.id.btn_login);
        editUserID = (EditText) findViewById(R.id.login_name_input);
        editPwd = (EditText) findViewById(R.id.login_pwd_input);
        deleteUserImage = (ImageView) findViewById(R.id.login_username_edit_delete_image);
        deletePasswordImage = (ImageView) findViewById(R.id.login_password_edit_delete_image);
        cbRememberPwd = (CheckBox) findViewById(R.id.ck_remember_pwd);
    }

    @Override
    protected void initView() {
        defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(LoginActivity.this);
        cbResult = defaultSharedPreferences.getBoolean(
                getString(R.string.user_check_key_token), false);
        cbRememberPwd.setChecked(cbResult);
        remember = cbResult;

        loginButton.setOnClickListener(clickOnClickListener);
        deleteUserImage.setOnClickListener(clickOnClickListener);
        deletePasswordImage.setOnClickListener(clickOnClickListener);
        editUserID.addTextChangedListener(editUserTextWatcherListener);
        editPwd.addTextChangedListener(editPswTextWatcherListener);
        cbRememberPwd.setOnCheckedChangeListener(checkBoxStateChange);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    private View.OnClickListener clickOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_login: //登录按钮
                    checkUsername(editUserID.getText().toString(), editPwd
                            .getText().toString());
                    break;
                case R.id.login_username_edit_delete_image: //用户名编辑框
                    editUserID.setText("");
                    editPwd.setText("");
                    remember = false;
                    cbRememberPwd.setChecked(remember);
                    SystemSharedPreferences.setCheckSate(LoginActivity.this,
                            remember);
                    break;
                case R.id.login_password_edit_delete_image: //密码编辑框
                    editPwd.setText("");
                    break;

                default:
                    break;
            }
        }
    };


    /**
     * 检查用户名 *
     */
    private void checkUsername(String name, String pwd) {
        if (TextUtils.isEmpty(name)) {
            MessageUtils.showMiddleToast(LoginActivity.this, getResources().getString(R.string.user_username));
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            MessageUtils.showMiddleToast(LoginActivity.this, getResources().getString(R.string.user_pwd));
            return;
        }
        login();
    }


    /**
     * 监听筛选框的值
     */
    private TextWatcher editUserTextWatcherListener = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable arg0) {
            if (null == arg0 || 0 == arg0.length()) {
                deleteUserImage.setVisibility(View.GONE);
            } else {
                deleteUserImage.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            if (null == arg0 || 0 == arg0.length()) {
                deleteUserImage.setVisibility(View.GONE);
            } else {
                deleteUserImage.setVisibility(View.VISIBLE);
            }
        }

    };

    /**
     * 监听筛选框的值
     */
    private TextWatcher editPswTextWatcherListener = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable arg0) {
            if (null == arg0 || 0 == arg0.length()) {
                deletePasswordImage.setVisibility(View.GONE);
            } else {
                deletePasswordImage.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

        }

    };


    private CompoundButton.OnCheckedChangeListener checkBoxStateChange = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            remember = isChecked;
            SystemSharedPreferences.setCheckSate(LoginActivity.this, remember);
            if ((TextUtils.isEmpty(editUserID.getText().toString()) || TextUtils
                    .isEmpty(editPwd.getText().toString()))
                    && (true == isChecked)) {
                SystemSharedPreferences.setUserNameAndPassWord(
                        LoginActivity.this, "", "");
            }

        }
    };


    /**
     * 登录界面
     */
    private void login() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, null,
                getString(R.string.login_loging), true, true);

        HttpManager.loginWithUsername(LoginActivity.this,
                editUserID.getText().toString(),
                editPwd.getText().toString(),
                new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        MessageUtils.showMiddleToast(LoginActivity.this, data);
                        rememberUserName(remember);
                        mProgressDialog.dismiss();
                        startIntent();


                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {
                        MessageUtils.showMiddleToast(LoginActivity.this, getString(R.string.login_successful_hint));
                        startIntent();
                    }

                    @Override
                    public void onFailure(String error) {
                        MessageUtils.showErrorMessage(LoginActivity.this, error);
                        mProgressDialog.dismiss();
//                        startIntent();
                    }
                });


    }


    private void startIntent() {
        Intent inetnt = new Intent();
        inetnt.setClass(this, MainActivity.class);
        startActivity(inetnt);

    }


    /**
     * 将用户名和密码保存至SharedPreferences *
     */
    public void rememberUserName(boolean result) {
        String userName = "";
        String userPwd = "";
        if (result) {
            userName = editUserID.getText().toString();
            userPwd = editPwd.getText().toString();
        }
        SystemSharedPreferences.setUserNameAndPassWord(LoginActivity.this,
                userName, userPwd);
    }


    /**
     * 获取用户名和密码 *
     */
    public void getCheckBoxState() {
        if (cbResult) {
            // getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
            String name = defaultSharedPreferences.getString(
                    getString(R.string.user_name_key_token), "");
            String pwd = defaultSharedPreferences.getString(
                    getString(R.string.user_pwd_key_token), "");
            editUserID.setText(name);
            editPwd.setText(pwd);
        } else {
            editUserID.setText("");
            editPwd.setText("");
        }
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }


    /**
     * 退出程序
     */
    public void showAlertDialog() {
        final NormalDialog dialog = new NormalDialog(LoginActivity.this);
        dialog.content("确定退出程序吗")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {


                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        AppManager.AppExit(LoginActivity.this);
                        dialog.dismiss();
                    }
                });

    }


}
