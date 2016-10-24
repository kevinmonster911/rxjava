package com.kevin.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * this test will finger out which is useful for
 */
public class ObservableDeferTest {

    public static class SomeType {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> sampleCase() {
            return Observable.defer(() -> Observable.just(value));
        }

        public Observable<String> moerSophisticatedCase(){
            return Observable.defer(() -> {
                SomeType someType = new SomeType();
                someType.setValue(value);

                try {
                    throw new IOException("io exception");
                } catch (IOException e) {
                    return Observable.error(e);
                }

            });
        }
    }


    public static void main(String[] args) throws InterruptedException {

        // first step
        SomeType instance = new SomeType();
        Observable<String> value = instance.sampleCase();
        instance.setValue("Some Value");
        value.subscribe(System.out::println);

        // more sophisticated case
        SomeType sophisticatedInstance = new SomeType();
        Observable<String> observable = sophisticatedInstance.moerSophisticatedCase();
        sophisticatedInstance.setValue("ggogogo");
        observable.subscribe(System.out::println, e -> System.out.println(e.getMessage()));


        Observable<Long> timer = Observable.timer(2000, TimeUnit.MILLISECONDS);
        timer.subscribe(System.out::println, e -> System.out.println(e.getMessage()), () -> System.out.println("completed!!"));

    }
}
