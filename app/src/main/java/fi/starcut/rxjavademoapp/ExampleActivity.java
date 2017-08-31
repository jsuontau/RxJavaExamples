package fi.starcut.rxjavademoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.Callable;

import fi.starcut.rxjavademoapp.api.WeatherAPI;
import fi.starcut.rxjavademoapp.data.DataProvider;
import fi.starcut.rxjavademoapp.models.Location;
import fi.starcut.rxjavademoapp.models.WeatherObservation;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jsuontaus on 31/08/2017.
 */

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        loaMostVisitedLocation();
        loadHottestWeatherObservation();
    }

    private void loaMostVisitedLocation() {
        Observable<Location> observable = Observable.fromCallable(
                new Callable<Location>() {

                    @Override
                    public Location call() throws Exception {
                        return DataProvider.getMostVisitedLocation();
                    }

                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        mDisposable.add(observable.subscribeWith(new DisposableObserver<Location>() {
            @Override
            public void onNext(Location location) {
                //TODO: Bind to UI
            }

            @Override
            public void onError(Throwable e) {
                //TODO: Handle error
            }

            @Override
            public void onComplete() {
                //Called when sequence is completed
            }

        }));

    }

    private void loadHottestWeatherObservation() {

        Observable<WeatherObservation> observable = Observable.fromCallable(
                new Callable<WeatherObservation>() {
                    @Override
                    public WeatherObservation call() throws Exception {
                        return WeatherAPI.getHottestObservation();
                    }
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        mDisposable.add(observable.subscribeWith(new DisposableObserver<WeatherObservation>(){
            @Override
            public void onNext(WeatherObservation weatherObservation) {
                //TODO: Bind to UI
            }

            @Override
            public void onError(Throwable e) {
                //TODO: Handle error
            }

            @Override
            public void onComplete() {
                //Called when sequence is completed
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
