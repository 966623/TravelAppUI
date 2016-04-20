package com.example.seanlin.unitravel;


import android.text.style.TtsSpan;

import java.util.Date;

/**
 * Created by Alex on 4/20/2016.
 */
public class Expense {

    public enum EXPENSE_TPYE{NONE,TRAVEL,LODGING,FOOD}


    private String name;
    private Date start, end;
    private double cost;
    private EXPENSE_TPYE expenseType;


    public Expense()
    {
        this.name = "";
        this.start = new Date();
        this.end = new Date();
        this.cost = 0.00;
        this.expenseType = EXPENSE_TPYE.NONE;
    }

    public Expense(String name, Date start, Date end, double cost, EXPENSE_TPYE expenseType)
    {
        this.name = name;
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.expenseType = expenseType;
    }
    public EXPENSE_TPYE GetExpenseType()
    {
        return this.expenseType;
    }

    @Override
    public String toString()
    {
        return this.name + " : " + this.cost;
    }
}
