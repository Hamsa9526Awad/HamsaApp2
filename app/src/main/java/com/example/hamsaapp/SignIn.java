package com.example.hamsaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamsaapp.Data.AppDataBase;
import com.example.hamsaapp.Data.myuser.MyUser;
import com.example.hamsaapp.Data.myuser.Myuserquery;
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
        checkEmailPass();



    }

    private void checkEmailPass()
    {
        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        // استخراج النص من حقل الايميل
        String email= etEmail.getText().toString();

        // استخراج نص كلمة المرور
        String pass=etPassword.getText().toString();

        // فحص الايميل ان كان طوله أقل من 6 أو لا يحتوي على @ فهو خاطئ
        if (email.length()<6 || email.contains("@")== false)
        {
            // تعديل المتغير ليدل على أن الفحص يعطي نتيجة خاطئة
            isAllOk=false;
            // عرض ملاحظة خطأ على الشاشة داخل حقل البريد
            etEmail.setError("wrong email");
        }
        if (pass.length()<8 || pass.contains(" ")== true)
        {
            isAllOk=false;
            etPassword.setError("Wrong password");
        }


        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();

            // بناء قاعدة بيانات وارجاع مؤشر عليها
            // ببنيها بس اول مرة حدا بسجل يعني أول مستخدم
            //  واذا كانت مبنية من قبل يعني مش أول واحد بستخدمها بس بحط عليها مؤشر
            AppDataBase db= AppDataBase.getDB(getApplicationContext());

            //مؤشر لكائن عمليات الجدول
            Myuserquery userQuery=db.getMyUserQuery();

            // استدعاء العملية التي تنفذ الاستعلام الذي يفحص البريد (الايميل) وكلمة المرور ويعيد كائن ان كان موجودا أو ان لم يكن موجود null
            MyUser myUser=userQuery.checkEmailPassw(email,pass);
            if (myUser==null)
            {
                Toast.makeText(this, "Wrong email or password", Toast.LENGTH_LONG).show();
            }
            else
            {
                //to open new activity from current to next activity
                Intent i= new Intent(SignIn.this,   MainActivity.class);
                startActivity(i);
                finish();

            }

        }
    }
}