package com.example.seanlin.unitravel;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by Sean Lin on 5/8/2016.
 */
public class BudgetSummary{


    public static void updateBar(ProgressBar p, Trip currentTrip){

        p.setMax((int) currentTrip.getBudget());
        p.setProgress((int)currentTrip.getSpent());
    }

    public static void changeBar(ProgressBar p, int i){


        p.setProgress(i);
    }

}
