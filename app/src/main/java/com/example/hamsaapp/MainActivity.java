package com.example.hamsaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
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



    //العملية() onCreate
    //يتم استدعاء هذه الحالة عند إنشاء النشاط لأول مرة.
    // في هذه الحالة، يجب على النشاط تهيئة واجهة المستخدم الخاصة به وأي مكونات أخرى يحتاجها.
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

//        Log.d("hamsa","onCreate");
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
//
//        // بناء قاعدة بيانات وارجاع مؤشر عليها1
//        AppDataBase db= AppDataBase.getDB(getApplicationContext());
//        // مؤشر لجدول2
//        MysubjectQuery subjectQuery = db.getMySubjectQuery();
//        // مثال لاستعمال جدول البيانات
//        // بناء كائن من نوع الجدول وتحديد قيم الصفات3
//        Mysubject s1=new Mysubject();
//        s1.setTitle("Math");
//        Mysubject s2=new Mysubject();
//        s2.title="Computers"; // set ولكن بطريقة أخرى لأنها public
//
//        //اضافة كائن للجدول4
//        subjectQuery.insertsubject(s1);
//        subjectQuery.insertsubject(s2);
//
//        //5فحص هل تم حفظ ما سبق
//        // استخراج وطباعة جميع معطيات الجدول المواضيع
//
//        List<Mysubject> allsubjects = subjectQuery.getAllsubjects();
//        for (Mysubject s: allsubjects) {
//            Log.d("Hamsa",s.title);
//            Toast.makeText(this, s.title, Toast.LENGTH_SHORT).show();
//
//        }

        fabadd=findViewById(R.id.fabadd);

        //ينتج لنا تطبيق لفئة "مجهولة" anonymous class
        //(لأن كل معالج حدث من هذا النوع عبارة عن واجهة Interface ويجب تطبيقها،
        // وهنا نقوم بتطبيقها بشكل فوري بواسطة ما يسمى  anonymous class  )
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


        //فحص من هو العنصر الذي تم اختياره حسب ال id
        if (item.getItemId()==R.id.itemSettings)
        {
            //مقطع رد الفعل لحدث الضغط على العنصر
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            //to open new activity from current to next activity
            Intent i= new Intent(MainActivity.this,   AddTaskActivity.class);
            startActivity(i);

        }
        if (item.getItemId()==R.id.itmSignout)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            showYesNoDialog();

        }
        if (item.getItemId()==R.id.itmAddTask)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
            //to open new activity from current to next activity
            Intent i= new Intent(MainActivity.this,   AddTaskActivity.class);
            startActivity(i);

        }
        return true;//تم المعالجة بنجاح
    }

    /**
     * دالة مساعدة لفتح قائمة تتلقى برامتر للكائن الذي سبب فتح القائمة
     * نحن نرغب بفتح القائمة عند الضغط على عنصر - مهمة- في قائمة العرض listview
     * @param v برامتر للكائن الذي سبب فتح القائمة
     * @param t
     */
    public void showMenu(View v, Mytask t)
    {
        //بناء قائمة
        PopupMenu popup= new PopupMenu(this,v);// الكائن الذي سبب فتح القائمة v
        //ملف القائمة
        popup.inflate(R.menu.options_menu);

        //اضافة معالج حدث لاختيار عنصر من القائمة
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.itmComplete)
                {
                    //هنا نكتب رد الففعل لاختيار العنصر من القائمة
                    Toast.makeText(MainActivity.this,"Completed",Toast.LENGTH_SHORT).show();


                }
                if (menuItem.getItemId()==R.id.itmEdit)
                {
                    //to open new activity from current to next activity
                    Intent i= new Intent(MainActivity.this,   EditTaskActivity.class);
                    startActivity(i);


                }
                if (menuItem.getItemId()==R.id.itmDelete)
                {
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();

                    AppDataBase db=AppDataBase.getDB(MainActivity.this);
                    MytaskQuery mytaskQuery=db.getMyTaskQuery();
                    mytaskQuery.deleteTask(t.keyid);
                    initAllListView();
                    initSubjectspnr();

                }
                return true;

            }
        });

        popup.show();//فتح وعرض القائمة


    }

    /**
     * عملية تجهيز السبنر بالمواضيع
     */

    private void initSubjectspnr()
    {
        //مؤشر لقاعدة البيانات
        // ببنيها بس اول مرة حدا بسجل يعني أول مستخدم
        //  واذا كانت مبنية من قبل يعني مش أول واحد بستخدمها بس بحط عليها مؤشر
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


                spnrsubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        //استخراج الموضوع حسب رقمه الترتيبي i
                        Mysubject item=subjectadapter.getItem(i);
                        if (item.title.equals("All"))//  الكلمة ALL تعني عرض جميع المهمات
                        {
                            initAllListView();
                        }
                        else
                        {
                            //استخراج كائن الموضوع الذي اخترناه لاستخراج رقمه-استخرج رقم الموضوع keyid
                            Mysubject subject=subjectquery.checkSubject(item.getTitle());

                            //استدعاء العملية التي تجهز القائمة حسب رقم الموضوع
                            initListViewBySubjid(subject.getKeyid());

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



    }

    /**
     * تجهيز قائمة جميع المهمات وعرضها ب ListView
     */

    public void initAllListView()
    {
        //مؤشر لقاعدة البيانات
        // ببنيها بس اول مرة حدا بسجل يعني أول مستخدم
        //  واذا كانت مبنية من قبل يعني مش أول واحد بستخدمها بس بحط عليها مؤشر
        AppDataBase db=AppDataBase.getDB(getApplicationContext());

        //مؤشر لواجهة استعمالات جدول المهمات
        MytaskQuery taskQuery=db.getMyTaskQuery();

        // مصدر المعطيات: استخراج جميع المهمات من الجدول
        List<Mytask> alltasks= taskQuery.getAllTasks();

        //بناء الوسيط adapter
        ArrayAdapter<Mytask> taskstadapter=new ArrayAdapter<Mytask>(this, android.R.layout.simple_list_item_1);

        //نضيف كل المعطيات التي بال Data base إلى الوسيط ال adapter
        taskstadapter.addAll(alltasks);

        //بعطي الوسيط listview عشان يقدر يرتبهن
        lstvtasks.setAdapter(taskstadapter);
        lstvtasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMenu(view,taskstadapter.getItem(i));//رقم العنصر الذي سبب الحدث i
            }
        });
    }


    /**
     * تجهيز قائمة المهمات حسب رقم الموضوع
     * @param key_id رقم الموضوع
     */

    private void initListViewBySubjid (long key_id)
    {
        //مؤشر لقاعدة البيانات
        // ببنيها بس اول مرة حدا بسجل يعني أول مستخدم
        //  واذا كانت مبنية من قبل يعني مش أول واحد بستخدمها بس بحط عليها مؤشر
        AppDataBase db=AppDataBase.getDB(getApplicationContext());

        //مؤشر لواجهة استعمالات جدول المهمات
        MytaskQuery taskQuery=db.getMyTaskQuery();


        List<Mytask> tasksbyid=taskQuery.getTasksBySubjid(key_id);


        // مصدر المعطيات: استخراج جميع المهمات من الجدول التي تحتوي على نفس ال id

        //بناء وسيط taksadapter
        ArrayAdapter<Mytask> taksadapter=new ArrayAdapter<Mytask>(this, android.R.layout.simple_list_item_1);

        //يضيف كل المهمات التي تحتوي على نفس ال id للوسيط لل adapter
        taksadapter.addAll(tasksbyid);

        //بعطيه للوسيط  listView عشان يقدر يرتبهن
        lstvtasks.setAdapter(taksadapter);


    }

    /**
     * بناء ديالوج
     */
    public void showYesNoDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SignOut");//تحديد العنوان
        builder.setMessage("Are you sure?"); //تحديد فحوى شباك الحوار

        //بما انه ممنوع نعمل كائن من واجهةInterface فالحاسوب ببنيلنا فئة مجهولة
        //فبصير عنا فئة بقلب فئة
        //فعشان هيك منكتب MainActivity.this مش this لحالها
        //عشان يفرق البرنامج انو احنا قصدنا this تبع الMainActivity مش تبع الفئة المجهولة
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //معالجة حدث للموافقة
                Toast.makeText(MainActivity.this, "Signing Out", Toast.LENGTH_SHORT).show();
                finish();


            }
        });

        //بما انه ممنوع نعمل كائن من واجهةInterface فالحاسوب ببنيلنا فئة مجهولة
        //فبصير عنا فئة بقلب فئة
        //فعشان هيك منكتب MainActivity.this مش this لحالها
        //عشان يفرق البرنامج انو احنا قصدنا this تبع الMainActivity مش تبع الفئة المجهولة
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "No Signing Out", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog dialog = builder.create();//بناء شباك الحوار ديالوغ
        dialog.show();// عرض الشباك
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
        initAllListView();
        initSubjectspnr();

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
