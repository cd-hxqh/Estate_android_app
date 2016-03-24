package com.example.admin.estate_android_app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 工单表
 *
 */
@JsonType
public class Workorder extends Entity {
    private static final String TAG = "Workorder";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "workorderid")
    public int workorderid;

    @JsonField(fieldName = "wonum")
    public String wonum;

    @JsonField(fieldName = "wodescription")
    public String wodescription;

    @JsonField(fieldName = "location")
    public String location;

    @JsonField(fieldName = "locdescription")
    public String locdescription;

    @JsonField(fieldName = "assetnum")
    public String assetnum;


    @JsonField(fieldName = "assetdescription")
    public String assetdescription;

    @JsonField(fieldName = "parent")
    public String parent;

    @JsonField(fieldName = "classstructureid")
    public String classstructureid;

    @JsonField(fieldName = "classdescription")
    public String classdescription;


    @JsonField(fieldName = "checkdate")
    public String checkdate;

    @JsonField(fieldName = "udcheckresult")
    public String udcheckresult;


    @JsonField(fieldName = "siteid")
    public String siteid;

    @JsonField(fieldName = "sitedesc")
    public String sitedesc;

    @JsonField(fieldName = "woclass")
    public String woclass;

    @JsonField(fieldName = "worktype")
    public String worktype;

    @JsonField(fieldName = "worktypedesc")
    public String worktypedesc;

    @JsonField(fieldName = "failurecode")
    public String failurecode;

    @JsonField(fieldName = "faildate")
    public String faildate;

    @JsonField(fieldName = "problemcode")
    public String problemcode;

    @JsonField(fieldName = "issurvey")
    public String issurvey;

    @JsonField(fieldName = "status")
    public String status;

    @JsonField(fieldName = "statusdate")
    public String statusdate;

    @JsonField(fieldName = "istask")
    public String istask;

    @JsonField(fieldName = "hadsurveyed")
    public String hadsurveyed;

    @JsonField(fieldName = "paidcservicecharge")
    public String paidcservicecharge;

    @JsonField(fieldName = "targstartdate")
    public String targstartdate;

    @JsonField(fieldName = "targcompdate")
    public String targcompdate;

    @JsonField(fieldName = "schedstart")
    public String schedstart;

    @JsonField(fieldName = "schedfinish")
    public String schedfinish;

    @JsonField(fieldName = "actstart")
    public String actstart;

    @JsonField(fieldName = "actfinish")
    public String actfinish;

    @JsonField(fieldName = "estdur")
    public String estdur;

    @JsonField(fieldName = "reportedby")
    public String reportedby;

    @JsonField(fieldName = "repdis")
    public String repdis;

    @JsonField(fieldName = "reportdate")
    public String reportdate;

    @JsonField(fieldName = "onbehalfof")
    public String onbehalfof;

    @JsonField(fieldName = "onbdis")
    public String onbdis;

    @JsonField(fieldName = "phone")
    public String phone;

    @JsonField(fieldName = "supervisor")
    public String supervisor;

    @JsonField(fieldName = "supdis")
    public String supdis;

    @JsonField(fieldName = "crewid")
    public String crewid;

    @JsonField(fieldName = "lead")
    public String lead;

    @JsonField(fieldName = "leadis")
    public String leadis;

    @JsonField(fieldName = "persongroup")
    public String persongroup;

    @JsonField(fieldName = "vendor")
    public String vendor;

    @JsonField(fieldName = "owner")
    public String owner;

    @JsonField(fieldName = "ownergroup")
    public String ownergroup;

    @JsonField(fieldName = "commoditygroup")
    public String commoditygroup;

    @JsonField(fieldName = "commodity")
    public String commodity;

    @JsonField(fieldName = "wopriority")
    public String wopriority;

    @JsonField(fieldName = "jpnum")
    public String jpnum;

    @JsonField(fieldName = "safetyplanid")
    public String safetyplanid;

    @JsonField(fieldName = "surveyedto")
    public String surveyedto;

    @JsonField(fieldName = "surveyway")
    public String surveyway;

    @JsonField(fieldName = "udsatisficing")
    public String udsatisficing;

    @JsonField(fieldName = "needreturn")
    public String needreturn;

    @JsonField(fieldName = "surveydate")
    public String surveydate;

    @JsonField(fieldName = "surveytor")
    public String surveytor;

    @JsonField(fieldName = "surveyopinion")
    public String surveyopinion;

    @JsonField(fieldName = "internalstatus")
    public String internalstatus;

    @JsonField(fieldName = "rowstamp")
    public int rowstamp;

    @JsonField(fieldName = "wid")
    public long wid;

    @JsonField(fieldName = "udtkchecksite")
    public String udtkchecksite;



}
