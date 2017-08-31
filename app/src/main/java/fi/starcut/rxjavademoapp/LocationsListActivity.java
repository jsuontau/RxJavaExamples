package fi.starcut.rxjavademoapp;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import fi.starcut.rxjavademoapp.api.WeatherAPI;
import fi.starcut.rxjavademoapp.data.DataProvider;
import fi.starcut.rxjavademoapp.models.Location;
import fi.starcut.rxjavademoapp.models.WeatherObservation;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LocationsListActivity extends AppCompatActivity {

    private LocationListAdapter mAdapter;

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_location_list_rv);
        mAdapter = new LocationListAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData() {

        //TODO: Implement Observalbe
        //1. Start with Observable.fromCallable

    }

    private void subscribe(Observable<Pair<Location, WeatherObservation>> observable) {
        mDisposable = observable.subscribeWith(new DisposableObserver<Pair<Location, WeatherObservation>>() {

            @Override
            public void onNext(Pair<Location, WeatherObservation> locationPair) {
                mAdapter.addLocation(locationPair);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(getClass().getSimpleName(), "Failed to featch location", e);
            }

            @Override
            public void onComplete() {
                //Sequence has completed
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable != null)
            mDisposable.dispose();
    }

    //region Adapter

    public static class LocationListAdapter extends RecyclerView.Adapter<LocationViewHolder> {

        private List<Pair<Location, WeatherObservation>> mLocations = new ArrayList<>();

        @Override
        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return LocationViewHolder.create(parent);
        }

        public void addLocation(Pair<Location, WeatherObservation> locationObservation) {
            mLocations.add(locationObservation);
            notifyItemInserted(mLocations.size() - 1);
        }

        @Override
        public void onBindViewHolder(LocationViewHolder holder, int position) {
            holder.bind(mLocations.get(position).first, mLocations.get(position).second);
        }

        @Override
        public int getItemCount() {
            return mLocations == null ? 0 : mLocations.size();
        }
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        public static LocationViewHolder create(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.vh_location, parent, false);
            return new LocationViewHolder(v);
        }

        private final TextView locationTv;

        private final TextView temperatureTv;

        public LocationViewHolder(View itemView) {
            super(itemView);
            this.locationTv = (TextView) itemView.findViewById(R.id.vh_location_tv);
            this.temperatureTv = (TextView) itemView.findViewById(R.id.vh_temperature_tv);
        }

        public void bind(Location location, WeatherObservation observation) {
            locationTv.setText(location.getName());
            temperatureTv.setText(observation.getTemperature() + " C");
        }
    }
    //endregion
}
