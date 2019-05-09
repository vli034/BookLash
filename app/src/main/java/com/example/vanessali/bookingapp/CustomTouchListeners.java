package com.example.vanessali.bookingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.vanessali.bookingapp.R.layout.activity_calendar;

public class CustomTouchListeners extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_calendar);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.time1:
                ((TextView)v).setTextColor(Color.parseColor("00CE39"));break;
            case R.id.time2:
                ((TextView)v).setTextColor(Color.parseColor("00CE39"));break;

        }

    }

}
