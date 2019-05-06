package com.example.vanessali.bookingapp;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomTouchListeners implements View.OnClickListener{

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
