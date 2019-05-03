package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int CREATE_ACC = 2;
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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });


        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity();

            }
        });

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),ServiceActivity.class);
                startActivityForResult(intent, 4);

            }
        });

    }

    private void launchNextActivity(){
        Intent intent = new Intent(
                getApplicationContext(),CreateProfileActivity.class);
        startActivityForResult(intent, CREATE_ACC);


    }

    private boolean emailValidation(){

        String emailInput =emailEdt.getEditableText().toString().trim();

        if(emailInput.isEmpty()){
            emailEdt.setError("Field cannot be empty");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            // we return here true or false to indicate whether the user has inserted a valid email
            // so if it does not match print error message
            emailEdt.setError("Please enter a valid email address");
            return false;
        } else {
            emailEdt.setError(null);
            return true;
        }


    }

    private boolean passwordValidation(){
        String passInput = passwordEdt.getEditableText().toString().trim();

        if(passInput.isEmpty()){
            passwordEdt.setError("Field cannot be empty");
            return false;
        }else {
            passwordEdt.setError(null);
            return true;
        }


    }

    public void inputValidation(){
        if(!emailValidation()| !passwordValidation()){
            Toast.makeText(this,"error checking", Toast.LENGTH_LONG).show();
        }else{ // if user inputs everything correctly go to next activity
            launchNextActivity();
        }
    }






}
