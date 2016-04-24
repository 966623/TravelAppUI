package com.example.seanlin.unitravel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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

    public void setTripList(TripList tl){
        tripList = tl;
    }
    public TripList getTripList(){
        return tripList;
    }

    public void saveFile(){
        String filename = "save_file";
        FileOutputStream outputStream;
        ObjectOutputStream outputObj;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputObj = new ObjectOutputStream(outputStream);
            outputObj.writeObject(getTripList());
            outputObj.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
