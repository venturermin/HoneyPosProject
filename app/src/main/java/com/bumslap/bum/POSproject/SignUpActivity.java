package com.bumslap.bum.POSproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumslap.bum.DB.User;
import com.bumslap.bum.R;


public class SignUpActivity extends AppCompatActivity {
    EditText password;
    EditText passwordConfirm;
    TextView passwordMatching;
    ImageView check;
    SignInActivity signInActivity;
    EditText editText_name;
    EditText editText_store_name;
    EditText editText_email;
    EditText editText_password;
    RadioGroup radioGroup_sex;
    EditText editText_phonenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//set the default according to value


        Spinner yearSpinner = (Spinner)findViewById(R.id.year);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_year, R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Spinner monthSpinner = (Spinner)findViewById(R.id.month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_month,R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        Spinner daySpinner = (Spinner)findViewById(R.id.day);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_day, R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(dayAdapter);


        password = (EditText) findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);

        check = findViewById(R.id.check);
        check.setVisibility(View.INVISIBLE);


        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String PasswordCheck = password.getText().toString();
                String confirmCheck = passwordConfirm.getText().toString();

                if(PasswordCheck.equals(confirmCheck)){
                    check.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_green);
                }
                else {
                    check.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_red);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }// end of onCreate

    protected void onResume(){

        super.onResume();

        editText_name = findViewById(R.id.name);
        editText_store_name = findViewById(R.id.nameOfStore);
        editText_email = findViewById(R.id.email);
        editText_phonenumber = findViewById(R.id.phoneNumber);
        radioGroup_sex = findViewById(R.id.radio);
        editText_password = findViewById(R.id.password);

        signInActivity = new SignInActivity();


    }
    public void onClickedSend(View v){
        User user = new User();
        String name = editText_name.getText().toString();
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        user.setUser_Name(editText_name.getText().toString());
        user.setUser_StoreName(editText_store_name.getText().toString());
        user.setUser_Email(editText_email.getText().toString());
        user.setUser_PhoneNumber(editText_phonenumber.getText().toString());

        //클래스로 싸서 처리한다.



       // signInActivity.CreateUser(email,name);

       // finish();



    }




}



/*FFF1F1F1<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
<solid android:color="#FFFFFF"/>
<stroke
        android:bottom = "1dp"
                android:color="#FFDFDFDF"
                />

</shape>*/