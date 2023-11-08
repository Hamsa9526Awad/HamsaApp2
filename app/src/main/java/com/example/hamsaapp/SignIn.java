package com.example.hamsaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class SignIn extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button btnsignup;
    private Button btnsignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in); // بناء واجهة المستعمل- كل الكائنات الموجودة على الواجه

        etEmail= findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        btnsignin= findViewById(R.id.btnsignin);
        btnsignup= findViewById(R.id.btnsignup);


    }


    /**
     * دالة معالجة حدث الضغط على كائن بواجهة المستعمل.
     */

    public void onClicksignup (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(SignIn.this,   Signupactivity.class);
        startActivity(i);





    }

    public void onClicksignin (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(SignIn.this,   MainActivity.class);
        startActivity(i);


    }


}