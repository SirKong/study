package com.ccnu.test.fundtest.dao;

import java.util.List;

import com.ccnu.test.fundtest.model.FundMarket;

public interface FundMarketDao {
    void saveList(List<FundMarket> fundMarkets) throws Exception;

    List<FundMarket> loadAll() throws Exception;


    List<FundMarket> query(String fund_status, String share_type, String min_fund_nav_total) throws Exception;

    void flushDB() throws Exception;
}

