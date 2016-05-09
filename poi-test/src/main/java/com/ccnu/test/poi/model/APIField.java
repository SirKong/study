package com.ccnu.test.poi.model;

public class APIField {

	public static final String FIELD_DIRECTION_IN = "0";
	public static final String FIELD_DIRECTION_OUT = "1";

	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 字段描述
	 */
	private String fieldDesc;
	/**
	 * 出入参方向
	 */
	private String fieldDirection;
	/**
	 * 字段是否公共字段
	 */
	private boolean isPublic;
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
	private boolean isListField;
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldDirection() {
		return fieldDirection;
	}

	public void setFieldDirection(String fieldDirection) {
		this.fieldDirection = fieldDirection;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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

	public static String getFieldDirectionOut() {
		return FIELD_DIRECTION_OUT;
	}
	
	public boolean isListField() {
		return isListField;
	}
	
	public void setListField(boolean isListField) {
		this.isListField = isListField;
	}

	@Override
	public String toString() {
		return "APIField [fieldName=" + fieldName + ", fieldType=" + fieldType + ", fieldDesc=" + fieldDesc
				+ ", fieldDirection=" + fieldDirection + ", isPublic=" + isPublic + ", required=" + required
				+ ", comment=" + comment + ", isListField=" + isListField + "]";
	}

}
