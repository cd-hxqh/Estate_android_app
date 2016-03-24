package com.example.admin.estate_android_app.api.ig.json.impl;

import com.example.admin.estate_android_app.api.ig.json.JsonHelper;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.example.admin.estate_android_app.model.DiyInvuse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;

import java.io.IOException;
import java.util.ArrayList;


public final class DiyInvuse_JsonHelper
        implements JsonHelper<DiyInvuse> {
    private static final String TAG = "DiyInvuse_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<DiyInvuse> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<DiyInvuse> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<DiyInvuse>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                DiyInvuse parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析DiyInvuse
     */
    public static DiyInvuse parseFromJson(JsonParser jp)
            throws IOException {
        DiyInvuse instance = new DiyInvuse();

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

    public static boolean processSingleField(DiyInvuse instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("invuseid".equals(fieldName)) {
            instance.invuseid= jp.getValueAsLong();
            return true;
        } else if ("invusenum".equals(fieldName)) {
            instance.invusenum = jp.getValueAsString();
            return true;
        } else if ("description".equals(fieldName)) {
            instance.description = jp.getValueAsString();
        } else if ("fromstoreloc".equals(fieldName)) {
            instance.fromstoreloc = jp.getValueAsString();
        }else if ("locdescription".equals(fieldName)) {
            instance.locdescription = jp.getValueAsString();
        }else if ("invowner".equals(fieldName)) {
            instance.invowner = jp.getValueAsString();
        }else if ("ownerdisplayname".equals(fieldName)) {
            instance.ownerdisplayname = jp.getValueAsString();
        }else if ("udnextperson".equals(fieldName)) {
            instance.udnextperson = jp.getValueAsString();
        }else if ("displayname".equals(fieldName)) {
            instance.displayname = jp.getValueAsString();
        }else if ("usetype".equals(fieldName)) {
            instance.usetype = jp.getValueAsString();
        }else if ("siteid".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        }else if ("status".equals(fieldName)) {
            instance.status = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析DiyInvuse_List*
     */
    public static ArrayList<DiyInvuse> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析BulletinBoard*
     */
    public static DiyInvuse parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
