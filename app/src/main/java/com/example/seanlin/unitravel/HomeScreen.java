package com.example.seanlin.unitravel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class HomeScreen extends AppCompatActivity {

    Globals g = (Globals)getApplication();
    ListView tripList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        g = (Globals)getApplication();
        tripList = (ListView) findViewById(R.id.PlannedTripsList);

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("ONSTART", "Starting home");

        //Load save file
        try {
            FileInputStream fis = this.openFileInput("save_file");
            ObjectInputStream is = new ObjectInputStream(fis);
            TripList tl = (TripList) is.readObject();
            g.setTripList(tl);
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Populate list of trips
        if(g.getTripList().tripArrayList == null)
            return;

        if(g.getTripList().tripArrayList.size() <= 0)
            return;

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < g.getTripList().tripArrayList.size(); ++i) {
            list.add(g.getTripList().tripArrayList.get(i).getTripName());
            Log.d("ADD TO LIST", g.getTripList().tripArrayList.get(i).getTripName());
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        tripList.setAdapter(adapter);

        //Add listener to each trip that goes to summary.
        tripList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                g.setCurrentTrip(g.getTripList().tripArrayList.get(position));
                Intent i = new Intent(getApplicationContext(), SummaryPage.class);
                startActivity(i);
            }

        });
    }

    //Open new trip screen
    public void onButtonClick(View view){
        //do something when button is clicked.

        Intent i = new Intent(getApplicationContext(), NewTripScreen.class);
        startActivity(i);
    }



    //Delete save file, just for testing
    public void clearData(View view){
        File dir = getFilesDir();
        File file = new File(dir, "save_file");
        file.delete();
        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed(){
        return;
    }
}
