// Copyright 2004-present Facebook. All Rights Reserved.

package com.example.admin.estate_android_app.api.ig.json;

import com.example.admin.estate_android_app.api.ig.json.impl.BulletinBoard_JsonHelper;
import com.example.admin.estate_android_app.api.ig.json.impl.DiyInvuse_JsonHelper;
import com.example.admin.estate_android_app.api.ig.json.impl.Toolitem_JsonHelper;
import com.example.admin.estate_android_app.api.ig.json.impl.Workorder_JsonHelper;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.example.admin.estate_android_app.model.DiyInvuse;
import com.example.admin.estate_android_app.model.Toolitem;
import com.example.admin.estate_android_app.model.Workorder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Helper class to parse the model.
 */
public class Ig_Json_Model {

    private static final String TAG = "Ig_Json_Model";


    /**
     * 公告栏解析*
     */
    public static ArrayList<BulletinBoard> parseBulletinBoardString(String input) throws IOException {
        return BulletinBoard_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 工单解析*
     */
    public static ArrayList<Workorder> parseWorkorderString(String input) throws IOException {
        return Workorder_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 库存使用情况*
     */
    public static ArrayList<DiyInvuse> parseDiyInvuseString(String input) throws IOException {
        return DiyInvuse_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 工具管理*
     */
    public static ArrayList<Toolitem> parseToolitemString(String input) throws IOException {
        return Toolitem_JsonHelper.parseFromJsonList(input);
    }

}
