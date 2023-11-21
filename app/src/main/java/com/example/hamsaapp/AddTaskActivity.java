package com.example.hamsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsaapp.Data.AppDataBase;
import com.example.hamsaapp.Data.mySubjectsTable.Mysubject;
import com.example.hamsaapp.Data.mySubjectsTable.MysubjectQuery;
import com.example.hamsaapp.Data.mySubjectsTable.MysubjectQuery_Impl;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private Button btnsavetask;
    private Button btncanceltask;
    private SeekBar skbrlimportance;
    private TextInputEditText etshorttitle;
    private TextInputEditText ettext;
    private TextView txtimportance;
    private AutoCompleteTextView autoETsubj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnsavetask= findViewById(R.id.btnsavetask);
        btncanceltask= findViewById(R.id.btncanceltask);
        skbrlimportance= findViewById(R.id.skbrlimportance);
        etshorttitle= findViewById(R.id.etshorttitle);
        ettext= findViewById(R.id.ettext);
        txtimportance=findViewById(R.id.txtimportance);
        autoETsubj=findViewById(R.id.autoETsubj);
        initAutoETSubjects();//دالة لاستخراج القيم وعرضها بالحقل السابق


    }


    private void initAutoETSubjects()
    {
        //مؤشر لقاعدة البيانات
        AppDataBase db=AppDataBase.getDB(getApplicationContext());
        //مؤشر لواجهة استعمالات جدول المواضيع
        MysubjectQuery subjectquery=db.getMySubjectQuery();
        // مصدر المعطيات: استخراج جميع المواضيع من الجدول
        List<Mysubject> allsubjects=subjectquery.getAllsubjects();
        //تجهيز وسيط
        ArrayAdapter<Mysubject> adapter=new ArrayAdapter<Mysubject>(this, android.R.layout.simple_dropdown_item_1line);
        adapter.addAll(allsubjects); // اضافة جميع المعطيات للوسيط
        autoETsubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoETsubj.showDropDown();

            }
        });

    }


    public void checkAndSaveTask (View v)
    {
        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة


    }



    public void onClicksaveTask (View v)
    {
        checkShortTitleAndText();
        //to open new activity from current to next activity
        Intent i= new Intent(AddTaskActivity.this,   MainActivity.class);
        startActivity(i);
    }



    public void onClickcanceladdtask (View v)
    {
        finish();



    }



    private void checkShortTitleAndText()
    {

        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        String shortTitle=etshorttitle.getText().toString();
        String text=ettext.getText().toString();

        if (shortTitle.length()<1)
        {
            isAllOk=false;
            etshorttitle.setError("short title is empty");
        }

        if (text.length()<1)
        {
            isAllOk=false;
            ettext.setError("text is empty");
        }

        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
        }


    }
}
