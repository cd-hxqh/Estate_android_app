package com.example.admin.estate_android_app.ui.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.unit.MessageUtils;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

public class LoginActivity extends BaseActivity {
    /** 用户 **/
    private EditText editUserID;
    /** 密码 **/
    private EditText editPwd;
    /** 登陆按钮 **/
    private Button loginButton;
    /** 删除用户名 **/
    private ImageView deleteUserImage;
    /** 删除密码 **/
    private ImageView deletePasswordImage;
    /** 记住密码 **/
    private CheckBox cbRememberPwd;
    /** 记住密码表示 **/
    private boolean remember = false;
    private boolean cbResult = false;
    private SharedPreferences defaultSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        initView();
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
    }

    private View.OnClickListener clickOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_login:
                    checkUsername(editUserID.getText().toString(), editPwd
                            .getText().toString());
                    break;
                case R.id.login_username_edit_delete_image:
                    editUserID.setText("");
                    editPwd.setText("");
                    remember = false;
                    cbRememberPwd.setChecked(remember);
                    SystemSharedPreferences.setCheckSate(LoginActivity.this,
                            remember);
                    break;
                case R.id.login_password_edit_delete_image:
                    editPwd.setText("");
                    break;

                default:
                    break;
            }
        }
    };


    /** 检查用户名 **/
    private void checkUsername(String name, String pwd) {
        if (TextUtils.isEmpty(name)) {
            MessageUtils.showMiddleToast(LoginActivity.this,getResources().getString(R.string.user_username));
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            MessageUtils.showMiddleToast(LoginActivity.this, getResources().getString(R.string.user_pwd));
            return;
        }

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

}
