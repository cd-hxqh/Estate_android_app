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


    /**
     * 数据库路径名称/有内存卡的文件路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/com.estate.client.activity/";
    /**
     * 数据库路径名称/无内存卡的文件路径
     */
    public static final String NOT_SDCARD_PATH_DB ="/data/data/";
    /** 存放图片的路径名称 **/
    public static final String PATH_IMAGE = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/com.estate.client.activity/image";

    /** 文件存放路径 **/
    public static final String FILE_PATH = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/com.estate.client.activity/";







    /** 公告栏的表名 **/
    public static final String BULLETIN_TABLE = "BULLETINBOARD";

    /** 工单表名 **/
    public static final String WORKORDER_TABLE = "cdhxqh_v_workorder";
    public static final String WORKORDER_LIST = "workorderid,wonum,wodescription,"
            + "location,locdescription,classstructureid,classdescription,"
            + "worktype,worktypedesc,status,statusdate,"
            + "assetnum,assetdescription,parent,"
            + "checkdate,udcheckresult,siteid,"
            + "sitedesc,woclass,failurecode,faildate,problemcode,"
            + "issurvey,istask,hadsurveyed,paidcservicecharge,"
            + "targstartdate,targcompdate,schedstart,schedfinish,actstart,actfinish,estdur,reportedby,repdis,"
            + "reportdate,onbehalfof,onbdis,phone,supervisor,supdis,crewid,lead,"
            + "leadis,vendor,owner,wopriority,jpnum,safetyplanid,internalstatus,rowstamp";

    /** 使用情况的表名 **/
    public static final String CDHXQH_V_INVUSE_TABLE = "CDHXQH_V_INVUSE";

    /** 工具管理--工具 **/
    public static final String TOOLITEM_TABLE = "cdhxqh_v_toolitem";
    public static final String TOOLITEM_LIST = "itemid,itemnum,description,commoditygroup,groupname,metername,itemsetid,status,lottype,issueunit,msdsnum,receipttolerance";
}
