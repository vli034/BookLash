package com.example.vanessali.bookingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.vanessali.bookingapp.R.layout.activity_calendar;

public class CustomTouchListeners extends AppCompatActivity implements View.OnClickListener{

    private TextView timeOption1;
    private TextView timeOption2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_calendar);

        timeOption1 = findViewById(R.id.time1);
        timeOption2 = findViewById(R.id.time2);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.time1:
               timeOption1.setTextColor(Color.parseColor("00CE39"));
                Toast.makeText(getApplicationContext(), "Button pressed ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.time2:
                timeOption2.setTextColor(Color.parseColor("00CE39"));
                break;

        }

    }

}
