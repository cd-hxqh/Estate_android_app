// Copyright 2004-present Facebook. All Rights Reserved.

package com.example.admin.estate_android_app.api.ig.json;

import com.example.admin.estate_android_app.api.ig.json.impl.BulletinBoard_JsonHelper;
import com.example.admin.estate_android_app.model.BulletinBoard;

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

}
