package com.ccnu.test.fundtest.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.ccnu.test.fundtest.dao.FundMarketDao;
import com.ccnu.test.fundtest.model.FundMarket;
import com.ccnu.test.fundtest.utils.RedisUtil;

public class RedisFundMarketDaoImpl implements FundMarketDao {

    public void saveList(List<FundMarket> fundMarkets) throws Exception {
        for (int i = 0; i < fundMarkets.size(); i++) {
            FundMarket fundMarket = fundMarkets.get(i);
            // 直接使用<fundcode,Gson(fundMarket)>形式存储原始数据
            /**
             * Map<String,String> 的形式来存放"基金行情" ，hash的名字叫做 "基金行情"
             * <fundcode1,JSON(fund1)> <fundcode2,JSON(fund2)> ...
             * <fundcode*,JSON(fund*)>
             */
            RedisUtil.pipeline.hset("fund_markets", fundMarket.getFund_code(),
                    JSON.toJSONString(fundMarket));

            // 建立基金状态到基金代码的反向索引
            RedisUtil.pipeline.sadd("fund_status:" + fundMarket.getFund_status(),
                    fundMarket.getFund_code());
            // 建立TA代码到基金代码的反向索引
            RedisUtil.pipeline.sadd("fund_ta_no:" + fundMarket.getTa_no(),
                    fundMarket.getFund_code());
            // 建立基金星级到基金代码的反向索引
            RedisUtil.pipeline.sadd(
                    "ofund_risklevel:" + fundMarket.getOfund_risklevel(),
                    fundMarket.getFund_code());
            // 建立基金类型到基金代码的反向索引
            RedisUtil.pipeline.sadd("ofund_type:" + fundMarket.getOfund_type(),
                    fundMarket.getFund_code());
            // 建立基金子类型到基金代码的反向索引
            RedisUtil.pipeline.sadd("fund_sub_type:" + fundMarket.getFund_sub_type(),
                    fundMarket.getFund_code());
            // 建立基金收费方式到基金代码的反向索引
            RedisUtil.pipeline.sadd("fund_share_type:" + fundMarket.getShare_type(),
                    fundMarket.getFund_code());
            // 建立基金管理人到基金代码的反向索引
            RedisUtil.pipeline.sadd(
                    "fund_manager_code:" + fundMarket.getFund_manager_code(),
                    fundMarket.getFund_code());
            // 建立是否可申购到基金代码的反向索引
            RedisUtil.pipeline.sadd(
                    "fund_is_support_purchase:"
                            + fundMarket.getIs_support_purchase(),
                    fundMarket.getFund_code());
            // 建立是否可认购到基金代码的反向索引
            RedisUtil.pipeline.sadd(
                    "fund_is_support_subscribe:"
                            + fundMarket.getIs_support_subscribe(),
                    fundMarket.getFund_code());
            // 建立是否可定投到基金代码的反向索引
            RedisUtil.pipeline.sadd(
                    "fund_is_support_fixedinvest:"
                            + fundMarket.getIs_support_fixedinvest(),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("fund_nav_total",
                    Double.parseDouble(fundMarket.getNav_total()),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("fund_nav",
                    Double.parseDouble(fundMarket.getNav()),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("fund_curr_income",
                    Double.parseDouble(fundMarket.getFund_curr_income()),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("fund_curr_ratio",
                    Double.parseDouble(fundMarket.getFund_curr_ratio()),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("per_myriad_income",
                    Double.parseDouble(fundMarket.getPer_myriad_income()),
                    fundMarket.getFund_code());
            RedisUtil.pipeline.zadd("pre_income_ratio",
                    Double.parseDouble(fundMarket.getPre_income_ratio()),
                    fundMarket.getFund_code());

            if ((i + 1) % 500 == 0) {
                RedisUtil.pipeline.sync();
            }
        }
        RedisUtil.pipeline.sync();
    }

    public List<FundMarket> loadAll() throws Exception {
        Set<String> fundcodes = RedisUtil.jedis.sunion("fund_share_type:A", "fund_share_type:B");
        String[] funds = new String[fundcodes.size()];
        fundcodes.toArray(funds);
        List<String> list = RedisUtil.jedis.hmget("fund_markets", funds);
        List<FundMarket> fundMarkets = new ArrayList<FundMarket>();
        for (String str : list) {
            FundMarket market = JSON.parseObject(str, FundMarket.class);
            fundMarkets.add(market);
        }
        return fundMarkets;
    }

    public List<FundMarket> query(String fund_status, String share_type, String min_fund_nav_total) throws Exception {
        // 查询份额类型为A，基金状态为2，累计收益率>=0.036的数据
        // zrangebyscore key min max
        Set<String> navTotalSet = RedisUtil.jedis.zrangeByScore("fund_nav_total",
                Float.parseFloat(min_fund_nav_total), 3.00);

        // 交集的步骤放置到客户端
        Set<String> sinter = RedisUtil.jedis.sinter("fund_share_type:" + share_type, "fund_status:" + fund_status);
        navTotalSet.retainAll(sinter);

        Set<String> finalSet = navTotalSet;
        String[] fundcodes = new String[finalSet.size()];
        finalSet.toArray(fundcodes);
        List<String> markets = RedisUtil.jedis.hmget("fund_markets", fundcodes);
        List<FundMarket> fundMarkets = new ArrayList<FundMarket>();
        for (String str : markets) {
            FundMarket market = JSON.parseObject(str, FundMarket.class);
            fundMarkets.add(market);
        }
        return fundMarkets;
    }

    public void flushDB() throws Exception {
        RedisUtil.jedis.flushDB();
    }

}
