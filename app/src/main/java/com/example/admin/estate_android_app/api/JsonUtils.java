package com.example.admin.estate_android_app.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.estate_android_app.bean.Results;
import com.example.admin.estate_android_app.unit.StringUnit;
import com.example.admin.estate_android_app.unit.SystemSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Json数据解析封装类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static boolean analysisUserLogin(Context context, String jsonStr) {
        boolean login = false;
        try {
            JSONObject arg = new JSONObject(jsonStr);
            JSONArray arrays = arg.getJSONArray("auth");
            JSONObject result = arrays.getJSONObject(0);
            String isSuccess = result.getString("isSuccess");
            if (isSuccess.equals("1")) {
                login = true;

                String useruid = result.getString("useruid");
                String personid = result.getString("personid");
                String displayname = result.getString("displayname");
                String title = result.getString("title");
                String department = result.getString("department");
                String location = result.getString("location");
                String locationsite = result.getString("locationsite");
                String locationorg = result.getString("locationorg");
                String loginid = result.getString("loginid");
                String imei = result.getString("imei");

                SystemSharedPreferences.setUserId(context, useruid);
                SystemSharedPreferences.setUserPersionId(context, personid);
                SystemSharedPreferences.setUserName(context, displayname);
                SystemSharedPreferences.setUserSite(context, locationsite);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return login;
        }


        return login;
    }



    /**
     * 解析公告栏
     */
    public static String analysisBulletin(Context context, String jsonStr) {
        String results=null;
        try {
            String jsonStr1 = StringUnit.trimFirstAndLastChar(jsonStr, '[', ']');

            JSONObject jsonObject= new JSONObject(jsonStr1);
            results=jsonObject.getString("cboset");
        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }
        Log.i(TAG,"results="+results);

        return results;
    }


    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        Results results = null;
        try {
            String jsonStr1 = StringUnit.trimFirstAndLastChar(data, '[', ']');
            JSONObject json = new JSONObject(jsonStr1);
            String isSuccess = json.getString("isSuccess");
            if (isSuccess.equals("1")) {
                String cboset = json.getString("cboset");
                String errorcode = json.getString("errorcode");
                int resultcount = json.getInt("resultcount");
                String type = json.getString("type");
                results = new Results();
                results.setCboset(cboset);
                results.setErrorcode(errorcode);
                results.setResultcount(Integer.toString(resultcount));
                results.setType(type);
            } else {
                results = new Results();
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }
}