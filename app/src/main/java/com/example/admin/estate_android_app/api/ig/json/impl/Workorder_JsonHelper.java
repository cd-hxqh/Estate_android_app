package com.example.admin.estate_android_app.api.ig.json.impl;

import com.example.admin.estate_android_app.api.ig.json.JsonHelper;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.example.admin.estate_android_app.model.Workorder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;

import java.io.IOException;
import java.util.ArrayList;


public final class Workorder_JsonHelper
        implements JsonHelper<Workorder> {
    private static final String TAG = "Workorder_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Workorder> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Workorder> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Workorder>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Workorder parsed = parseFromJson(jp);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Workorder
     */
    public static Workorder parseFromJson(JsonParser jp)
            throws IOException {
        Workorder instance = new Workorder();

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

    public static boolean processSingleField(Workorder instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("workorderid".equals(fieldName)) {
            instance.workorderid= jp.getValueAsInt();
            return true;
        } else if ("wonum".equals(fieldName)) {
            instance.wonum = jp.getValueAsString();
            return true;
        } else if ("wodescription".equals(fieldName)) {
            instance.wodescription = jp.getValueAsString();
        } else if ("location".equals(fieldName)) {
            instance.location = jp.getValueAsString();
        }else if ("locdescription".equals(fieldName)) {
            instance.locdescription = jp.getValueAsString();
        }else if ("assetnum".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
        }else if ("assetdescription".equals(fieldName)) {
            instance.assetdescription = jp.getValueAsString();
        }else if ("parent".equals(fieldName)) {
            instance.parent = jp.getValueAsString();
        }else if ("classstructureid".equals(fieldName)) {
            instance.classstructureid = jp.getValueAsString();
        }else if ("classdescription".equals(fieldName)) {
            instance.classdescription = jp.getValueAsString();
        }else if ("checkdate".equals(fieldName)) {
            instance.checkdate = jp.getValueAsString();
        }




        else if ("udcheckresult".equals(fieldName)) {
            instance.udcheckresult = jp.getValueAsString();
        }else if ("siteid".equals(fieldName)) {
            instance.siteid = jp.getValueAsString();
        }else if ("sitedesc".equals(fieldName)) {
            instance.sitedesc = jp.getValueAsString();
        }else if ("woclass".equals(fieldName)) {
            instance.woclass = jp.getValueAsString();
        }else if ("worktype".equals(fieldName)) {
            instance.worktype = jp.getValueAsString();
        }else if ("worktypedesc".equals(fieldName)) {
            instance.worktypedesc = jp.getValueAsString();
        }else if ("failurecode".equals(fieldName)) {
            instance.failurecode = jp.getValueAsString();
        }else if ("faildate".equals(fieldName)) {
            instance.faildate = jp.getValueAsString();
        }else if ("problemcode".equals(fieldName)) {
            instance.problemcode = jp.getValueAsString();
        }else if ("issurvey".equals(fieldName)) {
            instance.issurvey = jp.getValueAsString();
        }else if ("status".equals(fieldName)) {
            instance.status = jp.getValueAsString();
        }else if ("statusdate".equals(fieldName)) {
            instance.statusdate = jp.getValueAsString();
        }else if ("istask".equals(fieldName)) {
            instance.istask = jp.getValueAsString();
        }else if ("hadsurveyed".equals(fieldName)) {
            instance.hadsurveyed = jp.getValueAsString();
        }else if ("paidcservicecharge".equals(fieldName)) {
            instance.paidcservicecharge = jp.getValueAsString();
        }else if ("targstartdate".equals(fieldName)) {
            instance.targstartdate = jp.getValueAsString();
        }else if ("targcompdate".equals(fieldName)) {
            instance.targcompdate = jp.getValueAsString();
        }else if ("schedstart".equals(fieldName)) {
            instance.schedstart = jp.getValueAsString();
        }else if ("schedfinish".equals(fieldName)) {
            instance.schedfinish = jp.getValueAsString();
        }else if ("actstart".equals(fieldName)) {
            instance.actstart = jp.getValueAsString();
        }else if ("actfinish".equals(fieldName)) {
            instance.actfinish = jp.getValueAsString();
        }else if ("estdur".equals(fieldName)) {
            instance.estdur = jp.getValueAsString();
        }else if ("reportedby".equals(fieldName)) {
            instance.reportedby = jp.getValueAsString();
        }else if ("repdis".equals(fieldName)) {
            instance.repdis = jp.getValueAsString();
        }else if ("reportdate".equals(fieldName)) {
            instance.reportdate = jp.getValueAsString();
        }else if ("onbehalfof".equals(fieldName)) {
            instance.onbehalfof = jp.getValueAsString();
        }else if ("onbdis".equals(fieldName)) {
            instance.onbdis = jp.getValueAsString();
        }else if ("phone".equals(fieldName)) {
            instance.phone = jp.getValueAsString();
        }else if ("supervisor".equals(fieldName)) {
            instance.supervisor = jp.getValueAsString();
        }else if ("supdis".equals(fieldName)) {
            instance.supdis = jp.getValueAsString();
        }else if ("crewid".equals(fieldName)) {
            instance.crewid = jp.getValueAsString();
        }else if ("lead".equals(fieldName)) {
            instance.lead = jp.getValueAsString();
        }else if ("lead".equals(fieldName)) {
            instance.lead = jp.getValueAsString();
        }else if ("leadis".equals(fieldName)) {
            instance.leadis = jp.getValueAsString();
        }else if ("persongroup".equals(fieldName)) {
            instance.persongroup = jp.getValueAsString();
        }else if ("vendor".equals(fieldName)) {
            instance.vendor = jp.getValueAsString();
        }else if ("owner".equals(fieldName)) {
            instance.owner = jp.getValueAsString();
        }else if ("ownergroup".equals(fieldName)) {
            instance.ownergroup = jp.getValueAsString();
        }else if ("commoditygroup".equals(fieldName)) {
            instance.commoditygroup = jp.getValueAsString();
        }else if ("jpnum".equals(fieldName)) {
            instance.jpnum = jp.getValueAsString();
        }else if ("safetyplanid".equals(fieldName)) {
            instance.safetyplanid = jp.getValueAsString();
        }else if ("surveyedto".equals(fieldName)) {
            instance.surveyedto = jp.getValueAsString();
        }else if ("surveyway".equals(fieldName)) {
            instance.surveyway = jp.getValueAsString();
        }else if ("udsatisficing".equals(fieldName)) {
            instance.udsatisficing = jp.getValueAsString();
        }

        else if ("needreturn".equals(fieldName)) {
            instance.needreturn = jp.getValueAsString();
        }else if ("surveydate".equals(fieldName)) {
            instance.surveydate = jp.getValueAsString();
        }else if ("surveytor".equals(fieldName)) {
            instance.surveytor = jp.getValueAsString();
        }else if ("surveyopinion".equals(fieldName)) {
            instance.surveyopinion = jp.getValueAsString();
        }else if ("internalstatus".equals(fieldName)) {
            instance.internalstatus = jp.getValueAsString();
        }else if ("rowstamp".equals(fieldName)) {
            instance.rowstamp = jp.getValueAsInt();
        }else if ("wid".equals(fieldName)) {
            instance.wid = jp.getValueAsInt();
        }else if ("udtkchecksite".equals(fieldName)) {
            instance.udtkchecksite = jp.getValueAsString();
        }
        return false;
    }

    /**
     * 解析Workorder_List*
     */
    public static ArrayList<Workorder> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Workorder*
     */
    public static Workorder parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
