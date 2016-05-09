package com.example.seanlin.unitravel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
/**
 * Created by Sean Lin on 5/8/2016.
 */
public class OverBudgetDialogue extends DialogFragment {

    private Globals g;
    private Expense expense;
    private Context context;
    public OverBudgetDialogue(Globals newg, Context newContext, Expense newExpense){
        g = newg;
        context = newContext;
        expense = newExpense;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Adding this expense will exceed your budget. Are you sure you want to add it?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        g.getCurrentTrip().AddExpense(expense);
                        g.saveFile();
                        Intent i = new Intent(context, SummaryPage.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
