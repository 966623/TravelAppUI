package com.example.seanlin.unitravel;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SummaryPage extends AppCompatActivity {

    //for determining selected tab
    public enum TAB {
        SUMMARY, TRAVEL, LODGING, FOOD
    }

    private TAB selectedTab = TAB.SUMMARY;

    //buttons
    private Button btnSummary;
    private Button btnTravel;
    private Button btnLodging;
    private Button btnFood;
    private ListView expenseList;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        btnSummary = (Button) findViewById(R.id.btnSummary);
        btnTravel = (Button) findViewById(R.id.btnSummary);
        btnLodging = (Button) findViewById(R.id.btnSummary);
        btnFood = (Button) findViewById(R.id.btnSummary);
        expenseList = (ListView) findViewById(R.id.expenseList);

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        expenseList.setAdapter(adapter);
    }

    //buttons acting as a group of tabs. Not sure of the best way to do this, but is should work.
    public void onSummaryButtonClick(View view) {
        btnSummary.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));
        selectedTab = TAB.SUMMARY;
        clearExpensesList();
        populateTravelExpenses();
        populateLodgingExpenses();
        populateFoodExpenses();
        adapter.notifyDataSetChanged();
    }

    public void onTravelButtonClick(View view) {
        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));
        selectedTab = TAB.TRAVEL;
        clearExpensesList();
        populateTravelExpenses();
        adapter.notifyDataSetChanged();
    }

    public void onLodgingButtonClick(View view) {
        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));
        selectedTab = TAB.LODGING;
        clearExpensesList();
        populateLodgingExpenses();
        adapter.notifyDataSetChanged();
    }

    public void onFoodButtonClick(View view) {
        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#ffcc33"));
        selectedTab = TAB.FOOD;
        clearExpensesList();
        populateFoodExpenses();
        adapter.notifyDataSetChanged();
    }

    //save and return to the home screen
    public void onSaveButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(i);
    }

    public void onNewExpenseClick(View view) {
        Intent i;
        switch (selectedTab) {
            case SUMMARY:
                //not exactly sure what to do with this one...
                i = new Intent(getApplicationContext(), HomeScreen.class);
                break;
            case TRAVEL:
                i = new Intent(getApplicationContext(), TravelScreen.class);
                break;
            case LODGING:
                //change to lodging screen once created
                i = new Intent(getApplicationContext(), HomeScreen.class);
                break;
            case FOOD:
                //change to Food screen once created
                i = new Intent(getApplicationContext(), HomeScreen.class);
                break;
            default:
                i = new Intent(getApplicationContext(), HomeScreen.class);
                break;
        }
        startActivity(i);
    }

    public void clearExpensesList() {
        arrayList.clear();
    }

    public void populateTravelExpenses()
    {
        for (Expense e: trip.travelExpenses)
        {
            arrayList.add(e.toString());
        }
    }

    public void populateLodgingExpenses()
    {
        for (Expense e: trip.lodgingExpenses)
        {
            arrayList.add(e.toString());
        }
    }

    public void populateFoodExpenses()
    {
        for (Expense e: trip.foodExpenses)
        {
            arrayList.add(e.toString());
        }
    }

    public void assignTrip(Trip trip)
    {
        this.trip = trip;
    }
}
