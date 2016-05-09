package com.ccnu.test.webtest.model;

public class APIField {

	public static final String FIELD_DIRECTION_IN = "0";
	public static final String FIELD_DIRECTION_OUT = "1";

	/**
	 * 字段名
	 */
	private String fieldname;
	/**
	 * 字段类型
	 */
	private String fieldtype;
	/**
	 * 字段描述
	 */
	private String fielddesc;
	/**
	 * 出入参方向  0：入参  1：出参
	 */
	private String fielddirection;
	/**
	 * 字段是否公共字段
	 */
	private boolean publicfield;
	/**
	 * 字段是否必填
	 */
	private boolean required;
	/**
	 * 字段备注
	 */
	private String comment;

	/**
	 * 是否结果集字段
	 */
	private boolean listfield;
	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}

	public String getFielddesc() {
		return fielddesc;
	}

	public void setFielddesc(String fielddesc) {
		this.fielddesc = fielddesc;
	}

	public String getFielddirection() {
		return fielddirection;
	}

	public void setFielddirection(String fielddirection) {
		this.fielddirection = fielddirection;
	}

	public boolean isPublicfield() {
		return publicfield;
	}

	public void setPublicfield(boolean isPublic) {
		this.publicfield = isPublic;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isListfield() {
		return listfield;
	}
	
	public void setListfield(boolean isListField) {
		this.listfield = isListField;
	}

	@Override
	public String toString() {
		return "APIField [fieldname=" + fieldname + ", fieldtype=" + fieldtype + ", fielddesc=" + fielddesc
				+ ", fielddirection=" + fielddirection + ", publicfield=" + publicfield + ", required=" + required
				+ ", comment=" + comment + ", listfield=" + listfield + "]";
	}

}
