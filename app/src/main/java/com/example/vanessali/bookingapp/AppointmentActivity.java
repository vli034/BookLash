package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.vanessali.bookingapp.CreateProfileActivity.SERVICE;

public class AppointmentActivity extends OptionsMenuActivity {


    public final static int PROFILE_PAGE = 4;
    private TextView dateView;
    private Button btnNewAppt;
    private TextView toolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        dateView = findViewById(R.id.date_appt);
        btnNewAppt = findViewById(R.id.btn_new_apt);
        // Customizing ToolBar
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //On Click Listeners
        btnNewAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ServiceActivity.class);
                startActivityForResult(intent, SERVICE);

            }
        });

        //methods
        getDate();
    }





    private void getDate(){
        // Retrieving Date data from Calendar View
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        //String test = intent.getStringExtra("classic");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //dateView.setText(date);
        //dateView.setText(String.format("%s%s", date, test));
        dateView.setText(date);


    }

}
