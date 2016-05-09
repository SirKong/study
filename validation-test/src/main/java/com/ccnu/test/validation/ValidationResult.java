package com.ccnu.test.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

	// 校验结果是否有错
	private boolean hasErrors;

	// 校验错误信息
	private List<String> errorMsg = new ArrayList<String>();

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public void addErrorMsg(String errorMsg){
		this.errorMsg.add(errorMsg);
	}

	@Override
	public String toString() {
		return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg=" + errorMsg + "]";
	}

}