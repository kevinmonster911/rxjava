package com.kevin.rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class JustObervable {

    public static void main(String[] args) {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        Observable observable = Observable.just("hello world!!");

        observable.subscribe(onNextAction);

        // lambda
        Observable.just("Hello, world!")
                .subscribe(s -> System.out.println(s));


        Observable.just("cai")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "+ zheng";
                    }
                })
                .subscribe(s -> System.out.println(s));

        // lambda
        Observable.just("cai")
                .map(s -> s + "zheng")
                .subscribe(s -> System.out.println(s));


    }
}
