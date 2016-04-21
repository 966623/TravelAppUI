package com.example.seanlin.unitravel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 4/20/2016.
 */
public class Trip {
    private String tripName, notes;
    private Date start, end;
    public ArrayList<Expense> travelExpenses, lodgingExpenses, foodExpenses;


    //constructor with everything empty
    public Trip() {
        this.tripName = "";
        this.notes = "";
        this.start = new Date();
        this.end = new Date();
        this.travelExpenses = new ArrayList<>();
        this.lodgingExpenses = new ArrayList<>();
        this.foodExpenses = new ArrayList<>();
    }

    public Trip(String tripName, String notes, Date start, Date end) {
        this.tripName = tripName;
        this.notes = notes;
        this.start = start;
        this.end = end;
        //expenses still empty
        this.travelExpenses = new ArrayList<>();
        this.lodgingExpenses = new ArrayList<>();
        this.foodExpenses = new ArrayList<>();
    }

    public void AddExpense(Expense expense) {
        switch (expense.GetExpenseType())
        {
            case TRAVEL:
                travelExpenses.add(expense);
                break;
            case LODGING:
                lodgingExpenses.add(expense);
                break;
            case FOOD:
                foodExpenses.add(expense);
                break;
        }
    }

}
