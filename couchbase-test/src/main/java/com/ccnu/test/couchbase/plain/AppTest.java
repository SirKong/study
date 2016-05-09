package com.ccnu.test.couchbase.plain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

/**
 * Created by gongyb08837 on 2015/11/27.
 */
public class AppTest {
    private static final Log log = LogFactory.getLog(AppTest.class);

    public static void main(String[] args){

//        CouchbaseHelper.DEFAULT_BUCKET.bucketManager().flush(3, TimeUnit.MINUTES);
//        log.info("bucket data has bean flushed!");
//
        for (int i = 0; i < 100; i++) {
            String fundcode = "000000".substring(0, 6 - (i + "").length()) + i;
            FundMarket market = new FundMarket("20151122", "0", fundcode, "0.00", "0.000", "手工构造基金" + i, "基金管理人" + (i % 2), "管理人代码" + (i % 2), "基金名称" + i, "0.00", "" + (i % 2), "0", null, null, "20151121", null, null, null, null, null, 1200.0, null, 100.0, "1.0200", "0.0000", "0", "" + (i % 2), "0.0000", "0.0000", "0", (i % 2 == 0 ? "A" : "B"), "25");
            JsonObject jsonObject = JsonObject.fromJson(JSON.toJSONString(market));
            JsonDocument document = JsonDocument.create(fundcode,jsonObject);
            CouchbaseHelper.DEFAULT_BUCKET.upsert(document);
        }

        String statement = "SELECT * FROM default limit 1";
        N1qlQuery query = N1qlQuery.simple(statement);

        log.info("N1QL查询语句 ["+statement+"]");
        N1qlQueryResult result = CouchbaseHelper.DEFAULT_BUCKET.query(query);
        System.out.println("==========================================\n总记录数"+result.allRows().size());
        for (N1qlQueryRow row : result) {
            log.info("输出记录\n" + row.value());
        }
        CouchbaseHelper.disConnect();
    }

}
