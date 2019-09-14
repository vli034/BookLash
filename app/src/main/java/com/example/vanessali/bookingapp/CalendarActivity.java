package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.*;
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
        btnOK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
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


        timeOption1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClick2(timeOption1);
            }
        });

        //TODO create a method do that when a text  view is selected it knows to turn to different color

    }

    //Create an instance of your dialog box in the activity you want it to appear in
    public void openDialog(){
        DialogBox dialog = new DialogBox();
        dialog.show(getSupportFragmentManager(),"boop");
    }


    // creating and override method to allow us to direct where we want the dialog box to go when clicked yes
    public void onYesClicked(){
        FirebaseUser testUser = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = testUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final String value = myDate.getText().toString();
        // Document fields that will be placed in collections
        Map<String, String> newAppt = new HashMap<>(); //
        newAppt.put("Appointment", value);

        db.collection("users").document(userUid)
                .set(newAppt, SetOptions.merge()) //TODO set options merge allows you to add additional fields to db
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(
                                getApplicationContext(),AppointmentActivity.class);// when yes is clicked moved to next activity
                        startActivityForResult(intent, APPOINTMENT);
                        intent.putExtra("date",value); //Pass that value to profile activity when Yes is clicked in dialog box
                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void launchBackwards(){
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivityForResult(intent,SERVICE);
    }

    private void test(){

        timeOption1.setTextColor(Color.parseColor("red"));
    }

    private void onClick2(View v){
        switch(v.getId()){
            case R.id.time1:
                timeOption1.setTextColor(Color.parseColor("red"));
                break;
            case R.id.time2:
                timeOption2.setTextColor(Color.parseColor("red"));
                break;

        }
    }





}
