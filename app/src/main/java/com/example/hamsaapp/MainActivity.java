package com.example.hamsaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hamsaapp.Data.AppDataBase;
import com.example.hamsaapp.Data.mySubjectsTable.Mysubject;
import com.example.hamsaapp.Data.mySubjectsTable.MysubjectQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //spnr1 تعريف صفة للكائن المرئي
    private Spinner spnrsubject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //iuiyu8i7uii
        setContentView(R.layout.activity_main);
        // وضع مؤشر الصفة على الكائن المرئي الموجود بواجهة المستعمل spnr2
        spnrsubject = findViewById(R.id.spnrsubject);
        //بناء الوسيط وتحديد واجهة تنسيق لمعطى واحد spnr3
        ArrayAdapter<String> adapter =new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //مصدر معطيات (ممكن أن يكون قائمة من قاعدة بيانات مثلا) data source
        String[] ar={"Math","CS","Phs","Arb","Eng"};
        //تحديد المعطيات الوسيط spnr5
        adapter.addAll(ar);
        //ربط الكائن المرئي بالوسيط spnr6
        spnrsubject.setAdapter(adapter);

        Log.d("hamsa","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        // بناء قاعدة بيانات وارجاع مؤشر عليها1
        AppDataBase db= AppDataBase.getDB(getApplicationContext());
        // مؤشر لجدول2
        MysubjectQuery subjectQuery = db.getMySubjectQuery();
        // مثال لاستعمال جدول البيانات
        // بناء كائن من نوع الجدول وتحديد قيم الصفات3
        Mysubject s1=new Mysubject();
        s1.setTitle("Math");
        Mysubject s2=new Mysubject();
        s2.title="Computers"; // set ولكن بطريقة أخرى لأنها public

        //اضافة كائن للجدول4
        subjectQuery.insertsubject(s1);
        subjectQuery.insertsubject(s2);

        //5فحص هل تم حفظ ما سبق
        // استخراج وطباعة جميع معطيات الجدول المواضيع

        List<Mysubject> allsubjects = subjectQuery.getAllsubjects();
        for (Mysubject s: allsubjects) {
            Log.d("Hamsa",s.title);
            Toast.makeText(this, s.title, Toast.LENGTH_SHORT).show();

        }


    }
  
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("hamsa","onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hamsa","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hamsa","onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hamsa","onStart");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hamsa","onStart");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hamsa","onStart");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();

    }

}