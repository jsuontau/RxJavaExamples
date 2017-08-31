package fi.starcut.rxjavademoapp.models;

/**
 * Created by jsuontaus on 30/08/2017.
 */

public class WeatherObservation {

    private double temperature;

    public WeatherObservation(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
