package com.example.seanlin.unitravel;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
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

    private Button btnAddExpense;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> expenses;

    private ProgressBar budgetBar;
    private TextView budgetPercentage, budgetRemaining;

    private TextView tripNameText;

    Globals g;

    DecimalFormat df = new DecimalFormat("0.00##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);
        btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
        btnSummary = (Button) findViewById(R.id.btnSummary);
        btnTravel = (Button) findViewById(R.id.btnTravel);
        btnLodging = (Button) findViewById(R.id.btnLodging);
        btnFood = (Button) findViewById(R.id.btnFood);
        expenseList = (ListView) findViewById(R.id.expenseList);
        tripNameText = ((TextView) findViewById(R.id.tripNameText));
        budgetBar = (ProgressBar) findViewById(R.id.progressBar);
        budgetPercentage = (TextView) findViewById(R.id.budgetPercentage);
        budgetRemaining =  (TextView) findViewById(R.id.budgetRemaining);

        g = (Globals)getApplication();
        expenses = new ArrayList<>();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String s;
        btnAddExpense.setVisibility(View.INVISIBLE);
        BudgetSummary.updateBar(budgetBar, g.getCurrentTrip());
        tripNameText.setText(g.getCurrentTrip().getTripName());
        if(g.getCurrentTrip().getBudget() >= g.getCurrentTrip().getSpent()) {
            s = "Budget Usage: " + String.format(
                    "%.2f",
                    g.getCurrentTrip().getSpent() / g.getCurrentTrip().getBudget() * 100) + "%";
            budgetPercentage.setText(s);
            budgetPercentage.setTextColor(Color.BLACK);
            s = "Remaining Funds: $" + String.format(
                    "%.2f",
                    g.getCurrentTrip().getBudget() - g.getCurrentTrip().getSpent());
            budgetRemaining.setTextColor(Color.BLACK);
            BudgetSummary.changeBar(budgetBar,(int)g.getCurrentTrip().getSpent());
        }
        else
        {
            s = "Budget Usage: " + String.format(
                    "%.2f",
                    g.getCurrentTrip().getSpent() / g.getCurrentTrip().getBudget() * 100) + "%";
            budgetPercentage.setText(s);
            budgetPercentage.setTextColor(Color.RED);
            s = "Amount Over: $" + String.format(
                    "%.2f", g.getCurrentTrip().getBudget() - g.getCurrentTrip().getSpent());
            budgetRemaining.setTextColor(Color.RED);
            BudgetSummary.changeBar(budgetBar, (int)g.getCurrentTrip().getBudget());
        }
        budgetRemaining.setText(s);
        clearExpensesList();
        populateTravelExpenses();
        populateLodgingExpenses();
        populateFoodExpenses();

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, expenses);
        expenseList.setAdapter(adapter);

        expenseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                g.setCurrentExpense(g.getCurrentTrip().foodExpenses.get(position));
                Intent i = new Intent(getApplicationContext(), AddExpense.class);
                startActivity(i);
            }

        });



    }


    //buttons acting as a group of tabs. Not sure of the best way to do this, but is should work.
    public void onSummaryButtonClick(View view) {

        btnAddExpense.setVisibility(View.INVISIBLE);

        btnSummary.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));

        btnSummary.setTextColor(Color.BLACK);
        btnTravel.setTextColor(Color.WHITE);
        btnLodging.setTextColor(Color.WHITE);
        btnFood.setTextColor(Color.WHITE);

        selectedTab = TAB.SUMMARY;
        clearExpensesList();
        populateTravelExpenses();
        populateLodgingExpenses();
        populateFoodExpenses();
        // adapter.notifyDataSetChanged();
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, expenses);
        expenseList.setAdapter(adapter);
    }

    public void onTravelButtonClick(View view) {

        btnAddExpense.setVisibility(View.VISIBLE);

        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));

        btnSummary.setTextColor(Color.WHITE);
        btnTravel.setTextColor(Color.BLACK);
        btnLodging.setTextColor(Color.WHITE);
        btnFood.setTextColor(Color.WHITE);

        selectedTab = TAB.TRAVEL;
        clearExpensesList();
        populateTravelExpenses();
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, expenses);
        expenseList.setAdapter(adapter);
    }

    public void onLodgingButtonClick(View view) {
        btnAddExpense.setVisibility(View.VISIBLE);

        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#ffcc33"));
        btnFood.setBackgroundColor(Color.parseColor("#7a0019"));

        btnSummary.setTextColor(Color.WHITE);
        btnTravel.setTextColor(Color.WHITE);
        btnLodging.setTextColor(Color.BLACK);
        btnFood.setTextColor(Color.WHITE);

        selectedTab = TAB.LODGING;
        clearExpensesList();
        populateLodgingExpenses();

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, expenses);
        expenseList.setAdapter(adapter);
    }

    public void onFoodButtonClick(View view) {
        btnAddExpense.setVisibility(View.VISIBLE);

        btnSummary.setBackgroundColor(Color.parseColor("#7a0019"));
        btnTravel.setBackgroundColor(Color.parseColor("#7a0019"));
        btnLodging.setBackgroundColor(Color.parseColor("#7a0019"));
        btnFood.setBackgroundColor(Color.parseColor("#ffcc33"));

        btnSummary.setTextColor(Color.WHITE);
        btnTravel.setTextColor(Color.WHITE);
        btnLodging.setTextColor(Color.WHITE);
        btnFood.setTextColor(Color.BLACK);

        selectedTab = TAB.FOOD;

        clearExpensesList();
        populateFoodExpenses();

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, expenses);
        expenseList.setAdapter(adapter);
    }

    //save and return to the home screen
    public void onSaveButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(i);
    }

    public void onNewExpenseClick(View view) {
        g.setCurrentExpense(null);
        Intent i = new Intent(getApplicationContext(), AddExpense.class);
        startActivity(i);
    }

    public void clearExpensesList() {
        expenses.clear();
    }

    public void populateTravelExpenses() {
        for (int i = 0; i < g.getCurrentTrip().travelExpenses.size(); i++) {
            expenses.add(g.getCurrentTrip().travelExpenses.get(i).getName());
        }
    }

    public void populateLodgingExpenses()
    {
        for(int i = 0;i<g.getCurrentTrip().lodgingExpenses.size();++i){
            expenses.add(g.getCurrentTrip().lodgingExpenses.get(i).getName());
        }
    }

    public void populateFoodExpenses() {
        for (int i = 0; i < g.getCurrentTrip().foodExpenses.size(); i++) {
            expenses.add(g.getCurrentTrip().foodExpenses.get(i).getName());
        }
    }


    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(i);
    }
}
