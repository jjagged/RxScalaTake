package com.github.jjagged;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class JavaTake {
    public static Observable<Integer> testObservable(final Integer i) {
        return Observable.just(i)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("Next " + i);
                    }
                })
                .finallyDo(new Action0() {
                    @Override
                    public void call() {
                        System.out.println(("Finally do " + i));
                    }
                });
    }

    public static void main(String[] args) {
        Integer result1 = testObservable(1).take(1).toBlocking().single();
        System.out.println("Result is " + result1);
        Integer result2 = testObservable(2).single().toBlocking().single();
        System.out.println("Result is " + result2);
    }
}
