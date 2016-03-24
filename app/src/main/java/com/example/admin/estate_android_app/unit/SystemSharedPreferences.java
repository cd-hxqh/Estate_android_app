package com.example.admin.estate_android_app.unit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.admin.estate_android_app.R;


/**
 * @author king
 * @ClassName: SystemSharedPreferences
 * @Description: TODO 系统SharedPreferences的分享
 * @date 2014年4月21日 下午2:03:03
 */
public class SystemSharedPreferences {

    /**
     * 获取引导界面的标识
     */

    public static boolean getGuidance(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getBoolean(
                context.getString(R.string.guidance_id_key_token), false);
    }

    /**
     * 设置引导界面的标识 *
     */
    public static void setGuidance(Context context, boolean gmark) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences.edit()

                .putBoolean(context.getString(R.string.guidance_id_key_token), gmark)
                .commit();
    }


    /**
     * 设置记住密码的标识 *
     */
    public static void setCheckSate(Context context, boolean checksate) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences
                .edit()
                .putBoolean(context.getString(R.string.user_check_key_token),
                        checksate).commit();

    }


    /**
     * 设置用户名和密码
     */
    public static void setUserNameAndPassWord(Context context, String userName,
                                              String passWord) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences
                .edit()
                .putString(context.getString(R.string.user_name_key_token),
                        userName)
                .putString(context.getString(R.string.user_pwd_key_token),
                        passWord).commit();
    }


    /**
     * 设置用户Id *
     */
    public static void setUserId(Context context, String userId) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences.edit()

                .putString(context.getString(R.string.user_id_key_token), userId).commit();
    }

    /**
     * 获取用户Id
     */

    public static String getUserId(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getString(
                context.getString(R.string.user_id_key_token), "");
    }

    /**
     * 设置人员Id *
     */
    public static void setUserPersionId(Context context, String userId) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences.edit()

                .putString(context.getString(R.string.persion_id_key_token), userId)
                .commit();
    }


    /**
     * 设置用户账户 *
     */
    public static void setUserName(Context context, String userName) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences
                .edit()
                .putString(context.getString(R.string.user_account_key_token),
                        userName).commit();
    }


    /**
     * 设置用户所属站点 *
     */
    public static void setUserSite(Context context, String site) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        defaultSharedPreferences.edit()

                .putString(context.getString(R.string.user_sub_site), site).commit();
    }

    /**
     * 获取用户所属站点
     */

    public static String getUserSite(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getString(
                context.getString(R.string.user_sub_site), "");
    }


    /**
     * 获取用户账户
     */

    public static String getUserName(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getString(
                context.getString(R.string.user_account_key_token), "");
    }
}
