package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {
    public static final int SERVICE = 3;
    private EditText fnameInput;
    private EditText lnameInput;
    private EditText numberInput;
    private EditText emailInput;
    private EditText password_input;
    private Button nextBtn;
    private FirebaseAuth mAuth;
    private TextView toolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        // Customizing ToolBar title - no back button
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("CREATE YOUR PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        fnameInput = findViewById(R.id.et_firstname);
        lnameInput = findViewById(R.id.et_lastname);
        numberInput = findViewById(R.id.et_phone);
        emailInput = findViewById(R.id.et_email);
        password_input = findViewById(R.id.et_makePass);
        nextBtn = findViewById(R.id.btn_next);

        fnameInput.addTextChangedListener(loginTextWatcher);
        lnameInput.addTextChangedListener(loginTextWatcher);
        numberInput.addTextChangedListener(loginTextWatcher);
        emailInput.addTextChangedListener(loginTextWatcher);
        password_input.addTextChangedListener(loginTextWatcher);
        //Initialize Fire Base
        mAuth = FirebaseAuth.getInstance();


        //OnClick Listener
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount( emailInput.getText().toString(),password_input.getText().toString());


            }
        });
        //Button set to disabled
        nextBtn.setEnabled(false);


    }



    // Method for disabling button
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            String firstNameInput =fnameInput.getText().toString().trim();
            String lastNameInput = lnameInput.getText().toString().trim();
            String emailAddressInput = emailInput.getText().toString().trim();
            String phoneNumberInput = numberInput.getText().toString().trim();
            String passInput = password_input.getText().toString().trim();

            if(!emailAddressInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAddressInput).matches()
                    && !passInput.isEmpty() && passInput.length()>=6
                    && !firstNameInput.isEmpty()
                    &&!lastNameInput.isEmpty()
                    &&!phoneNumberInput.isEmpty()){
                nextBtn.setEnabled(true);
            } else{

                nextBtn.setEnabled(false);
            }

        }
    };

    //Firebase- Create an account
    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    //authenticating new user via email/password
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if authentication is successful register the new user information
                            //to the database
                            String firstNameInput =fnameInput.getText().toString().trim();
                            String lastNameInput = lnameInput.getText().toString().trim();
                            String emailAddressInput = emailInput.getText().toString().trim();
                            String phoneNumberInput = numberInput.getText().toString().trim();
                            String passInput = password_input.getText().toString().trim();

                            // Document fields that will be placed in collections
                            Map<String, String> newUser = new HashMap<>(); //
                            newUser.put("First Name", firstNameInput);
                            newUser.put("Last Name", lastNameInput);
                            newUser.put("Email", emailAddressInput);
                            newUser.put("Password", phoneNumberInput);
                            newUser.put("Phone", passInput);

                            //Create database collection called users
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("users")
                                    .add(newUser) //add the newUser information from hashMap
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            //When successfully registered launch next activity
                                            Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                                            launchNextActivity();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(),"This email address already exists with an account", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void launchNextActivity(){
        Intent intent = new Intent(
                getApplicationContext(),ServiceActivity.class);
        startActivityForResult(intent,SERVICE);
    }
}
