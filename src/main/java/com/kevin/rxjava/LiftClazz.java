package com.kevin.rxjava;

/**
 * Created by kevin on 16/10/20.
 */
public class LiftClazz {

    public  static <T, R> UpperLowerClazz<? extends T, ? super R> lift(UpperLowerClazz<? extends T, ? super R> ulc) {
        return ulc;
    }

    public static void main(String[] args) {

        new UpperLowerClazz<Long, Number>() {
            @Override
            public Number call(Long t) {
                return null;
            }
        };

        UpperLowerClazz<? extends Number, ? super Long> sss =lift(new UpperLowerClazz<Long, Number>() {
            @Override
            public Number call(Long t) {
                return null;
            }
        });
    }
}
