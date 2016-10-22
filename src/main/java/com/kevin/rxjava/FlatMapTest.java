package com.kevin.rxjava;

import rx.Observable;

import java.util.Arrays;

/**
 * flat map test cases
 */
public class FlatMapTest {

    public static void main(String[] args) {

        Observable.just("u1_baidu", "u2_google", "u3_sohu", "u4_apple")
                .flatMap(urls -> Observable.from(Arrays.asList(urls)))
                .flatMap(url -> Observable.from(Arrays.asList(url.split("_")[1])))
                .filter(title -> "baidu".equals(title))
                .subscribe(title -> System.out.println(title));

    }
}
