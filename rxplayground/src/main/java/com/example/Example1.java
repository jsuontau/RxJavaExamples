package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by jsuontaus on 30/08/2017.
 * <p>
 *
 * Few basic Operations for creating and transforming Observables in RxJava
 *
 */
public class Example1 extends Example {

    @Override
    public void run() {

        //region Just

        print("--Just--");
        Observable<String> observable = Observable.just(someFunction());
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                print(s);
            }
        });

        //endregion

        //region FromCallable

        /*
        Java 7 Style
        Observable<String> observable = Observable.fromCallable(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return someFunction();
            }

        });
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                print(s);
            }
        });*/

        print("--fromCallable--");
        Observable.fromCallable(() -> someFunction());
        observable.subscribe((result) -> print(result));

        //endregion

        //region FromIterable

        /*
        Java 7 Style

        Observable.fromArray("1","2","3").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });
        */
        print("--fromIterable--");
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        Observable.fromIterable(strings).subscribe((s) -> {
            print(s);
        });

        //endregion

        //region FlatMap

        /*
        Java 7 Style
        Observable.just(strings).flatMap(new Function<List<String>,
                ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String o) throws Exception {
                int number = Integer.parseInt(o);
                number++;
                return number;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                print(integer + ".");
            }
        });*/
        print("--flatMap--");
        Observable.just(strings).flatMap(
                new Function<List<String>,
                        ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull List<String> strings)
                            throws Exception {
                        return Observable.fromIterable(strings);
                    }
                }).map((o) -> {
                    int number = Integer.parseInt(o);
                    number++;
                    return number;
                }
        ).subscribe((i) -> print(i + "."));
        //endregion
    }

    String someFunction() {
        return "some data";
    }

}
