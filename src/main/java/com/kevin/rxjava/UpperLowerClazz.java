package com.kevin.rxjava;

/**
 * Created by kevin on 16/10/20.
 */
public interface UpperLowerClazz<T, R> {
    R call(T t);
}
