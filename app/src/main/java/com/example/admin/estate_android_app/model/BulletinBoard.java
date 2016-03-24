package com.example.admin.estate_android_app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 公告栏
 *
 *
 */
@JsonType
public class BulletinBoard extends Entity {
    private static final String TAG = "Assets";
    private static final long serialVersionUID = 2015050105L;
    @JsonField(fieldName = "bulletinboardid")
    /** 服务器的Id **/
    public String bulletinboardid;

    /** 主题 **/
    @JsonField(fieldName = "subject")
    public String subject;
    /** 消息 **/
    @JsonField(fieldName = "message")
    public String message;
    /** 负责人 **/
    @JsonField(fieldName = "postby")
    public String postby;
    /** 发布日期 **/
    @JsonField(fieldName = "postdate")
    public String postdate;
    /** 截止日期 **/
    @JsonField(fieldName = "expiredate")
    public String expiredate;
    @JsonField(fieldName = "bulletinboarduid")
    public String bulletinboarduid;
    @JsonField(fieldName = "rowstamp")
    public String rowstamp;
    /** 状态 **/
    @JsonField(fieldName = "status")
    public String status;
    @JsonField(fieldName = "customer")
    public String customer;
    @JsonField(fieldName = "pluspinsertcustomer")
    public String pluspinsertcustomer;


}
