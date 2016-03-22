package com.example.admin.estate_android_app.bean;


/**
 * Created by apple on 15/9/10.
 * 解析结果
 */
public class Results {
    private int id;
    /**
     * 返回结果*
     */
    private String cboset;
    /**
     * 返回表识
     */
    private String errorcode;
    /**
     * 返回是否成功
     */
    private String isSuccess;
    /**
     * 总共条数*
     */
    private String resultcount;
    /**
     * 显示类型
     */
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCboset() {
        return cboset;
    }

    public void setCboset(String cboset) {
        this.cboset = cboset;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResultcount() {
        return resultcount;
    }

    public void setResultcount(String resultcount) {
        this.resultcount = resultcount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
