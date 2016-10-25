package com.kevin.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 16/10/24.
 */
public class ObservableBufferTest {

    private static TestScheduler scheduler;
    private static Scheduler.Worker innerScheduler;

    static {
        scheduler = new TestScheduler();
        innerScheduler = scheduler.createWorker();
    }

    public static void main(String[] args) throws InterruptedException {
        Observable.from(Arrays.asList("a", "b", "c"))
                .map(str -> str + "_map")
                .filter(str -> str.equals("b_map"))
                .buffer(1)
                .subscribe(list -> {
                    list.forEach(System.out::println);
                });


        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> observer) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            observer.onNext(1);
                            Thread.sleep(100);
                            observer.onNext(2);
                            Thread.sleep(100);
                            observer.onNext(3);
                            Thread.sleep(100);
                            observer.onNext(4);
                            Thread.sleep(100);
                            observer.onNext(5);
                            Thread.sleep(100);
                            observer.onNext(6);
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        }).buffer(new Func0<Observable<?>>() {
            @Override
            public Observable<?> call() {

                return Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(final Subscriber<? super Object> observer2) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    observer2.onNext("s");
                                    Thread.sleep(100);
                                    observer2.onNext("s");
                                    Thread.sleep(400);
                                    observer2.onNext("s");
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });

            }
        }).subscribe(System.out::println);
    }

    public static <T> void push(final Observer<T> observer, final T value, int delay) {
        innerScheduler.schedule(new Action0() {
            @Override
            public void call() {
                observer.onNext(value);
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public static void complete(final Observer<?> observer, int delay) {
        innerScheduler.schedule(new Action0() {
            @Override
            public void call() {
                observer.onCompleted();
            }
        }, delay, TimeUnit.MILLISECONDS);
    }
}
