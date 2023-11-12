package com.example.hamsaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Signupactivity extends AppCompatActivity {

    private TextInputEditText ETemail;
    private TextInputEditText ETpass;
    private TextInputEditText ETrepass;
    private TextInputEditText ETname;
    private TextInputEditText ETphone;
    private Button btnsave;
    private Button btncancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        ETemail= findViewById(R.id.ETemail);
        ETpass=findViewById(R.id.ETpass);
        ETrepass= findViewById(R.id.ETrepass);
        ETname= findViewById(R.id.ETname);
        ETphone=findViewById(R.id.ETphone);
        btnsave=findViewById(R.id.btnsave);
        btncancel=findViewById(R.id.btncancel);
    }



    public void onClicksavesignup(View v) //نقل إلى شاشة أخرى
    {
        //to open new activity from current to next activity
        Intent i= new Intent(Signupactivity.this,   SignIn.class);
        startActivity(i);





    }


    public void onClickcancelsignup(View v)
    {



    }



    private void checkData()
    {
        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        // استخراج النص من حقل الايميل
        String email= ETemail.getText().toString();

        // استخراج نص كلمة المرور
        String pass=ETpass.getText().toString();

        // استخراج تكرار كلمة المرور
        String repass=ETrepass.getText().toString();

        // استخراج الاسم
        String name=ETname.getText().toString();

        //استخراج رقم الهاتف
        String number=ETphone.getText().toString();




        // فحص الايميل ان كان طوله أقل من 6 أو لا يحتوي على @ فهو خاطئ
        if (email.length()<6 || email.contains("@")== false)
        {
            // تعديل المتغير ليدل على أن الفحص يعطي نتيجة خاطئة
            isAllOk=false;
            // عرض ملاحظة خطأ على الشاشة داخل حقل البريد
            ETemail.setError("wrong email");
        }

        if (pass.length()<8 || pass.contains("")== true)
        {
            isAllOk=false;
            ETpass.setError("Wrong password");
        }



        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
        }
    }
}