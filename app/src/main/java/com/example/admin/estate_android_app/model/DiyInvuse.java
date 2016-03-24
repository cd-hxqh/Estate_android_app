package com.example.admin.estate_android_app.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/25.
 * 库存使用情况
 *
 */
@JsonType
public class DiyInvuse extends Entity {
    private static final String TAG = "DiyInvuse";
    private static final long serialVersionUID = 2015050105L;

    @JsonField(fieldName = "invuseid")
    public long invuseid;

    @JsonField(fieldName = "invusenum")
    public String invusenum;


    @JsonField(fieldName = "description")
    public String description;

    @JsonField(fieldName = "fromstoreloc")
    public String fromstoreloc;

    @JsonField(fieldName = "locdescription")
    public String locdescription;

    @JsonField(fieldName = "invowner")
    public String invowner;

    @JsonField(fieldName = "ownerdisplayname")
    public String ownerdisplayname;

    @JsonField(fieldName = "udnextperson")
    public String udnextperson;

    @JsonField(fieldName = "displayname")
    public String displayname;

    @JsonField(fieldName = "usetype")
    public String usetype;

    @JsonField(fieldName = "siteid")
    public String siteid;

    @JsonField(fieldName = "status")
    public String status;

    public long getInvuseid() {
        return invuseid;
    }

    public void setInvuseid(long invuseid) {
        this.invuseid = invuseid;
    }

    public String getInvusenum() {
        return invusenum;
    }

    public void setInvusenum(String invusenum) {
        this.invusenum = invusenum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromstoreloc() {
        return fromstoreloc;
    }

    public void setFromstoreloc(String fromstoreloc) {
        this.fromstoreloc = fromstoreloc;
    }

    public String getLocdescription() {
        return locdescription;
    }

    public void setLocdescription(String locdescription) {
        this.locdescription = locdescription;
    }

    public String getInvowner() {
        return invowner;
    }

    public void setInvowner(String invowner) {
        this.invowner = invowner;
    }

    public String getOwnerdisplayname() {
        return ownerdisplayname;
    }

    public void setOwnerdisplayname(String ownerdisplayname) {
        this.ownerdisplayname = ownerdisplayname;
    }

    public String getUdnextperson() {
        return udnextperson;
    }

    public void setUdnextperson(String udnextperson) {
        this.udnextperson = udnextperson;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
