package fi.starcut.rxjavademoapp.data;

import java.util.ArrayList;
import java.util.List;

import fi.starcut.rxjavademoapp.models.Location;

/**
 * Created by jsuontaus on 30/08/2017.
 */

public class DataProvider {

    public static Location getMostVisitedLocation(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException ignore){}

        return new Location("Lahti", 12, 34);
    }

    public static List<Location> getFavoriteLocations(){

        //Simulate disk reading delay
        try {
            Thread.sleep(100);
        }catch (InterruptedException ignore){}

        List<Location> locations = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            locations.add(new Location("Location " + (i + 1), i, i));
        }

        return locations;
    }



}
