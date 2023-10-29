package com.example.hamsaapp.Data.mySubjectsTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity//Entity==Table==جدول

public class Mysubject {

    @PrimaryKey(autoGenerate = true)// تجديد الصفة كمفتاح رئيسي والذي ينتج بشكل تلقائي
    /*** رقم المهمة*/
    public long key_id;//رقم المهمة

    public String title;

    @Override
    public String toString() {
        return "Mysubject{" +
                "key_id=" + key_id +
                ", title='" + title + '\'' +
                '}';
    }

    public long getKeyid() {
        return key_id;
    }

    public String getTitle() {
        return title;
    }

    public void setKeyid(long keyid) {
        this.key_id = key_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
