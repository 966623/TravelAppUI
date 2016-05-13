package com.example.seanlin.unitravel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 4/20/2016.
 */
public class Trip implements java.io.Serializable{
    private String tripName, notes, schoolName;
    private Date start, end;
    private float budget, spent;
    public ArrayList<Expense> travelExpenses, lodgingExpenses, foodExpenses;

    public String getTripName(){
        return tripName;
    }
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

    public Trip(String tripName, String newschoolName, Date start, Date end, float newBudget, String notes ) {
        this.tripName = tripName;
        this.notes = notes;
        this.start = start;
        this.end = end;
        this.schoolName = newschoolName;
        this.budget = newBudget;
        this.spent = 0;
        //expenses still empty
        this.travelExpenses = new ArrayList<>();
        this.lodgingExpenses = new ArrayList<>();
        this.foodExpenses = new ArrayList<>();
    }

    public float getBudget(){
        return budget;
    }
    public float getSpent(){
        return spent;
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
        addCost(expense);
    }

    public void DeleteExpense(Expense e){
        removeCost(e);
        switch (e.GetExpenseType())
        {
            case TRAVEL:
                travelExpenses.remove(e);
                break;
            case LODGING:
                lodgingExpenses.remove(e);
                break;
            case FOOD:
                foodExpenses.remove(e);
                break;
        }
    }
    
        public void removeCost(Expense expense)
    {
        spent -= expense.getCost();
    }

    public void addCost(Expense expense)
    {
        spent += expense.getCost();
    }

}
