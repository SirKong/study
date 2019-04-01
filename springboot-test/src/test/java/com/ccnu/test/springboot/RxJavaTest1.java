package com.ccnu.test.springboot;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.ccnu.test.springboot.constant.OpenAPIResult;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJavaTest1 {

    public static void main(String[] args) throws InterruptedException {


    }

    public static void test0() throws InterruptedException {
        Observable.range(0, 10).map(x -> (x + "\t")).forEach(System.out::print);

        System.out.println();

        Observable.interval(2, TimeUnit.SECONDS).map(x -> (x + "\t")).subscribe(System.out::print);

        System.out.println();

        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            observableEmitter.onNext(1);
            observableEmitter.onNext(2);
            observableEmitter.onComplete();
        }).map(x -> x + "\t").subscribe(System.out::print);

        Thread.sleep(15 * 1000);
    }

    public static void test1() throws InterruptedException {

        List<Integer> dataList = Lists.newArrayList();

        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Request.Builder builder = new Request.Builder()
                        .url("http://127.0.0.1:6001/?Action=ListByPage&Offset=0&Limit=10")
                        .get();
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, List<Integer>>() {
            @Override
            public List<Integer> apply(@NonNull Response response) throws Exception {

                System.out.println("map 线程:" + Thread.currentThread().getName() + "\n");
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();

                    if (body != null) {
                        OpenAPIResult openAPIResult = new Gson().fromJson(body.string(), OpenAPIResult.class);
                        if (OpenAPIResult.isSuccess(openAPIResult)) {
                            System.out.println("map:转换前:" + response.body());
                            return openAPIResult.getResultList();
                        }
                        return null;
                    }
                }
                return null;
            }
        }).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(@NonNull List<Integer> data) throws Exception {
                System.out.println("接受本次数据之前：" + dataList);
                dataList.addAll(data);
                System.out.println("接受本次数据之后：" + dataList);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                System.out.println("发生异常" + throwable);
            }
        });

        Thread.sleep(15 * 1000);

        System.out.println("全部完成之后：" + dataList);
    }

    public static void test2() {
        Observable<String> observable = Observable.<String>create(emitter -> {
            try {
                int pageNo = 1;

                while (pageNo < 5) {
                    int current = pageNo;

                    System.out.println("start read pageNo=" + pageNo);

                    IntStream.range(0, 10).mapToObj(i -> "sku" + String.valueOf((current - 1) * 10000 + i)).forEach(emitter::onNext);

                    Thread.sleep(10);

                    System.out.println("end read pageNo=" + pageNo);

                    ++pageNo;
                }
                emitter.onComplete();
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                emitter.onComplete();
            }
        });

        observable
                .blockingSubscribe(System.out::println);


        System.out.println("将读取放在单独的线程中执行:");
        observable
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(System.out::println);


        System.out.println("将读取放在单独的线程中, 并且分批执行:");
        observable
                .subscribeOn(Schedulers.io())
                .buffer(10) //分批处理
                .blockingSubscribe(System.out::println);
    }
}
