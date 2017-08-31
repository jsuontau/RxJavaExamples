package com.example;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jsuontaus on 30/08/2017.
 *
 * More complicated example.
 *
 */
public class Example3 extends Example {

    @Override
    public void run() {

        /*
        Java 7 Style

        Observable.zip(
                Observable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                }).subscribeOn(Schedulers.io()),
                Observable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                }).subscribeOn(Schedulers.io()),
                new BiFunction<String, String, Pair<String, String>>() {
                    @Override
                    public Pair<String, String> apply(@NonNull String r1,
                                                      @NonNull String r2)
                            throws Exception {
                        return new Pair<>(r1, r2);
                    }
                })
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<Pair<String, String>>() {
                    @Override
                    public void accept(@NonNull Pair<String, String> combinedResult) throws Exception {
                        printResult(combinedResult.fst, combinedResult.snd);
                    }
                });
        */

        Observable.zip(
                Observable.fromCallable(() -> networkCall1()).subscribeOn(Schedulers.io()),
                Observable.fromCallable(() -> networkCall2()).subscribeOn(Schedulers.io()),
                (s, s2) -> new Pair<>(s, s2))
                .observeOn(Schedulers.computation())
                .subscribe((result) -> printResult(result.fst, result.snd));
    }

    private String networkCall1() {
        print("networkCall1 started: " + System.currentTimeMillis()
                + " Tread: " + Thread.currentThread().getName());
        sleep(100);
        print("networkCall1 ready: " + System.currentTimeMillis());
        return "result1";
    }

    private String networkCall2() {
        print("networkCall2 started: "
                + System.currentTimeMillis()
                + " Tread: " + Thread.currentThread().getName());
        sleep(10);
        print("networkCall2 ready: " + System.currentTimeMillis());
        return "result2";
    }

    private void printResult(String result1, String result2) {
        print("Show result: " + System.currentTimeMillis() + " Tread: "
                + Thread.currentThread().getName());
        print("1: " + result1 + " 2: " + result2);
    }

    static class Pair<T, V> {
        final T fst;
        final V snd;

        public Pair(T fst, V snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }
}
