package com.ccnu.test.https;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by gongyb08837 on 2015/12/21.
 */
public class HttpsClientTest {
	public static void main(String[] args) throws Exception {
		test();

	}

	public static void test() throws Exception {
		HttpClient httpsClient = HttpsClient.newHttpsClient();
		//
		HttpUriRequest request = new HttpGet(
				"https://www.htbuy.com/restful/invest/list");
		//
		HttpResponse httpResponse = httpsClient.execute(request);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.print("请求成功");
		} else {
			System.out.print("请求失败");
		}
	}
}
