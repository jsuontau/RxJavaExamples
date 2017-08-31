package com.example;

import fi.starcut.rxjavademoapp.models.WeatherObservation;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jsuontaus on 30/08/2017.
 *
 * Basic asynchronous NetworkCall with RxJava
 */
public class Example2 extends Example {

    @Override
    public void run() {

        /*
        Java 7 Style:
        Observable<WeatherObservation> observable = Observable.fromCallable(
                new Callable<WeatherObservation>() {
                    @Override
                    public WeatherObservation call() throws Exception {
                        return getFromNetwork();
                    }
                }
        );

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<WeatherObservation>() {
                    @Override
                    public void accept(@NonNull WeatherObservation weatherObservation) throws Exception {
                        showResult(weatherObservation);
                    }
                });
        */
        Observable.fromCallable(() -> getFromNetwork())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe((weatherObservation) -> showResult(weatherObservation));

    }

    private WeatherObservation getFromNetwork() {

        System.out.println("getFromNetwork: " +
                Thread.currentThread().getName());

        return new WeatherObservation(10.2);
    }

    private void showResult(WeatherObservation observations) {

        System.out.println("showResult: " +
                Thread.currentThread().getName());

    }
}
