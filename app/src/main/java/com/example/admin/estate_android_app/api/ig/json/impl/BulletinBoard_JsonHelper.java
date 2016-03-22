package com.example.admin.estate_android_app.api.ig.json.impl;

import android.util.Log;

import com.example.admin.estate_android_app.api.ig.json.JsonHelper;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;

import java.io.IOException;
import java.util.ArrayList;


public final class BulletinBoard_JsonHelper
        implements JsonHelper<BulletinBoard> {
    private static final String TAG = "BulletinBoard_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<BulletinBoard> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<BulletinBoard> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<BulletinBoard>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                BulletinBoard parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析BulletinBoard
     */
    public static BulletinBoard parseFromJson(JsonParser jp)
            throws IOException {
        BulletinBoard instance = new BulletinBoard();

        // validate that we're on the right token
        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
            jp.skipChildren();
            return null;
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();
            processSingleField(instance, fieldName, jp);
            jp.skipChildren();
        }

        return instance;
    }

    public static boolean processSingleField(BulletinBoard instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("bulletinboardid".equals(fieldName)) {
            instance.bulletinboardid= jp.getValueAsString();
            return true;
        } else if ("subject".equals(fieldName)) {
            instance.subject = jp.getValueAsString();
            return true;
        } else if ("message".equals(fieldName)) {
            instance.message = jp.getValueAsString();
        } else if ("postby".equals(fieldName)) {
            instance.postby = jp.getValueAsString();
        }else if ("postdate".equals(fieldName)) {
            instance.postdate = jp.getValueAsString();
        }else if ("expiredate".equals(fieldName)) {
            instance.expiredate = jp.getValueAsString();
        }else if ("bulletinboarduid".equals(fieldName)) {
            instance.bulletinboarduid = jp.getValueAsString();
        }else if ("rowstamp".equals(fieldName)) {
            instance.rowstamp = jp.getValueAsString();
        }else if ("status".equals(fieldName)) {
            instance.status = jp.getValueAsString();
        }else if ("customer".equals(fieldName)) {
            instance.customer = jp.getValueAsString();
        }else if ("pluspinsertcustomer".equals(fieldName)) {
            instance.pluspinsertcustomer = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析BulletinBoard_List*
     */
    public static ArrayList<BulletinBoard> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析BulletinBoard*
     */
    public static BulletinBoard parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
