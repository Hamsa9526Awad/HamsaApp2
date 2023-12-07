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
import com.example.hamsaapp.Data.mytasktable.Mytask;
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

    /**
     * استخراج أسماء المواضيع من جدول المواضيع وعرضه بالحقل من نوع AutoCopleteTextView
     * طريقة التعامل معه شبيهة بالسبنر
     */

    private void initAutoETSubjects()
    {
        //مؤشر لقاعدة البيانات
        AppDataBase db=AppDataBase.getDB(getApplicationContext());
        //مؤشر لواجهة استعمالات جدول المواضيع
        MysubjectQuery subjectquery=db.getMySubjectQuery();
        // مصدر المعطيات: استخراج جميع المواضيع من الجدول
        List<Mysubject> allsubjects=subjectquery.getAllsubjects();
        //تجهيز وسيط
        // الوسيط هو الذي يربط بين ال
        ArrayAdapter<Mysubject> adapter=new ArrayAdapter<Mysubject>(this, android.R.layout.simple_dropdown_item_1line);
        adapter.addAll(allsubjects); // اضافة جميع المعطيات للوسيط
        autoETsubj.setAdapter(adapter);
        autoETsubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoETsubj.showDropDown();
            }
        });

    }


    public void onClicksaveTask (View v)
    {
        checkShortTitleAndText();

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
        String whichsubj=autoETsubj.getText().toString();

        int importancee=skbrlimportance.getProgress();

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
        if (whichsubj.length()<1)
        {
            isAllOk=false;
            autoETsubj.setError("you didn't chose the subject");

        }

        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            AppDataBase db=AppDataBase.getDB(getApplicationContext());
            MysubjectQuery subjectQuery=db.getMySubjectQuery();

            if (subjectQuery.checkSubject(whichsubj)==null) // فحص هل الموضوع من قبل بالجدول
            {
                //بناء موضوع جديد واضافته
                Mysubject subject=new Mysubject();
                subject.title=whichsubj;
                subjectQuery.insertsubject(subject);
            }
            //استخراج id الموضوع لأننا بحاجة لرقمه التسلسلي

            Mysubject subject= subjectQuery.checkSubject(whichsubj);

            Mytask task=new Mytask();
            task.importance=importancee;
            task.text=text;
            task.shortTitle=shortTitle;
            task.subid=subject.getKeyid();//تحديد رقم الموضوع للمهة
            db.getMyTaskQuery().insertTask(task);//اضافة المهمة للجدول
            finish();

        }


    }
}
