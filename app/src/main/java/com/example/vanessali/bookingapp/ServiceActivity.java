package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends OptionsMenuActivity {

    private Button classicFull;
    private TextView toolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        // Customizing ToolBar title - no back button
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("CHOOSE A SERVICE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        classicFull = findViewById(R.id.btn_classic);



        classicFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),CalendarActivity.class);
                startActivityForResult(intent, 5);
            }
        });
    }
}
