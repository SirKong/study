package com.ccnu.test.webtest.model;

import java.util.List;

public class APIDescriptor {

    /**
     * id
     */
    private String uuid;

    /**
     * api功能号
     */
    private String functionno;
    /**
     * api对应的class名字
     */
    private String classname;
    /**
     * api对应的访问URL
     */
    private String url;
    /**
     * api对应的功能描述
     */
    private String functiondesc;
    /**
     * api创建人
     */
    private String createuser;
    /**
     * api创建日期
     */
    private String createdate;
    /**
     * api对应的最后更新日期
     */
    private String lastupdatedate;
    /**
     * api对应的最后更新人
     */
    private String lastupdateuser;
    /**
     * api修改日志
     */
    private String updatelog;

    /**
     * 接口版本
     */
    private String version = "V1.0";

    /**
     * 接口状态 1：可用 2：不推荐使用但尚未退役 3：接口已退役
     */
    private String functionstatus;

    /**
     * 接口归属哪个系统 0：网上交易 1：直销 2：数据中心
     */
    private String fundctionbelong;

    /**
     * API所包含的字段列表
     */
    private List<APIField> apifields;

    public APIDescriptor() {

    }

    public APIDescriptor(List<APIField> apiFields) {
        this.apifields = apiFields;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunctiondesc() {
        return functiondesc;
    }

    public void setFunctiondesc(String functiondesc) {
        this.functiondesc = functiondesc;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(String lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public String getUpdatelog() {
        return updatelog;
    }

    public void setUpdatelog(String updatelog) {
        this.updatelog = updatelog;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setApifields(List<APIField> apifields) {
        this.apifields = apifields;
    }

    public List<APIField> getApifields() {
        return apifields;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getFunctionstatus() {
        return functionstatus;
    }

    public void setFunctionstatus(String functionstatus) {
        this.functionstatus = functionstatus;
    }

    public String getFundctionbelong() {
        return fundctionbelong;
    }

    public void setFundctionbelong(String fundctionbelong) {
        this.fundctionbelong = fundctionbelong;
    }

    @Override
    public String toString() {
        return "APIDescriptor [uuid=" + uuid + ", functionno=" + functionno + ", classname=" + classname + ", url="
                + url + ", functiondesc=" + functiondesc + ", createuser=" + createuser + ", createdate=" + createdate
                + ", lastupdatedate=" + lastupdatedate + ", lastupdateuser=" + lastupdateuser + ", updatelog="
                + updatelog + ", version=" + version + ", functionstatus=" + functionstatus + ", fundctionbelong="
                + fundctionbelong + ", apifields=" + apifields + "]";
    }

}
