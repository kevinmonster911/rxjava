package com.kevin.rxjava;

import rx.Observable;

import java.util.Arrays;

/**
 * Created by kevin on 16/10/24.
 */
public class ObservableBufferTest {

    public static void main(String[] args) {
        Observable.from(Arrays.asList("a", "b", "c"))
                .map(str -> str + "_map")
                .filter(str -> str.equals("b_map"))
                .buffer(1)
                .subscribe(list -> {
                    list.forEach(System.out::println);
                });
    }
}
