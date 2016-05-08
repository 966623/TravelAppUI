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
    private Date date;
    private double cost;
    private EXPENSE_TPYE expenseType;
    private Uri fileUri;

    public Expense()
    {
        this.name = "";
        this.date = new Date();
        this.cost = 0.00;
        this.expenseType = EXPENSE_TPYE.NONE;
    }

    public Expense(String name, Date newdate, double cost, Uri uri, EXPENSE_TPYE expenseType)
    {
        this.name = name;
        this.date = newdate;
        this.cost = cost;
        this.fileUri = uri;
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

    public Date getDate(){
        return date;
    }
    @Override
    public String toString()
    {
        return this.name + " : " + this.cost;
    }
}
