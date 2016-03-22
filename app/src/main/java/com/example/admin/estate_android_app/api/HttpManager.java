package com.example.admin.estate_android_app.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.application.BaseApplication;
import com.example.admin.estate_android_app.bean.Results;
import com.example.admin.estate_android_app.constant.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;


/**
 * Created by admin on 2016/3/22.
 */
public class HttpManager {


    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "HttpManager";


    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password,
                                         final HttpRequestHandler<String> handler) {
        Log.i(TAG, "username=" + username + ",password=" + password);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        client.post(Constant.USER_LOGIN, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                boolean login = JsonUtils.analysisUserLogin(cxt, responseString);
                if (login) {
                    SafeHandler.onSuccess(handler, cxt.getResources().getString(R.string.login_successful_hint));
                } else {
                    SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
                }
            }
        });
    }


    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String tableName, String fields, String param, boolean b, final int currentpage, String pagesize, String useruid, final HttpRequestHandler<Results> handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("tableName", tableName);
        params.put("fields", fields);
        params.put("params", param);
        params.put("haspage", b + "");
        params.put("currentpage", currentpage+"");
        params.put("pagesize", pagesize);
        params.put("useruid", useruid + "");
        client.post(Constant.RECORD_LIST, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "statusCode" + "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);

                SafeHandler.onSuccess(handler, result, currentpage, 10);
            }
        });
    }

}
