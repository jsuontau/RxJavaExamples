package fi.starcut.rxjavademoapp.api;

import java.io.IOException;

import fi.starcut.rxjavademoapp.models.WeatherObservation;

/**
 * Created by jsuontaus on 30/08/2017.
 */

public class WeatherAPI {

    private static final double MIN_TEMP = -50;

    private static final double MAX_TEMP = 50;

    public static WeatherObservation getHottestObservation(){
        //Simulate Network delay
        try{
            Thread.sleep(500);
        }catch (InterruptedException ingonred){}

        return new WeatherObservation(MAX_TEMP);
    }

    public static WeatherObservation getWeatherObservation(double lat, double lon) throws IOException{

        //Simulate Network delay
        try{
            Thread.sleep(500);
        }catch (InterruptedException ingonred){}

        double temp = (Math.random() * (MAX_TEMP - MIN_TEMP)) + MIN_TEMP;

        return new WeatherObservation(temp);
    }

}
