package com.ccnu.test.poi.model;

import java.util.List;

public class APIDescriptor {

	/**
	 * api功能号
	 */
	private String functionNo;
	/**
	 * api对应的class名字
	 */
	private String className;
	/**
	 * api对应的访问URL
	 */
	private String url;
	/**
	 * api对应的功能描述
	 */
	private String functionDesc;
	/**
	 * api创建人
	 */
	private String createUser;
	/**
	 * api创建日期
	 */
	private String createDate;
	/**
	 * api对应的最后更新日期
	 */
	private String lastUpdateDate;
	/**
	 * api对应的最后更新人
	 */
	private String lastUpdateUser;
	/**
	 * api修改日志
	 */
	private String updateLog;

	/**
	 * 接口版本
	 */
	private String version = "V1.0";

	/**
	 * API所包含的字段列表
	 */
	private List<APIField> apiFields;

	public APIDescriptor() {

	}

	public APIDescriptor(List<APIField> apiFields) {
		this.apiFields = apiFields;
	}

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setApiFields(List<APIField> apiFields) {
		this.apiFields = apiFields;
	}

	public List<APIField> getApiFields() {
		return apiFields;
	}

	public String getCreateUser() {
		return createUser;
	}
	
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
	@Override
	public String toString() {
		return "APIDescriptor [functionNo=" + functionNo + ", className=" + className + ", url=" + url
				+ ", functionDesc=" + functionDesc + ", createUser=" + createUser + ", createDate=" + createDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateUser=" + lastUpdateUser + ", updateLog="
				+ updateLog + ", version=" + version + ", apiFields=" + apiFields + "]";
	}

}
