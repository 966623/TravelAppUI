package com.example.seanlin.unitravel;

/**
 * Created by Sean Lin on 4/21/2016.
 */
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

public class DatePickerWindow extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private Fragment frag;
    private Bundle bundle;

    public DatePickerWindow(){

    }

    public DatePickerWindow(Bundle b){
        bundle = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Button button = (Button) getActivity().findViewById(bundle.getInt("id"));

        button.setText(Integer.toString(month+1) + "/" + Integer.toString(day) + "/" + Integer.toString(year));
    }
}
