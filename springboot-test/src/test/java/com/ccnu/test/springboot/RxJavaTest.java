package com.ccnu.test.springboot;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccnu.test.springboot.constant.OpenAPIResult;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJavaTest {

    private static final Logger logger = LoggerFactory.getLogger(RxJavaTest.class);

    public static void main(String[] args) {
        Observable<List<Integer>> listObservable = Observable.range(0, Integer.MAX_VALUE).map(RxJavaTest::queryByPage).takeWhile(list -> !list.isEmpty());

    }

    private static List<Integer> queryByPage(Integer page) throws IOException {
        Integer limit = 10;
        Integer offset = limit * page;
        Request.Builder builder = new Request.Builder()
                .url("http://127.0.0.1:6001/?Action=ListByPage&Offset=" + offset + "&Limit=" + limit)
                .get();
        Request request = builder.build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            if (body != null) {
                OpenAPIResult openAPIResult = new Gson().fromJson(body.string(), OpenAPIResult.class);
                if (OpenAPIResult.isSuccess(openAPIResult)) {
                    logger.info("本次查询{}:{}:{}:", offset, limit, openAPIResult.getResultList());
                    return openAPIResult.getResultList();
                }
                return Lists.newArrayList();
            }
        }
        return Lists.newArrayList();
    }
}
