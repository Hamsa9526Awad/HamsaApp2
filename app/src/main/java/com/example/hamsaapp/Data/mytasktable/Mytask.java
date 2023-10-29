package com.example.hamsaapp.Data.mytasktable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity//Entity==Table==جدول

/**
 * فئة تمثل مهمة
 */
public class Mytask
{
    @PrimaryKey(autoGenerate = true)// تجديد الصفة كمفتاح رئيسي والذي ينتج بشكل تلقائي
    /*** رقم المهمة*/
    public long keyid;//رقم المهمة

    /*** درجة الأهمية*/
    public int importance;

    /*** نص المهمة*/
    public String text;

    /*** رقم موضوع المهمة*/
    public long subid;

    /*** رقم المستعمل الذي أضاف المهمة*/
    public long userid;

    /*** زمن بناء المهمة*/
    public long time;
    /** هل تمت المهمة  */
    public boolean isCompleted;


    @Override
    public String toString() {
        return "mysubject{" +
                "keyid=" + keyid +
                ", importance=" + importance +
                ", text='" + text + '\'' +
                ", subid=" + subid +
                ", userid=" + userid +
                ", time=" + time +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public long getKeyid() {
        return keyid;
    }

    public int getImportance() {
        return importance;
    }

    public String getText() {
        return text;
    }

    public long getSubid() {
        return subid;
    }

    public long getUserid() {
        return userid;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setKeyid(long keyid) {
        this.keyid = keyid;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubid(long subid) {
        this.subid = subid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
