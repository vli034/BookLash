package com.example.vanessali.bookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button fbBtn;
    private Button googleBtn;
    private EditText emailEdt;
    private EditText passwordEdt;
    private TextView createAcc;
    private TextView forgotPass;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.btn_login);
        fbBtn = findViewById(R.id.btn_fb);
        googleBtn = findViewById(R.id.btn_google);
        emailEdt = findViewById(R.id.edtEmail);
        passwordEdt = findViewById(R.id.edtPass);
        createAcc = findViewById(R.id.text_create_account);
        forgotPass = findViewById(R.id.text_forgot_pass);


        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });

    }

    private void launchNextActivity(){


    }




}
