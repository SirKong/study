package com.ccnu.test.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class Main {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                print("Observable emit 1" + "\n");
                e.onNext(1);
                print("Observable emit 2" + "\n");
                e.onNext(2);
                print("Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                print("Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                print("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                print("onComplete" + "\n");
            }
        });
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
