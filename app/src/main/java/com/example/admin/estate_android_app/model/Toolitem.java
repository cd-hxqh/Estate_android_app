package com.example.admin.estate_android_app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 工具管理
 *
 */
@JsonType
public class Toolitem extends Entity {
    private static final String TAG = "Toolitem";
    private static final long serialVersionUID = 2015050105L;
    /** 服务器Id **/
    @JsonField(fieldName = "itemid")
    public long itemid;
    /** 工具编号 **/
    @JsonField(fieldName = "itemnum")
    public String itemnum;

    /** 描述 **/
    @JsonField(fieldName = "description")
    public String description;

    /** 商品组 **/
    @JsonField(fieldName = "commoditygroup")
    public String commoditygroup;

    /** 商品代码 **/
    @JsonField(fieldName = "commodity")
    public String commodity;

    /** 计量表组 **/
    @JsonField(fieldName = "groupname")
    public String groupname;

    /** 计量表 **/
    @JsonField(fieldName = "metername")
    public String metername;

    /** 项目集 **/
    @JsonField(fieldName = "itemsetid")
    public String itemsetid;

    /** 状态 **/
    @JsonField(fieldName = "status")
    public String status;

    /** 批次类型 **/
    @JsonField(fieldName = "lottype")
    public String lottype;

    /** 发放单位 **/
    @JsonField(fieldName = "issueunit")
    public String issueunit;

    /** MSDS **/
    @JsonField(fieldName = "msdsnum")
    public String msdsnum;

    /** 接收容差百分比 **/
    @JsonField(fieldName = "receipttolerance")
    public String receipttolerance;


    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommoditygroup() {
        return commoditygroup;
    }

    public void setCommoditygroup(String commoditygroup) {
        this.commoditygroup = commoditygroup;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getMetername() {
        return metername;
    }

    public void setMetername(String metername) {
        this.metername = metername;
    }

    public String getItemsetid() {
        return itemsetid;
    }

    public void setItemsetid(String itemsetid) {
        this.itemsetid = itemsetid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLottype() {
        return lottype;
    }

    public void setLottype(String lottype) {
        this.lottype = lottype;
    }

    public String getIssueunit() {
        return issueunit;
    }

    public void setIssueunit(String issueunit) {
        this.issueunit = issueunit;
    }

    public String getMsdsnum() {
        return msdsnum;
    }

    public void setMsdsnum(String msdsnum) {
        this.msdsnum = msdsnum;
    }

    public String getReceipttolerance() {
        return receipttolerance;
    }

    public void setReceipttolerance(String receipttolerance) {
        this.receipttolerance = receipttolerance;
    }
}
