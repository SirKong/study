package com.ccnu.test.validation.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.ccnu.test.validation.constraints.CheckRule;
import com.ccnu.test.validation.constraints.CheckRule.Rule;
import com.ccnu.test.validation.constraints.Option;
import com.ccnu.test.validation.constraints.OptionIf;

@CheckRule(rules = { @Rule(exp = "(getTrade_acco()!=null or getTa_acco()!=null or getClient_id()!=null or (getCust_type()!=null and getId_kind_gb()!=null and getId_no()!=null))", message = "理财账号、交易帐号、客户编号以及证件类型+证件号码+客户类型不能同时为空") })
public class FundAccountQueryParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 交易委托方式
	 */
	private String trust_way;
	/**
	 * 请求行数
	 */
	private String request_num;
	/**
	 * 返回排序方式
	 */
	private String sort_direction;
	/**
	 * 交易账号
	 */
//	@Pattern(regexp = "/^\\d{4,17}$/", message = "trade_acco传入值不合法")
	private String trade_acco;
	/**
	 * TA账号
	 */
	@NotBlank(message="ta_acco不能为空")
	@Pattern(regexp = "[\\*a-zA-Z0-9]{1,12}", message = "ta_acco传入值不合法")
	private String ta_acco;
	/**
	 * 客户编号
	 */
	@Pattern(regexp = "[\\d]{1,12}", message = "client_id传入值不合法")
	private String client_id;
	/**
	 * 客户类别
	 */
	@Option({ "0", "1" })
	private String cust_type;
	/**
	 * 证件类别
	 */
	@OptionIf(optionItems = {
			@OptionIf.OptionItem(condition = "'1'.equals(getCust_type())", options = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
					"9", "A" }),
			@OptionIf.OptionItem(condition = "'0'.equals(getCust_type())", options = { "0", "1", "2", "3", "4", "5", "6", "7", "8" }) })
	private String id_kind_gb;
	/**
	 * 证件号码
	 */
	private String id_no;

	public String getTrust_way() {
		return trust_way;
	}

	public void setTrust_way(String trust_way) {
		this.trust_way = trust_way;
	}

	public String getRequest_num() {
		return request_num;
	}

	public void setRequest_num(String request_num) {
		this.request_num = request_num;
	}

	public String getSort_direction() {
		return sort_direction;
	}

	public void setSort_direction(String sort_direction) {
		this.sort_direction = sort_direction;
	}

	public String getTrade_acco() {
		return trade_acco;
	}

	public void setTrade_acco(String trade_acco) {
		this.trade_acco = trade_acco;
	}

	public String getTa_acco() {
		return ta_acco;
	}

	public void setTa_acco(String ta_acco) {
		this.ta_acco = ta_acco;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getCust_type() {
		return cust_type;
	}

	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}

	public String getId_kind_gb() {
		return id_kind_gb;
	}

	public void setId_kind_gb(String id_kind_gb) {
		this.id_kind_gb = id_kind_gb;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	@Override
	public String toString() {
		return "FundAccountQueryParam [trust_way=" + trust_way + ", request_num=" + request_num + ", sort_direction="
				+ sort_direction + ", trade_acco=" + trade_acco + ", ta_acco=" + ta_acco + ", client_id=" + client_id
				+ ", cust_type=" + cust_type + ", id_kind_gb=" + id_kind_gb + ", id_no=" + id_no + "]";
	}

}