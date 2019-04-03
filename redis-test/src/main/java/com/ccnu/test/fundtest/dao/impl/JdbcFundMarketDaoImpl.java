package com.ccnu.test.fundtest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ccnu.test.fundtest.dao.FundMarketDao;
import com.ccnu.test.fundtest.model.FundMarket;
import com.ccnu.test.fundtest.utils.JDBCUtil;

public class JdbcFundMarketDaoImpl implements FundMarketDao {

    public void saveList(List<FundMarket> fundMarkets) throws Exception {
        String sql = "INSERT INTO fundmarket(accept_hq_date,fund_code,share_type,fund_name,nav,fund_share,fund_status,fund_curr_income,fund_curr_ratio,per_myriad_income,hq_date,ta_no,ofund_risklevel,ofund_type,fund_full_name,forbid_modi_autobuy_flag,nav_total,pre_income_ratio,prod_term,is_collect_product,fund_sub_type,hidden_fund_code,hidden_fund_status,is_support_purchase,is_support_fixedinvest,max_purchase_amount,min_purchase_amount,max_fixinvest_amount,min_fixinvest_amount,fund_manager,is_support_subscribe,fund_manager_code) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = JDBCUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < fundMarkets.size(); i++) {
            pstmt.setString(1, fundMarkets.get(i).getAccept_hq_date());
            pstmt.setString(2, fundMarkets.get(i).getFund_code());
            pstmt.setString(3, fundMarkets.get(i).getShare_type());
            pstmt.setString(4, fundMarkets.get(i).getFund_name());
            pstmt.setString(5, fundMarkets.get(i).getNav());
            pstmt.setString(6, fundMarkets.get(i).getFund_share());
            pstmt.setString(7, fundMarkets.get(i).getFund_status());
            pstmt.setString(8, fundMarkets.get(i).getFund_curr_income());
            pstmt.setString(9, fundMarkets.get(i).getFund_curr_ratio());
            pstmt.setString(10, fundMarkets.get(i).getPer_myriad_income());
            pstmt.setString(11, fundMarkets.get(i).getHq_date());
            pstmt.setString(12, fundMarkets.get(i).getTa_no());
            pstmt.setString(13, fundMarkets.get(i).getOfund_risklevel());
            pstmt.setString(14, fundMarkets.get(i).getOfund_type());
            pstmt.setString(15, fundMarkets.get(i).getFund_full_name());
            pstmt.setString(16, fundMarkets.get(i)
                    .getForbid_modi_autobuy_flag());
            pstmt.setString(17, fundMarkets.get(i).getNav_total());
            pstmt.setString(18, fundMarkets.get(i).getPre_income_ratio());
            pstmt.setString(19, fundMarkets.get(i).getProd_term());
            pstmt.setString(20, fundMarkets.get(i).getIs_collect_product());
            pstmt.setString(21, fundMarkets.get(i).getFund_sub_type());
            pstmt.setString(22, fundMarkets.get(i).getHidden_fund_code());
            pstmt.setString(23, fundMarkets.get(i).getHidden_fund_status());
            pstmt.setString(24, fundMarkets.get(i).getIs_support_purchase());
            pstmt.setString(25, fundMarkets.get(i).getIs_support_fixedinvest());
            pstmt.setString(26, fundMarkets.get(i).getMax_purchase_amount());
            pstmt.setString(27, fundMarkets.get(i).getMin_purchase_amount());
            pstmt.setString(28, fundMarkets.get(i).getMax_fixinvest_amount());
            pstmt.setString(29, fundMarkets.get(i).getMin_fixinvest_amount());
            pstmt.setString(30, fundMarkets.get(i).getFund_manager());
            pstmt.setString(31, fundMarkets.get(i).getIs_support_subscribe());
            pstmt.setString(32, fundMarkets.get(i).getFund_manager_code());

            pstmt.addBatch();

            if ((i + 1) % 500 == 0) {// 当增加了500个批处理的时候再提交
                pstmt.executeBatch();// 执行批处理
                conn.commit();// 提交
                JDBCUtil.close(conn);
                conn = JDBCUtil.getConnection();// 重新获取一次连接
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(sql);
            }
        }
    }

    public List<FundMarket> loadAll() throws Exception {
        String sql = "select * from fundmarket";
        Connection conn = JDBCUtil.getConnection();
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<FundMarket> fundMarkets = new ArrayList<FundMarket>();
        while (resultSet.next()) {
            FundMarket fundMarket = new FundMarket();
            fundMarket.setAccept_hq_date(resultSet.getString("accept_hq_date"));
            fundMarket.setFund_code(resultSet.getString("fund_code"));
            fundMarket.setShare_type(resultSet.getString("share_type"));
            fundMarket.setFund_name(resultSet.getString("fund_name"));
            fundMarket.setNav(resultSet.getString("nav"));
            fundMarket.setFund_share(resultSet.getString("fund_share"));
            fundMarket.setFund_status(resultSet.getString("fund_status"));
            fundMarket.setFund_curr_income(resultSet.getString("fund_curr_income"));
            fundMarket.setFund_curr_ratio(resultSet.getString("fund_curr_ratio"));
            fundMarket.setPer_myriad_income(resultSet.getString("per_myriad_income"));
            fundMarket.setHq_date(resultSet.getString("hq_date"));
            fundMarket.setTa_no(resultSet.getString("ta_no"));
            fundMarket.setOfund_risklevel(resultSet.getString("ofund_risklevel"));
            fundMarket.setOfund_type(resultSet.getString("ofund_type"));
            fundMarket.setFund_full_name(resultSet.getString("fund_full_name"));
            fundMarket.setForbid_modi_autobuy_flag(resultSet.getString("forbid_modi_autobuy_flag"));
            fundMarket.setNav_total(resultSet.getString("nav_total"));
            fundMarket.setPre_income_ratio(resultSet.getString("pre_income_ratio"));
            fundMarket.setProd_term(resultSet.getString("prod_term"));
            fundMarket.setIs_collect_product(resultSet.getString("is_collect_product"));
            fundMarket.setFund_sub_type(resultSet.getString("fund_sub_type"));
            fundMarket.setHidden_fund_code(resultSet.getString("hidden_fund_code"));
            fundMarket.setHidden_fund_status(resultSet.getString("hidden_fund_status"));
            fundMarket.setIs_support_purchase(resultSet.getString("is_support_purchase"));
            fundMarket.setIs_support_fixedinvest(resultSet.getString("is_support_fixedinvest"));
            fundMarket.setMax_purchase_amount(resultSet.getString("max_purchase_amount"));
            fundMarket.setMin_purchase_amount(resultSet.getString("min_purchase_amount"));
            fundMarket.setMax_fixinvest_amount(resultSet.getString("max_fixinvest_amount"));
            fundMarket.setMin_fixinvest_amount(resultSet.getString("min_fixinvest_amount"));
            fundMarket.setFund_manager(resultSet.getString("fund_manager"));
            fundMarket.setIs_support_subscribe(resultSet.getString("is_support_subscribe"));
            fundMarket.setFund_manager_code(resultSet.getString("fund_manager_code"));

            fundMarkets.add(fundMarket);
        }
        return fundMarkets;
    }

    public List<FundMarket> query(String fund_status, String share_type, String min_fund_nav_total) throws Exception {
        String sql = "select * from fundmarket where 1=1 and fund_status= '" + fund_status + "' and share_type= '" + share_type + "' and nav_total>= '" + min_fund_nav_total + "' and nav_total<3";
        Connection conn = JDBCUtil.getConnection();
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<FundMarket> fundMarkets = new ArrayList<FundMarket>();
        while (resultSet.next()) {
            FundMarket fundMarket = new FundMarket();
            fundMarket.setAccept_hq_date(resultSet.getString("accept_hq_date"));
            fundMarket.setFund_code(resultSet.getString("fund_code"));
            fundMarket.setShare_type(resultSet.getString("share_type"));
            fundMarket.setFund_name(resultSet.getString("fund_name"));
            fundMarket.setNav(resultSet.getString("nav"));
            fundMarket.setFund_share(resultSet.getString("fund_share"));
            fundMarket.setFund_status(resultSet.getString("fund_status"));
            fundMarket.setFund_curr_income(resultSet.getString("fund_curr_income"));
            fundMarket.setFund_curr_ratio(resultSet.getString("fund_curr_ratio"));
            fundMarket.setPer_myriad_income(resultSet.getString("per_myriad_income"));
            fundMarket.setHq_date(resultSet.getString("hq_date"));
            fundMarket.setTa_no(resultSet.getString("ta_no"));
            fundMarket.setOfund_risklevel(resultSet.getString("ofund_risklevel"));
            fundMarket.setOfund_type(resultSet.getString("ofund_type"));
            fundMarket.setFund_full_name(resultSet.getString("fund_full_name"));
            fundMarket.setForbid_modi_autobuy_flag(resultSet.getString("forbid_modi_autobuy_flag"));
            fundMarket.setNav_total(resultSet.getString("nav_total"));
            fundMarket.setPre_income_ratio(resultSet.getString("pre_income_ratio"));
            fundMarket.setProd_term(resultSet.getString("prod_term"));
            fundMarket.setIs_collect_product(resultSet.getString("is_collect_product"));
            fundMarket.setFund_sub_type(resultSet.getString("fund_sub_type"));
            fundMarket.setHidden_fund_code(resultSet.getString("hidden_fund_code"));
            fundMarket.setHidden_fund_status(resultSet.getString("hidden_fund_status"));
            fundMarket.setIs_support_purchase(resultSet.getString("is_support_purchase"));
            fundMarket.setIs_support_fixedinvest(resultSet.getString("is_support_fixedinvest"));
            fundMarket.setMax_purchase_amount(resultSet.getString("max_purchase_amount"));
            fundMarket.setMin_purchase_amount(resultSet.getString("min_purchase_amount"));
            fundMarket.setMax_fixinvest_amount(resultSet.getString("max_fixinvest_amount"));
            fundMarket.setMin_fixinvest_amount(resultSet.getString("min_fixinvest_amount"));
            fundMarket.setFund_manager(resultSet.getString("fund_manager"));
            fundMarket.setIs_support_subscribe(resultSet.getString("is_support_subscribe"));
            fundMarket.setFund_manager_code(resultSet.getString("fund_manager_code"));

            fundMarkets.add(fundMarket);
        }
        return fundMarkets;
    }

    public void flushDB() throws Exception {
        Connection conn = JDBCUtil.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from fundmarket");
        JDBCUtil.close(conn);
    }

    public void query() throws Exception {
        String sql = "select * from test where 1=1 and name= ?";
        Connection conn = JDBCUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "a");
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("age"));
        }
    }

    public static void main(String[] args) throws Exception {
        new JdbcFundMarketDaoImpl().query();
    }
}
