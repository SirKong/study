package com.ccnu.test.fundtest.model;

import java.io.Serializable;

/**
 * Created by gongyb08837 on 2015/11/22.
 */
public class FundMarket implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 接收行情日期
     */
    private String accept_hq_date;
    /**
     * 基金代码
     */
    private String fund_code;
    /**
     * 份额分类
     */
    private String share_type;
    /**
     * 基金名称
     */
    private String fund_name;
    /**
     * T-1日基金单位净值
     */
    private String nav;
    /**
     * 基金总份额
     */
    private String fund_share;
    /**
     * 基金状态
     */
    private String fund_status;
    /**
     * 基金收益
     */
    private String fund_curr_income;
    /**
     * 基金收益率
     */
    private String fund_curr_ratio;
    /**
     * 万份单位收益
     */
    private String per_myriad_income;

    /**
     * 行情日期
     */
    private String hq_date;
    /**
     * TA编号
     */
    private String ta_no;
    /**
     * 基金风险等级
     */
    private String ofund_risklevel;
    /**
     * 基金类型
     */
    private String ofund_type;

    /**
     * 基金全称
     */
    private String fund_full_name;
    /**
     * 禁止修改分红方式标志
     */
    private String forbid_modi_autobuy_flag;

    /**
     * 累计净值
     */
    private String nav_total;
    /**
     * 预期年化收益率
     */
    private String pre_income_ratio;
    /**
     * 产品期限
     */
    private String prod_term;

    /**
     * 是否集合类产品
     */
    private String is_collect_product;

    /**
     * 基金子类型
     */
    private String fund_sub_type;
    /**
     * 屏蔽基金代码(支持多个基金代码，以逗号分隔)
     */
    private String hidden_fund_code;
    /**
     * 屏蔽基金状态(支持多个基金状态，以逗号分隔)
     */
    private String hidden_fund_status;
    /**
     * 是否可申购
     */
    private String is_support_purchase;
    /**
     * 是否可定投
     */
    private String is_support_fixedinvest;
    /**
     * 最大申购金额
     */
    private String max_purchase_amount;
    /**
     * 最小申购金额
     */
    private String min_purchase_amount;
    /**
     * 最大定投金额
     */
    private String max_fixinvest_amount;
    /**
     * 最小定投金额
     */
    private String min_fixinvest_amount;
    /**
     * 基金管理人
     */
    private String fund_manager;
    /**
     * 是否可认购
     */
    private String is_support_subscribe;
    /**
     * 基金管理人代码
     */
    private String fund_manager_code;

    public FundMarket() {
    }

    public FundMarket(String accept_hq_date, String forbid_modi_autobuy_flag, String fund_code, String fund_curr_income, String fund_curr_ratio, String fund_full_name, String fund_manager, String fund_manager_code, String fund_name, String fund_share, String fund_status, String fund_sub_type, String hidden_fund_code, String hidden_fund_status, String hq_date, String is_collect_product, String is_support_fixedinvest, String is_support_purchase, String is_support_subscribe, String max_fixinvest_amount, String max_purchase_amount, String min_fixinvest_amount, String min_purchase_amount, String nav, String nav_total, String ofund_risklevel, String ofund_type, String per_myriad_income, String pre_income_ratio, String prod_term, String share_type, String ta_no) {
        this.accept_hq_date = accept_hq_date;
        this.forbid_modi_autobuy_flag = forbid_modi_autobuy_flag;
        this.fund_code = fund_code;
        this.fund_curr_income = fund_curr_income;
        this.fund_curr_ratio = fund_curr_ratio;
        this.fund_full_name = fund_full_name;
        this.fund_manager = fund_manager;
        this.fund_manager_code = fund_manager_code;
        this.fund_name = fund_name;
        this.fund_share = fund_share;
        this.fund_status = fund_status;
        this.fund_sub_type = fund_sub_type;
        this.hidden_fund_code = hidden_fund_code;
        this.hidden_fund_status = hidden_fund_status;
        this.hq_date = hq_date;
        this.is_collect_product = is_collect_product;
        this.is_support_fixedinvest = is_support_fixedinvest;
        this.is_support_purchase = is_support_purchase;
        this.is_support_subscribe = is_support_subscribe;
        this.max_fixinvest_amount = max_fixinvest_amount;
        this.max_purchase_amount = max_purchase_amount;
        this.min_fixinvest_amount = min_fixinvest_amount;
        this.min_purchase_amount = min_purchase_amount;
        this.nav = nav;
        this.nav_total = nav_total;
        this.ofund_risklevel = ofund_risklevel;
        this.ofund_type = ofund_type;
        this.per_myriad_income = per_myriad_income;
        this.pre_income_ratio = pre_income_ratio;
        this.prod_term = prod_term;
        this.share_type = share_type;
        this.ta_no = ta_no;
    }

    public String getFund_manager_code() {
        return fund_manager_code;
    }

    public void setFund_manager_code(String fund_manager_code) {
        this.fund_manager_code = fund_manager_code;
    }

    public String getFund_manager() {
        return fund_manager;
    }

    public void setFund_manager(String fund_manager) {
        this.fund_manager = fund_manager;
    }


    public String getMax_purchase_amount() {
        return max_purchase_amount;
    }

    public void setMax_purchase_amount(String max_purchase_amount) {
        this.max_purchase_amount = max_purchase_amount;
    }

    public String getMin_purchase_amount() {
        return min_purchase_amount;
    }

    public void setMin_purchase_amount(String min_purchase_amount) {
        this.min_purchase_amount = min_purchase_amount;
    }

    public String getMax_fixinvest_amount() {
        return max_fixinvest_amount;
    }

    public void setMax_fixinvest_amount(String max_fixinvest_amount) {
        this.max_fixinvest_amount = max_fixinvest_amount;
    }

    public String getMin_fixinvest_amount() {
        return min_fixinvest_amount;
    }

    public void setMin_fixinvest_amount(String min_fixinvest_amount) {
        this.min_fixinvest_amount = min_fixinvest_amount;
    }

    public String getHidden_fund_code() {
        return hidden_fund_code;
    }

    public void setHidden_fund_code(String hidden_fund_code) {
        this.hidden_fund_code = hidden_fund_code;
    }

    public String getHidden_fund_status() {
        return hidden_fund_status;
    }

    public void setHidden_fund_status(String hidden_fund_status) {
        this.hidden_fund_status = hidden_fund_status;
    }

    public String getIs_support_purchase() {
        return is_support_purchase;
    }

    public void setIs_support_purchase(String is_support_purchase) {
        this.is_support_purchase = is_support_purchase;
    }

    public String getIs_support_fixedinvest() {
        return is_support_fixedinvest;
    }

    public void setIs_support_fixedinvest(String is_support_fixedinvest) {
        this.is_support_fixedinvest = is_support_fixedinvest;
    }

    /**
     * @param accept_hq_date 设置接收行情日期
     */
    public void setAccept_hq_date(String accept_hq_date) {
        this.accept_hq_date = accept_hq_date;
    }

    /**
     * @return 获取接收行情日期
     */
    public String getAccept_hq_date() {
        return this.accept_hq_date;
    }

    /**
     * @param fund_code 设置基金代码
     */
    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    /**
     * @return 获取基金代码
     */
    public String getFund_code() {
        return this.fund_code;
    }

    /**
     * @param share_type 设置份额分类
     */
    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    /**
     * @return 获取份额分类
     */
    public String getShare_type() {
        return this.share_type;
    }

    /**
     * @param fund_name 设置基金名称
     */
    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    /**
     * @return 获取基金名称
     */
    public String getFund_name() {
        return this.fund_name;
    }

    /**
     * @param nav 设置T-1日基金单位净值
     */
    public void setNav(String nav) {
        this.nav = nav;
    }

    /**
     * @return 获取T-1日基金单位净值
     */
    public String getNav() {
        return this.nav;
    }

    /**
     * @param fund_share 设置基金总份额
     */
    public void setFund_share(String fund_share) {
        this.fund_share = fund_share;
    }

    /**
     * @return 获取基金总份额
     */
    public String getFund_share() {
        return this.fund_share;
    }

    /**
     * @param fund_status 设置基金状态
     */
    public void setFund_status(String fund_status) {
        this.fund_status = fund_status;
    }

    /**
     * @return 获取基金状态
     */
    public String getFund_status() {
        return this.fund_status;
    }

    /**
     * @param fund_curr_income 设置基金收益
     */
    public void setFund_curr_income(String fund_curr_income) {
        this.fund_curr_income = fund_curr_income;
    }

    /**
     * @return 获取基金收益
     */
    public String getFund_curr_income() {
        return this.fund_curr_income;
    }

    /**
     * @param fund_curr_ratio 设置基金收益率
     */
    public void setFund_curr_ratio(String fund_curr_ratio) {
        this.fund_curr_ratio = fund_curr_ratio;
    }

    /**
     * @return 获取基金收益率
     */
    public String getFund_curr_ratio() {
        return this.fund_curr_ratio;
    }

    /**
     * @param per_myriad_income 设置万份单位收益
     */
    public void setPer_myriad_income(String per_myriad_income) {
        this.per_myriad_income = per_myriad_income;
    }

    /**
     * @return 获取万份单位收益
     */
    public String getPer_myriad_income() {
        return this.per_myriad_income;
    }

    /**
     * @param hq_date 设置行情日期
     */
    public void setHq_date(String hq_date) {
        this.hq_date = hq_date;
    }

    /**
     * @return 获取行情日期
     */
    public String getHq_date() {
        return this.hq_date;
    }

    /**
     * @param ta_no 设置TA编号
     */
    public void setTa_no(String ta_no) {
        this.ta_no = ta_no;
    }

    /**
     * @return 获取TA编号
     */
    public String getTa_no() {
        return this.ta_no;
    }

    /**
     * @param ofund_risklevel 设置基金风险等级
     */
    public void setOfund_risklevel(String ofund_risklevel) {
        this.ofund_risklevel = ofund_risklevel;
    }

    /**
     * @return 获取基金风险等级
     */
    public String getOfund_risklevel() {
        return this.ofund_risklevel;
    }

    /**
     * @param ofund_type 设置基金类型
     */
    public void setOfund_type(String ofund_type) {
        this.ofund_type = ofund_type;
    }

    /**
     * @return 获取基金类型
     */
    public String getOfund_type() {
        return this.ofund_type;
    }

    /**
     * @param fund_full_name 设置基金全称
     */
    public void setFund_full_name(String fund_full_name) {
        this.fund_full_name = fund_full_name;
    }

    /**
     * @return 获取基金全称
     */
    public String getFund_full_name() {
        return this.fund_full_name;
    }

    /**
     * @param forbid_modi_autobuy_flag 设置禁止修改分红方式标志
     */
    public void setForbid_modi_autobuy_flag(String forbid_modi_autobuy_flag) {
        this.forbid_modi_autobuy_flag = forbid_modi_autobuy_flag;
    }

    /**
     * @return 获取禁止修改分红方式标志
     */
    public String getForbid_modi_autobuy_flag() {
        return this.forbid_modi_autobuy_flag;
    }

    /**
     * @param nav_total 设置累计净值
     */
    public void setNav_total(String nav_total) {
        this.nav_total = nav_total;
    }

    /**
     * @return 获取累计净值
     */
    public String getNav_total() {
        return this.nav_total;
    }

    /**
     * @param pre_income_ratio 设置预期年化收益率
     */
    public void setPre_income_ratio(String pre_income_ratio) {
        this.pre_income_ratio = pre_income_ratio;
    }

    /**
     * @return 获取预期年化收益率
     */
    public String getPre_income_ratio() {
        return this.pre_income_ratio;
    }

    /**
     * @param prod_term 设置产品期限
     */
    public void setProd_term(String prod_term) {
        this.prod_term = prod_term;
    }

    /**
     * @return 获取产品期限
     */
    public String getProd_term() {
        return this.prod_term;
    }

    public String getIs_collect_product() {
        return is_collect_product;
    }

    public void setIs_collect_product(String is_collect_product) {
        this.is_collect_product = is_collect_product;
    }

    public String getFund_sub_type() {
        return fund_sub_type;
    }

    public void setFund_sub_type(String fund_sub_type) {
        this.fund_sub_type = fund_sub_type;
    }


    public String getIs_support_subscribe() {
        return is_support_subscribe;
    }

    public void setIs_support_subscribe(String is_support_subscribe) {
        this.is_support_subscribe = is_support_subscribe;
    }

    @Override
    public String toString() {
        return "FundMarket [accept_hq_date=" + accept_hq_date + ", fund_code="
                + fund_code + ", share_type=" + share_type + ", fund_name="
                + fund_name + ", nav=" + nav + ", fund_share=" + fund_share
                + ", fund_status=" + fund_status + ", fund_curr_income="
                + fund_curr_income + ", fund_curr_ratio=" + fund_curr_ratio
                + ", per_myriad_income=" + per_myriad_income + ", hq_date="
                + hq_date + ", ta_no=" + ta_no + ", ofund_risklevel="
                + ofund_risklevel + ", ofund_type=" + ofund_type
                + ", fund_full_name=" + fund_full_name
                + ", forbid_modi_autobuy_flag=" + forbid_modi_autobuy_flag
                + ", nav_total=" + nav_total + ", pre_income_ratio="
                + pre_income_ratio + ", prod_term=" + prod_term
                + ", is_collect_product=" + is_collect_product
                + ", fund_sub_type=" + fund_sub_type + ", hidden_fund_code="
                + hidden_fund_code + ", hidden_fund_status="
                + hidden_fund_status + ", is_support_purchase="
                + is_support_purchase + ", is_support_fixedinvest="
                + is_support_fixedinvest + ", max_purchase_amount="
                + max_purchase_amount + ", min_purchase_amount="
                + min_purchase_amount + ", max_fixinvest_amount="
                + max_fixinvest_amount + ", min_fixinvest_amount="
                + min_fixinvest_amount + ", fund_manager=" + fund_manager
                + ", is_support_subscribe=" + is_support_subscribe
                + ", fund_manager_code=" + fund_manager_code + "]";
    }
}
