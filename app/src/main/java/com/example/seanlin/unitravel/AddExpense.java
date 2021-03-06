package com.example.seanlin.unitravel;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddExpense extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    private ImageView receiptImage;
    private Button receiptButton;
    private Expense expense;
    private EditText expenseName;
    private EditText cost;
    private Button datePicker;
    private ProgressBar budgetBar;
    private TextView usedText;
    private TextView totalText;
    private ImageButton deleteButton;
    private Globals g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g = (Globals)getApplication();

        setContentView(R.layout.activity_add_expense);


        deleteButton = (ImageButton) findViewById(R.id.delete_button);
        if(g.getCurrentExpense() == null)
            deleteButton.setVisibility(View.INVISIBLE);
        usedText = (TextView) findViewById(R.id.budget_spent_text);
        totalText = (TextView) findViewById(R.id.budget_total_text);
        usedText.setText(String.format("%.2f", (g.getCurrentTrip().getSpent())));
        totalText.setText(String.format("%.2f", (g.getCurrentTrip().getBudget())));

        budgetBar = (ProgressBar) findViewById(R.id.budget_summary_bar);
        BudgetSummary.updateBar(budgetBar, ((Globals) getApplication()).getCurrentTrip());

        receiptImage = (ImageView) findViewById(R.id.receipt_image);
        receiptButton = (Button) findViewById(R.id.add_receipt_button);
        expenseName = (EditText) findViewById(R.id.expense_name);
        expenseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        cost = (EditText) findViewById(R.id.cost_text);
        cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(g.getCurrentExpense() == null) {
                    if (!cost.getText().toString().equals("")) {
                        BudgetSummary.changeBar(budgetBar,
                                (int) ((Globals) getApplication()).getCurrentTrip().getSpent() + (int) Double.parseDouble(cost.getText().toString()));

                        usedText.setText(String.format("%.2f", (g.getCurrentTrip().getSpent() + Double.parseDouble(cost.getText().toString()))));
                    } else {
                        BudgetSummary.changeBar(budgetBar, (int) ((Globals) getApplication()).getCurrentTrip().getSpent());
                        usedText.setText(String.format("%.2f", (g.getCurrentTrip().getSpent())));
                    }
                }
                else
                {
                    if (!cost.getText().toString().equals("")) {
                        BudgetSummary.changeBar(budgetBar,
                                (int) ((Globals) getApplication()).getCurrentTrip().getSpent() -
                                        (int) g.getCurrentExpense().getCost() +
                                        (int) Double.parseDouble(cost.getText().toString()));

                        usedText.setText(String.format("%.2f", (g.getCurrentTrip().getSpent() -
                                g.getCurrentExpense().getCost() +
                                Double.parseDouble(cost.getText().toString()))));
                    } else {
                        BudgetSummary.changeBar(budgetBar, (int) ((Globals) getApplication()).getCurrentTrip().getSpent());
                        usedText.setText(String.format("%.2f", (g.getCurrentTrip().getSpent())));
                    }
                }
            }
        });
        datePicker = ((Button) findViewById(R.id.date_picker));
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, day);
        String stringDate = new java.text.SimpleDateFormat("MM/dd/yyyy")
                .format(newDate.getTime());
        datePicker.setText(stringDate);

        expense = new Expense();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(g.getCurrentExpense() != null)
        {
            try {
                expenseName.setText(g.getCurrentExpense().getName());
                cost.setText(String.format("%.2f", g.getCurrentExpense().getCost()));
                String stringDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(
                        g.getCurrentExpense().getDate().getTime());

                datePicker.setText(stringDate);

                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), g.getCurrentExpense().getUri());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                int nh = (int) ( bitmap.getHeight() * (1280.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1280, nh, true);
                //receiptImage.setImageURI(fileUri);
                receiptImage.setImageBitmap(scaled);
                receiptButton.setText("");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void openCamera(View v){
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                //Toast.makeText(this, "Image saved to:\n" +
                //       fileUri.toString(), Toast.LENGTH_LONG).show();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                int nh = (int) ( bitmap.getHeight() * (1280.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1280, nh, true);
                //receiptImage.setImageURI(fileUri);
                receiptImage.setImageBitmap(scaled);
                receiptButton.setText("");
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    public void saveExpense(View v){
        if(expenseName.getText().toString().equals("")) {
            Toast.makeText(this, "You must enter a name.", Toast.LENGTH_LONG).show();
            return;
        }
        if(datePicker.getText().toString().length() <= 0) {
            Toast.makeText(this, "You must select a date.", Toast.LENGTH_LONG).show();
            return;
        }
        if(cost.getText().toString().equals("")){
            Toast.makeText(this, "You must enter a cost.", Toast.LENGTH_LONG).show();
            return;
        }
        if(Double.parseDouble(cost.getText().toString()) == 0.0){
            Toast.makeText(this, "The cost must be more than $0.", Toast.LENGTH_LONG).show();
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date newDate = new Date();
        try {
            newDate = formatter.parse(datePicker.getText().toString());
        } catch (ParseException e) {              // Insert this block.
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (g.getCurrentExpense() != null)
        {
            g.getCurrentTrip().removeCost(g.getCurrentExpense());
            g.getCurrentExpense().setCost(Double.parseDouble(cost.getText().toString()));
            g.getCurrentTrip().addCost(g.getCurrentExpense());
            g.getCurrentExpense().setName(expenseName.getText().toString());
            g.getCurrentExpense().setDate(newDate);
            g.getCurrentExpense().setUri(fileUri);
            g.saveFile();
            Intent i = new Intent(getApplicationContext(), SummaryPage.class);
            startActivity(i);
        }
        else
        {
            expense = new Expense(expenseName.getText().toString(), newDate,
                    Double.parseDouble(cost.getText().toString()), fileUri, Expense.EXPENSE_TPYE.FOOD);

            if (((Globals) getApplication()).getCurrentTrip().getSpent() + (float) Double.parseDouble(cost.getText().toString()) >
                    ((Globals) getApplication()).getCurrentTrip().getBudget()) {
                android.support.v4.app.DialogFragment overBudget = new OverBudgetDialogue((Globals) getApplication(), getApplicationContext(), expense);
                overBudget.show(getSupportFragmentManager(), "overbudget");
            }
            else{
                g.getCurrentTrip().AddExpense(expense);
                g.saveFile();
                Intent i = new Intent(getApplicationContext(), SummaryPage.class);
                startActivity(i);
            }

        }

    }

    public void setDate(View view)
    {
        hideKeyboard(view);
        //Create a Bundle to pass in the ID of the button we want to modify the text of
        Bundle idBundle = new Bundle();
        idBundle.putInt("id", datePicker.getId());

        //Create a new DialogFragment and pass in the bundle
        DialogFragment newFragment = new DatePickerWindow(idBundle);

        //Show the date picker
        newFragment.show(getFragmentManager(), "Date");

    }

    public void deleteExpense(View view){
        android.support.v4.app.DialogFragment deleteD = new DeleteExpenseDialogue((Globals) getApplication(), getApplicationContext());
        deleteD.show(getSupportFragmentManager(), "deleteexpense");
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
