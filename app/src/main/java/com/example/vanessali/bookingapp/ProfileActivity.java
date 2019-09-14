package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextDirectionHeuristic;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends OptionsMenuActivity {
    public static final int EDIT_PROFILE = 11;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView displayEmail;
    private TextView toolBarTitle;
    private ImageButton editProfileBtn;
    private TextView displayAppt;
    private TextView displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayEmail = findViewById(R.id.email_display);
        displayAppt = findViewById(R.id.currentAppt);
        editProfileBtn = findViewById(R.id.edit_btn1);
        displayName = findViewById(R.id.tv_name);

        //FireBase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        displayEmail.setText(firebaseUser.getEmail());// setting email in edt info box


        // Customizing ToolBar
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //onClickListeners
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivityForResult(intent, EDIT_PROFILE);

            }
        });

        readData();

    }


    private void readData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUser= user.getUid();//getting unique user id

        db.collection("users")
                .whereEqualTo("uId",currentUser)// TODO: important to note that UID i retrieving the unique id of the current user
                // in order to get db information from logged in users it needs to identify the unique id they're associated with
                // then firebse will be able to retrieve document snap shot information
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                displayAppt.setText((CharSequence) document.get("Appointment"));
                                displayName.setText((CharSequence) document.get("firstName"));

                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"No such document",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}