package com.example.seanlin.unitravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void onButtonClick(View view){
        //do something when button is clicked.

        Intent i = new Intent(getApplicationContext(), NewTripScreen.class);
        startActivity(i);
    }


}
