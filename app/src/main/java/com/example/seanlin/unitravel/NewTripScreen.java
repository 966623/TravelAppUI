package com.example.seanlin.unitravel;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.EditText;
import android.widget.AutoCompleteTextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.ArrayAdapter;

public class NewTripScreen extends AppCompatActivity {

    EditText budgetText;
    EditText tripNameText;
    AutoCompleteTextView schoolNameText;
    EditText dateStartText;
    EditText DateEndText;

    String[] schools={"University of Minnesota","University of Wisconsin","Yale","Stanford","Princeton","University of Michigan"};

    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_screen);


        schoolNameText = ((AutoCompleteTextView) findViewById((R.id.SchoolNameAuto)));
        ArrayAdapter adapt = new ArrayAdapter(this,android.R.layout.simple_list_item_1,schools);
        schoolNameText.setAdapter(adapt);
        schoolNameText.setThreshold(1);

        //Budget text changer
        budgetText = ((EditText) findViewById(R.id.NewBudgetNumber));
        budgetText.addTextChangedListener(
            new TextWatcher() {
                public void afterTextChanged(Editable s) {   //Convert the Text to String
                    String oldText = budgetText.getText().toString();
                    String newText = budgetText.getText().toString();
                    if(oldText.length() <= 0)
                        return;
                    if(oldText.charAt(0) == '0'){
                        if(oldText.length() <=1 )
                            newText = "";
                        else
                            newText = oldText.substring(1);

                    }
                    if(!newText.equals(oldText)) {
                        if(newText.length() <= 0)
                            budgetText.setText("");
                        else
                            budgetText.setText(newText);
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // TODO Auto-generated method stub
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub
            }
        });

        tripNameText = ((EditText) findViewById(R.id.NewTripName));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onNewTripButtonClick(View view) {
        //do something when button is clicked.
        boolean schoolEntered = false;
        for(int n = 0; n < schools.length; n++){
            if(schoolNameText.getText().toString().equals(schools[n]))
                schoolEntered = true;

        }
        if(!schoolEntered)
            return;

        if(budgetText.getText().toString().length() <= 0)
            return;

        if(tripNameText.getText().toString().length() <= 0)
            return;
        Intent i = new Intent(getApplicationContext(), SummaryPage.class);
        startActivity(i);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewTripScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.seanlin.unitravel/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewTripScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.seanlin.unitravel/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
