package com.example.vanessali.bookingapp;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class HomePage extends Application { //extends the main application
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); // create an instance of firebaseauth
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();//retrieve user


        // Checked status of user - so if it has not signed out then we are sending the user to
        // a certain activity
        if(firebaseUser !=null){
            Intent i = new Intent(this,ProfileActivity.class);//ServiceActivity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
    }



}



