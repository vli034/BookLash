package com.example.vanessali.bookingapp;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends OptionsMenuActivity {
    public static final int EDIT_PROFILE = 11;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView displayEmail;
    private TextView toolBarTitle;
    private ImageButton editProfileBtn;
    private TextView displayAppt;

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // we pass an item and then we can check which items were clicked
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(this, ServiceActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.item1:
                Toast.makeText(this, " item click worked",Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.item2:
                signOut();

                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayEmail = findViewById(R.id.email_display);
        displayAppt = findViewById(R.id.current_app);
        editProfileBtn = findViewById(R.id.edit_btn1);
        //FireBase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        displayEmail.setText(firebaseUser.getEmail());

        // Customizing ToolBar
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Retrieving Date data from Calendar View
        Intent intent = getIntent();
        String date = intent.getStringExtra("hi");
        displayAppt.setText(date);


        //onClickListeners
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivityForResult(intent, EDIT_PROFILE);

            }
        });



    }

}
