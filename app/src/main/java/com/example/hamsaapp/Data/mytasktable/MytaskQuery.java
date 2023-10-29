package com.example.hamsaapp.Data.mytasktable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao//واجهة استعلامات على قاعدة بيانات
public interface MytaskQuery
{
    /**
     * اعادة جميع معطيات جدول المهمات
     * @return *قائمة من  المهمات
     */
    @Query("SELECT * FROM Mytask")
    List<Mytask> getAllTasks();

    @Query("SELECT * FROM Mytask WHERE userid=:userid_p ORDER BY time DESC")
    List<Mytask> getalltasksOrderedby(long userid_p);

    /**
     * استخراج المهمات حسب المستعمل وهل انتهت أم لا ومرتبة حسب الأهمية
     * @param userid_p
     * @param iscompleted_p
     * @return
     */

    @Query("SELECT * FROM Mytask WHERE userid=:userid_p AND isCompleted=:iscompleted_p "+
            "ORDER BY importance DESC")
    List<Mytask> getalltasksOrderedby(long userid_p, boolean iscompleted_p);


    /**
     * ادخال مهمات
     * @param t * مجموعة مهمات
     */
    @Insert
    void insertTask(Mytask...t);//ثلاثة نقاط تعني مجموعة

    /**
     * تعديل المهمات
     * @param task * مجموعة مهمات للتعديل(التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void updateTask(Mytask...task);

    /**
     * حذف مهمة أو مهمات
     * @param task * حذف المهمات ( حسب المفتاح الرئيسي)
     */
    @Delete
    void deleteTask(Mytask...task);
    @Query("DELETE FROM Mytask WHERE keyid=:id")
    void deleteTask(long id);


}
