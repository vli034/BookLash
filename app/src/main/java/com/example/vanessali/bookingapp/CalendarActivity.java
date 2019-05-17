package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import static com.example.vanessali.bookingapp.CreateProfileActivity.SERVICE;

public class CalendarActivity extends AppCompatActivity implements DialogBox.DialogBoxListener{
    public static final int APPOINTMENT = 6;
    private CalendarView calendarView;
    private Button btnOK;
    private Button btnCancel;
    private TextView myDate;
    private TextView toolBarTitle;
    private TextView timeOption1;
    private TextView timeOption2;
    private TextView timeOption3;
    private TextView timeOption4;
    private TextView timeOption5;
    private TextView timeOption6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // Customizing ToolBar title - no back button
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("CHOOSE A DATE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnOK = findViewById(R.id.btn_ok);
        btnCancel = findViewById(R.id.btn_cancel);
        calendarView = findViewById(R.id.calendar_view);
        myDate = findViewById(R.id.my_date);
        timeOption1 = findViewById(R.id.time1);
        timeOption2 = findViewById(R.id.time2);

        //On Click Listeners
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBackwards();

            }
        });


        // Setting calendar format
        btnOK.setEnabled(false);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month+1)+"/"+ dayOfMonth + "/" + year;
                myDate.setText(date);
                btnOK.setEnabled(true); // user must select date to enable button
            }
        });

        timeOption1.setOnClickListener(new CustomTouchListeners());



    }

    //Create an instance of your dialog box in the activity you want it to appear in
    public void openDialog(){
        DialogBox dialog = new DialogBox();
        dialog.show(getSupportFragmentManager(),"boop");
    }


    // creating and override method to allow us to direct where we want the dialog box to go when clicked yes
    public void onYesClicked(){
        Intent intent = new Intent(
                getApplicationContext(),AppointmentActivity.class);// when yes is clicked moved to next activity
        startActivityForResult(intent, APPOINTMENT);
        String value = myDate.getText().toString();// get value from textView
        intent.putExtra("date",value); //Pass that value to profile activity when Yes is clicked in dialog box
        startActivity(intent);

    }

    private void launchBackwards(){
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivityForResult(intent,SERVICE);
    }



}
