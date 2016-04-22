package com.example.seanlin.unitravel;

import android.app.Application;

/**
 * Created by Sean Lin on 4/21/2016.
 */
public class Globals extends Application {

    private Trip currentTrip;
    private TripList tripList = new TripList();

    public void onCreate(){
        super.onCreate();
        tripList = new TripList();
    }

    public Trip getCurrentTrip(){
        return currentTrip;
    }

    public void setCurrentTrip(Trip t){
        currentTrip = t;
    }

    public TripList getTripList(){
        return tripList;
    }

}
