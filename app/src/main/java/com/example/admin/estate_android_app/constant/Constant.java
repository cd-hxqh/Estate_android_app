package com.example.admin.estate_android_app.constant;

/**
 * Created by admin on 2016/3/22.
 * TODO设置App界面静态常量的类
 */
public class Constant {
    /** 服务器的Ip地址 -（远程） **/
    public final static String SERVER_IP = "http://119.253.41.96:8080";
    /**
     * 服务器验证的接口
     */
    public final static String USER_LOGIN = SERVER_IP
            + "/cdhxqh/services/user/auth/";

    /** 获取信息接口 **/
    public final static String RECORD_LIST = SERVER_IP
            + "/cdhxqh/services/cmd/getcboset";


    /** 公告栏的表名 **/
    public static final String BULLETIN_TABLE = "BULLETINBOARD";
}
