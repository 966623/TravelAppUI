package com.example.seanlin.unitravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTripScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_screen);
    }
    public void onNewTripButtonClick(View view){
        //do something when button is clicked.
        Intent i = new Intent(getApplicationContext(), SummaryPage.class);
        startActivity(i);
    }
}
