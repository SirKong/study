package com.ccnu.test.springboot.constant;

/**
 * @author SirKong
 *         错误码枚举类
 */
public enum ErrorCodeEnum {
	Success("Success", "Success", "处理成功", 200),

	MissingOptionalParameters("MissingOptionalParameters", "Optional parameters[%s] should provide at least one",
			"选填参数[%s]至少应提供一个", 400),
	MissingParameter("MissingParameter", "The required input parameter %s for processing this request is not supplied.", "参数 %s 缺失", 400),
	MissingParameters("MissingParameters", "The required input parameter is not supplied.", "参数缺失", 400),
	InternalServerError("InternalServerError", "Internal server error.", "服务器内部错误", 500),
	GatewayDeny("GatewayDeny", "Gateway Deny.", "来源网关不允许访问该接口", 400),
	BadRequest("BadRequest", "Bad Request", "请求错误", 400),
	InvalidParameter("InvalidParameter", "Invalid parameter.", "非法参数", 400),
	ExceedSizeLimit("ExceedSizeLimit ", "Exceed size limit.", "超出请求大小限制", 400),
	ExceedQuota("ExceedQuota", "Exceed quota.", "超出配额", 400),

	UnauthorizedOperation("UnauthorizedOperation",
			"You are not authorized to perform operation (%s) on resources (%s)",
			"You are not authorized to perform operation (%s) on resources (%s)", 403),

	OperationCrossOrganizationDeny("OperationCrossOrganizationDeny", "Operation cross organization deny.", "不允许跨组织操作",
			400),
	OrganizationNotExist("OrganizationNotExist", "Organization not exist.", "组织不存在", 400),
	PolicyNotExist("PolicyNotExist", "Policy not exist.", "策略不存在", 400),
	AccountNotExist("AccountNotExist", "Account not exist.", "账号不存在", 400),
	AccountAlreadyExist("AccountAlreadyExist", "Account already exist.", "账号已存在", 400),
	HandshakeNotExist("HandshakeNotExist", "Handshake not exist.", "握手消息不存在", 400),
	HandshakeStateInvalid("HandshakeStateInvalid", "Handshake state invalid.", "握手消息状态非法", 400),
	NotHandshakeTargetAccount("NotHandshakeTargetAccount", "not handshake target account.", "非握手消息目标用户", 400),

	AlreadyCreatedOrganization("AlreadyCreatedOrganization", "Already created an organization.", "已创建过组织", 400),
	AlreadyJoinedOrganization("AlreadyJoinedOrganization", "Already joined an organization.", "已加入过组织", 400),
	HasRequestedStateHandshake("HasRequestedStateHandshake", "Has requested state handshake.", "存在请求未完成状态的握手消息",
			400),
	NotAnOrganizationUser("NotAnOrganizationUser", "Not an organization user yet.", "用户不是组织账户", 400),

	DedicatedCloudAlreadyExist("DedicatedCloudAlreadyExist", "DedicatedCloud already exist.", "专属云已存在", 400),
	DedicatedCloudNotExist("DedicatedCloudNotExist", "DedicatedCloud not exist.", "专属云不存在", 400),
	NotNdcAccount("NotNdcAccount", "Not ndc account.", "不是专属云账号", 400),
	HasBillInstance("HasBillInstance", "Has bill instance", "有计费实例", 400),
	HasArrearsBill("HasArrearsBill", "Has arrears bill", "有欠费账单", 400),
	;

	private String code;
	private String enMsg;
	private String msg;
	private int statusCode;

	ErrorCodeEnum(String code, String enMsg, String msg, int statusCode) {
		this.code = code;
		this.enMsg = enMsg;
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEnMsg() {
		return enMsg;
	}

	public void setEnMsg(String enMsg) {
		this.enMsg = enMsg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}