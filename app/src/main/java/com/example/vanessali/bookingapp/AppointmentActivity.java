package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppointmentActivity extends AppCompatActivity {
    public final static int SERVICE_OPTIONS = 4;
    private TextView dateView;
    private Button btnNewAppt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        dateView = findViewById(R.id.date_appt);
        btnNewAppt = findViewById(R.id.btn_new_apt);


        //On Click Listeners
        btnNewAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ServiceActivity.class);
                startActivityForResult(intent, SERVICE_OPTIONS);

            }
        });


        // Retrieving Date data from Calendar View
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        dateView.setText(date);
    }
}
