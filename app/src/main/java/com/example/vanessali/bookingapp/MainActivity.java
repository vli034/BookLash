package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {



    public static final int CREATE_ACC = 2;
    private Button loginBtn;
    private Button fbBtn;
    private Button googleBtn;
    private EditText emailEdt;
    private EditText passwordEdt;
    private TextView createAcc;
    private TextView forgotPass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    

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
        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth

        //on click listeners
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
                startActivityForResult(intent,4);

            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),ProfileActivity.class);
                startActivityForResult(intent,11);
//
            }
        });



    }




    //Firebase Sign-in Existing Users
    //Takes in an email address and password, validates them and the signs in them in.
    private void logIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // Sign in success, update UI with the signed-in user's information

                            Intent intent = new Intent(
                                    getApplicationContext(),ProfileActivity.class);
                            startActivityForResult(intent,7);


                            Toast.makeText(getApplicationContext(),"Welcome Back",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                        }

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
        // checking if it is empty or if it has proper email format
        if(emailInput.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            emailEdt.setError("Please enter valid email address");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            // we return here true or false to indicate whether the user has inserted a valid email
            // so if it does not match -- print error message
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
            Toast.makeText(this,"Incorrect Email or Password", Toast.LENGTH_LONG).show();

        }else{ // if user inputs everything correctly go to next activity
            logIn(emailEdt.getText().toString(),passwordEdt.getText().toString());
        }
    }



}
