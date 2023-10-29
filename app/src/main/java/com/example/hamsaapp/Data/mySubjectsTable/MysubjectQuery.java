package com.example.hamsaapp.Data.mySubjectsTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao//واجهة استعلامات على قاعدة بيانات
public interface MysubjectQuery {

    /**
     * اعادة جميع معطيات جدول المهمات
     * @return *قائمة من  المهمات
     */
    @Query("SELECT * FROM Mysubject")
    List<Mysubject> getAllsubjects();

    /**
     * ادخال مهمات
     * @param s * مجموعة مهمات
     */
    @Insert
    void insertsubject(Mysubject...s);//ثلاثة نقاط تعني مجموعة

    /**
     * تعديل المهمات
     * @param subject * مجموعة مهمات للتعديل(التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void updatesubject(Mysubject...subject);

    @Delete
    void deletesubject(Mysubject...subject);
    @Query("DELETE FROM Mytask WHERE keyid=:id")
    void deletesubject(long id);


}
