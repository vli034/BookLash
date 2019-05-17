package com.example.vanessali.bookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {
    private TextView toolBarTitle;
    private EditText nameEdt;
    private EditText emailEdt;
    private EditText phoneEdt;
    private TextView changePic;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_btn, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameEdt = findViewById(R.id.name_edt);
        emailEdt = findViewById(R.id.email_edt);
        phoneEdt = findViewById(R.id.phone_edt);
        changePic = findViewById(R.id.change_profile);


        // Customizing ToolBar
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Edit Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("EDIT PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
}
