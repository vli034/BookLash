package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProfileActivity extends AppCompatActivity {
    public static final int PROFILE_PAGE = 3;
    private EditText fnameInput;
    private EditText lnameInput;
    private EditText numberInput;
    private EditText emailInput;
    private EditText password_input;
    private Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

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


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity();
            }
        });

        nextBtn.setEnabled(false);

    }




    private void launchNextActivity(){
        Intent intent = new Intent(
                getApplicationContext(),ProfileActivity.class);
        startActivityForResult(intent,PROFILE_PAGE);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String firstNameInput =fnameInput.getText().toString().trim();
            String lastNameInput = lnameInput.getText().toString().trim();
            String emailAddressInput = emailInput.getText().toString().trim();
            String phoneNumberInput = numberInput.getText().toString().trim();
            String passInput = password_input.getText().toString().trim();

            if(!firstNameInput.isEmpty()
                    && !lastNameInput.isEmpty()
                    && !emailAddressInput.isEmpty()
                    && !phoneNumberInput.isEmpty()
                    && !passInput.isEmpty()){
                nextBtn.setEnabled(true);
            } else{
                nextBtn.setEnabled(false);
            }
        }
    };



}
