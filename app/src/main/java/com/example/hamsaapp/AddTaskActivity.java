package com.example.hamsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {

    private Button btnsavetask;
    private Button btncanceltask;
    private SeekBar skbrlimportance;
    private TextInputEditText etshorttitle;
    private TextInputEditText ettext;
    private TextView txtimportance;



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
    }


    public void onClicksaveTask (View v){

        //to open new activity from current to next activity
        Intent i= new Intent(AddTaskActivity.this,   MainActivity.class);
        startActivity(i);


    }

    public void onClickcanceladdtask (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(AddTaskActivity.this,   MainActivity.class);
        startActivity(i);
        //to close current activity
        finish();

    }








}
