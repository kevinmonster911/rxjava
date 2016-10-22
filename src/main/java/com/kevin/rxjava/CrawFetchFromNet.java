package com.kevin.rxjava;

import rx.Observable;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

/**
 * just for testing the fetch net using rxjava.programing
 * with flatMap (map a observable to another observable)
 * list to component
 *
 */
public class CrawFetchFromNet {

    public static Observable<List<String>> query(){
        return Observable.just(Arrays.asList("u1", "u2", "u3"));
    }

    public static void main(String[] args) {

        query().subscribe(urls -> {
            Observable.from(urls).subscribe(url -> System.out.println(url));
        });

        Observable.from(Arrays.asList((Number)1, 2, 3))
                .<Number>flatMap(new Func1<Object, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Object url) {
                        return Observable.from(Arrays.asList(1, 2, 3));
                    }
        }).subscribe(url -> System.out.println(url));

    }
}
