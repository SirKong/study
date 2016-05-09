package com.ccnu.test.fundtest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccnu.test.fundtest.dao.impl.JdbcFundMarketDaoImpl;
import com.ccnu.test.fundtest.dao.impl.RedisFundMarketDaoImpl;
import com.ccnu.test.fundtest.model.FundMarket;
import com.ccnu.test.fundtest.utils.StringUtil;

/**
 * Created by gongyb08837 on 2015/11/22.
 */
public class AppTest {

	private static final Log log = LogFactory.getLog(AppTest.class);

	public static void main(String[] args) throws Exception {
		List<FundMarket> fundMarkets = generateDate();
		//----------------------------------------------------------------------------
		long before = System.currentTimeMillis();
		new RedisFundMarketDaoImpl().flushDB();
		long after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("清空redis数据共耗时[%s]ms!", 	(after - before)));
		log.info("=======================================================");
		
		before = System.currentTimeMillis();
		new JdbcFundMarketDaoImpl().flushDB();
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("清空数据库数据共耗时[%s]ms!", 	(after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------
		
		before = System.currentTimeMillis();
		new JdbcFundMarketDaoImpl().saveList(fundMarkets);
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("插入[%s]条数据到数据库共耗时[%s]ms!", fundMarkets.size(),
				(after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------

		before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		new RedisFundMarketDaoImpl().saveList(fundMarkets);
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("插入[%s]条数据到redis共耗时[%s]ms!", fundMarkets.size(),
				(after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------
		
		before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		new RedisFundMarketDaoImpl().loadAll();
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("获取redis中全部数据共耗时[%s]ms!", (after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------
		
		before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		new JdbcFundMarketDaoImpl().loadAll();
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("获取数据库中全部数据共耗时[%s]ms!", (after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------
		
		before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		System.out.println(new RedisFundMarketDaoImpl().query("2", "A", "0.5").size());
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("查询redis中数据共耗时[%s]ms!", (after - before)));
		log.info("=======================================================");
		//----------------------------------------------------------------------------
		
		before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		System.out.println(new JdbcFundMarketDaoImpl().query("2", "A", "0.5").size());
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("查询数据库中数据共耗时[%s]ms!", (after - before)));
		log.info("=======================================================");
		
	}

	public static void flushDB() throws Exception{
		long before = System.currentTimeMillis();
		new RedisFundMarketDaoImpl().flushDB();
		long after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("清空redis数据共耗时[%s]ms!", 	(after - before)));
		log.info("=======================================================");
		
		before = System.currentTimeMillis();
		new JdbcFundMarketDaoImpl().flushDB();
		after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("清空数据库数据共耗时[%s]ms!", 	(after - before)));
		log.info("=======================================================");
	}
	
	/**
	 * 制造原始数据
	 * @return
	 */
	public static List<FundMarket> generateDate() {
		List<FundMarket> fundMarkets = new ArrayList<FundMarket>();
		Random random = new Random();
		DecimalFormat dcmFmt = new DecimalFormat("0.00");
		String[] accept_hq_dates = new String[] { "20151203", "20151204",
				"20151205", "20151206", "20151207" };
		String[] share_types = new String[] { "A", "B" };
		String[] tas = new String[] { "98", "25" };
		String[] ofund_risklevels = new String[] { "1", "2", "3" };
		String[] ofund_types = new String[] { "1", "2", "3" };
		String[] fund_statuss = new String[] { "1", "2", "3", "4", "5" };
		String[] fund_sub_types = new String[] { "1", "2", "3", "4", "5" };
		String[] fund_manager_codes = new String[] { "11111111", "22222222",
				"33333333" };

		for (int i = 0; i < 100000; i++) {
			FundMarket fundMarket = new FundMarket();
			fundMarket
					.setAccept_hq_date(accept_hq_dates[random.nextInt(i + 1) % 5]);
			fundMarket.setFund_code(StringUtil.flushLeft('0', 6,
					String.valueOf(i)));
			fundMarket.setShare_type(share_types[random.nextInt(i + 1) % 2]);
			fundMarket.setFund_name("基金" + fundMarket.getFund_code());
			fundMarket.setFund_status(fund_statuss[random.nextInt(i + 1) % 5]);
			fundMarket.setNav(dcmFmt.format(0.2 + Math.random()));
			fundMarket.setFund_share(dcmFmt.format(random.nextFloat()));
			fundMarket.setFund_curr_income(dcmFmt.format(random.nextFloat()));
			fundMarket.setFund_curr_ratio(dcmFmt.format(0.2 + Math.random()));
			fundMarket.setPer_myriad_income(dcmFmt.format(0.2 + Math.random()));
			fundMarket.setHq_date(fundMarket.getAccept_hq_date());
			fundMarket.setTa_no(tas[random.nextInt(i + 1) % 2]);
			fundMarket.setOfund_risklevel(ofund_risklevels[random
					.nextInt(i + 1) % 3]);
			fundMarket.setOfund_type(ofund_types[random.nextInt(i + 1) % 3]);
			fundMarket.setFund_full_name("基金全称" + fundMarket.getFund_code());
			fundMarket
					.setForbid_modi_autobuy_flag(random.nextInt(i + 1) % 2 == 0 ? "0"
							: "1");
			fundMarket.setNav_total(dcmFmt.format(0.5 + Math.random()));
			fundMarket.setPre_income_ratio(dcmFmt.format(Math.random() / 100));
			fundMarket
					.setIs_collect_product(random.nextInt(i + 1) % 2 == 0 ? "0"
							: "1");
			fundMarket
					.setFund_sub_type(fund_sub_types[random.nextInt(i + 1) % 5]);
			fundMarket
					.setIs_support_fixedinvest(random.nextInt(i + 1) % 2 == 0 ? "0"
							: "1");
			fundMarket
					.setIs_support_purchase(random.nextInt(i + 1) % 2 == 0 ? "0"
							: "1");
			fundMarket
					.setIs_support_subscribe(random.nextInt(i + 1) % 2 == 0 ? "0"
							: "1");
			fundMarket
					.setMax_fixinvest_amount(dcmFmt.format(Math.random() * 10000));
			fundMarket.setMin_fixinvest_amount("0.00");
			fundMarket
					.setMax_purchase_amount(dcmFmt.format(Math.random() * 10000));
			fundMarket.setMin_purchase_amount("0.00");
			fundMarket.setFund_manager_code(fund_manager_codes[random
					.nextInt(i + 1) % 3]);
			fundMarkets.add(fundMarket);
		}
		return fundMarkets;
	}

	/**
	 * 将原始数据写入到DB
	 * @param list
	 * @throws Exception
	 */
	public static void saveToDB(List<FundMarket> list) throws Exception {
		long before = System.currentTimeMillis();
		new JdbcFundMarketDaoImpl().saveList(list);
		long after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("插入[%s]条数据到数据库共耗时[%s]ms!", list.size(),
				(after - before)));
		log.info("=======================================================");
	}

	/**
	 * 将原始数据写入到Redis
	 * @param list
	 * @throws Exception 
	 */
	public static void saveToRedis(List<FundMarket> list) throws Exception {
		long before = System.currentTimeMillis();
		// 使用一定的反向索引来存储，为后续的查询做准备
		new RedisFundMarketDaoImpl().saveList(list);
		long after = System.currentTimeMillis();
		log.info("=======================================================");
		log.info(String.format("插入[%s]条数据到redis共耗时[%s]ms!", list.size(),
				(after - before)));
		log.info("=======================================================");
	}
}
