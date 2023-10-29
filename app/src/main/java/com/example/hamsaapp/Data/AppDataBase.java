package com.example.hamsaapp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hamsaapp.Data.mytasktable.Mytask;
import com.example.hamsaapp.Data.mytasktable.MytaskQuery;
import com.example.hamsaapp.Data.mySubjectsTable.Mysubject;
import com.example.hamsaapp.Data.mySubjectsTable.MysubjectQuery;
import com.example.hamsaapp.Data.myuser.MyUser;
import com.example.hamsaapp.Data.myuser.Myuserquery;


/*
 تعريف الجداول ورقم النسخة(version)
 عند تغير أي شيء يخص جدول أو جداول علينا تغيير رقم الاصدار
 ليتم بناء قاعدة البيانات من جديد
 */
@Database(entities = {MyUser.class, Mysubject.class, Mytask.class},version = 1)
/**
 * الفئة المسؤولة عن بناء قاعدة البيانات في كل جداولها
 * وتوفر لنا كائن للتعامل مع قاعدة البيانات
 */
public abstract class AppDataBase extends RoomDatabase {

    /**
     * كائن للتعامل مع قاعدة البيانات
     */
    private static AppDataBase db;

    /**
     * يعيد كائن لعمليات جدول المستعملين
     * @return
     */

    public abstract Myuserquery.MyUserQuery getMyUserQuery();

    /**
     * يعيد كائن لعمليات جدول المستعملين
     * @return
     */

    public abstract MysubjectQuery getMySubjectQuery();

    /**
     * يعيد كائن لعمليات جدول المستعملين
     * @return
     */

    public abstract MytaskQuery getMyTaskQuery();

    /**
     * بناء قاعدة البيانات واعادة كائن يؤشر عليها
     * @param context
     * @return
     */

    public static AppDataBase getDB(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                            AppDataBase.class, "Hamsadatabase")//اسم قاعدة البيانات
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();


        }
        return db;
    }
}

