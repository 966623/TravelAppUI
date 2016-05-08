package com.example.seanlin.unitravel;


import android.net.Uri;
import android.text.style.TtsSpan;

import java.util.Date;

/**
 * Created by Alex on 4/20/2016.
 */
public class Expense implements java.io.Serializable{

    public enum EXPENSE_TPYE{NONE,TRAVEL,LODGING,FOOD}


    private String name;
    private Date start, end;
    private double cost;
    private EXPENSE_TPYE expenseType;
    private Uri fileUri;

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

    public void setName(String str){
        name = str;
    }

    public String getName(){
        return name;
    }

    public void setCost(double d){
        cost = d;
    }

    public double getCost(){
        return cost;
    }
    public void setUri(Uri u){
        fileUri = u;
    }
    public Uri getUri(){
        return fileUri;
    }
    public void setExpenseType(EXPENSE_TPYE e){
        expenseType = e;
    }
    @Override
    public String toString()
    {
        return this.name + " : " + this.cost;
    }
}
