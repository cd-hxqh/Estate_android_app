package com.example.admin.estate_android_app.api.ig.json.impl;

import com.example.admin.estate_android_app.api.ig.json.JsonHelper;
import com.example.admin.estate_android_app.model.DiyInvuse;
import com.example.admin.estate_android_app.model.Toolitem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 工具管理
 */
public final class Toolitem_JsonHelper
        implements JsonHelper<Toolitem> {
    private static final String TAG = "Toolitem_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Toolitem> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Toolitem> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Toolitem>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Toolitem parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Toolitem
     */
    public static Toolitem parseFromJson(JsonParser jp)
            throws IOException {
        Toolitem instance = new Toolitem();

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

    public static boolean processSingleField(Toolitem instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("itemid".equals(fieldName)) {
            instance.itemid= jp.getValueAsLong();
            return true;
        } else if ("itemnum".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("description".equals(fieldName)) {
            instance.description = jp.getValueAsString();
        } else if ("commoditygroup".equals(fieldName)) {
            instance.commoditygroup = jp.getValueAsString();
        }else if ("commodity".equals(fieldName)) {
            instance.commodity = jp.getValueAsString();
        }else if ("groupname".equals(fieldName)) {
            instance.groupname = jp.getValueAsString();
        }else if ("metername".equals(fieldName)) {
            instance.metername = jp.getValueAsString();
        }else if ("itemsetid".equals(fieldName)) {
            instance.itemsetid = jp.getValueAsString();
        }else if ("status".equals(fieldName)) {
            instance.status = jp.getValueAsString();
        }else if ("lottype".equals(fieldName)) {
            instance.lottype = jp.getValueAsString();
        }else if ("issueunit".equals(fieldName)) {
            instance.issueunit = jp.getValueAsString();
        }else if ("msdsnum".equals(fieldName)) {
            instance.msdsnum = jp.getValueAsString();
        }
        else if ("receipttolerance".equals(fieldName)) {
            instance.receipttolerance = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Toolitem_List*
     */
    public static ArrayList<Toolitem> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Toolitem*
     */
    public static Toolitem parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
