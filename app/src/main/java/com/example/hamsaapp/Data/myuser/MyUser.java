package com.example.hamsaapp.Data.myuser;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity//Entity==Table==جدول
// عندما نريد أن نتعامل مع هذه الفئة كجدول معطيات نضع Entity

/**
 * فئة تمثل مهمة
 */
public class MyUser
{
    @PrimaryKey(autoGenerate = true)// تجديد الصفة كمفتاح رئيسي والذي ينتج بشكل تلقائي
    public long keyid;//
    @ColumnInfo(name = "full_Name") //اعطاء اسم جديد للعامود(الصفة في الجدول)
    public String fullName;
    public String email;// بحالة لم يتم اعطاء اسم لعامود يكون اسم الصفة هو اسم العامود
    public String phone;
    public String passw;

    @Override
    public String toString() {
        return "MyUser{" +
                "keyid=" + keyid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassw() {return passw;}

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

}
