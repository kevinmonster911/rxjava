package com.kevin.rxjava;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class First4Know {

    public static void hello(String... names){

        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    public static Observable<String> createOnSubscribe(){

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello!!!");
                subscriber.onCompleted();
            }
        });

    }


    public static void main(String[] args) {
        hello("caizheng", "caizheng2");

        Subscriber<? super String> subscriber4CreateObservable = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        createOnSubscribe().subscribe(subscriber4CreateObservable);
    }
}
