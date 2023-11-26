package com.example.hamsaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hamsaapp.Data.AppDataBase;
import com.example.hamsaapp.Data.mySubjectsTable.Mysubject;
import com.example.hamsaapp.Data.mySubjectsTable.MysubjectQuery;
import com.example.hamsaapp.Data.mytasktable.Mytask;
import com.example.hamsaapp.Data.mytasktable.MytaskQuery;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //spnr1 تعريف صفة للكائن المرئي
    private Spinner spnrsubject;
    private FloatingActionButton fabadd;
    private SearchView srchv;
    private ListView lstvtasks;



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

        fabadd=findViewById(R.id.fabadd);
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to open new activity from current to next activity
                Intent i= new Intent(MainActivity.this,   AddTaskActivity.class);
                startActivity(i);
            }
        });





        lstvtasks=findViewById(R.id.lstvtasks);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.itemSettings)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();

        }
        if (item.getItemId()==R.id.itmSignout)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();

        }
        if (item.getItemId()==R.id.itmAddTask)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            //to open new activity from current to next activity
            Intent i= new Intent(MainActivity.this,   AddTaskActivity.class);
            startActivity(i);

        }
        return true;
    }


    /**
     * عملية تجهيز السبنر بالمواضيع
     */

    private void initSubjectspnr()
    {
        //مؤشر لقاعدة البيانات
        AppDataBase db=AppDataBase.getDB(getApplicationContext());
        //مؤشر لواجهة استعمالات جدول المواضيع
        MysubjectQuery subjectquery=db.getMySubjectQuery();
        // مصدر المعطيات: استخراج جميع المواضيع من الجدول
        List<Mysubject> allsubjects=subjectquery.getAllsubjects();
        //تجهيز وسيط
        ArrayAdapter<Mysubject> subjectadapter=new ArrayAdapter<Mysubject>(this, android.R.layout.simple_dropdown_item_1line);
        Mysubject s=new Mysubject();
        s.title="All";
        subjectadapter.add(s);//ستظهر أولا بالسبنر تعني عرض
        for (Mysubject subject:allsubjects)//اضافة المواضيع للوسيط
        {
            subjectadapter.add(subject);
        }
        spnrsubject.setAdapter(subjectadapter);//ربط السبنر بالوسيط
        spnrsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spnrsubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        //استخراج الموضوع حسب رقمه الترتيبي i
                        Mysubject item=subjectadapter.getItem(i);
                        if (item.equals("ALL"))//  الكلمة ALL تعني عرض جميع المهمات
                        {
                            initAllListView();
                        }
                        else
                        {
                            Mysubject subject=subjectquery.checkSubject(item.getTitle());
                            initListViewBySubjid(subject.getKeyid());

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
        });

    }

    /**
     * تجهيز قائمة جميع المهمات وعرضها ب ListView
     */

    public void initAllListView()
    {
        //مؤشر لقاعدة البيانات
        AppDataBase db=AppDataBase.getDB(getApplicationContext());

        //مؤشر لواجهة استعمالات جدول المهمات
        MytaskQuery taskQuery=db.getMyTaskQuery();

        // مصدر المعطيات: استخراج جميع المهمات من الجدول
        List<Mytask> alltasks= taskQuery.getAllTasks();
        ArrayAdapter<Mytask> taskstadapter=new ArrayAdapter<Mytask>(this, android.R.layout.simple_list_item_1);
        taskstadapter.addAll(alltasks);
        lstvtasks.setAdapter(taskstadapter);
    }


    /**
     * تجهيز قائمة المهمات حسب رقم الموضوع
     * @param key_id رقم الموضوع
     */

    private void initListViewBySubjid (long key_id)
    {
        //مؤشر لقاعدة البيانات
        AppDataBase db=AppDataBase.getDB(getApplicationContext());

        //مؤشر لواجهة استعمالات جدول المهمات
        MytaskQuery taskQuery=db.getMyTaskQuery();

        List<Mytask> tasksbyid=taskQuery.getTasksBySubjid(key_id);


        // مصدر المعطيات: استخراج جميع المهمات من الجدول التي تحتوي على نفس ال id
        ArrayAdapter<Mytask> taksadapter=new ArrayAdapter<Mytask>(this, android.R.layout.simple_list_item_1);
        taksadapter.addAll(tasksbyid);
        lstvtasks.setAdapter(taksadapter);
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














