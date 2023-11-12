package com.example.hamsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditTaskActivity extends AppCompatActivity {


    private Button btnupdateedittask;
    private Button btncanceledittask;
    private SeekBar skbrlimportanceEdit;
    private TextInputEditText etshorttitleEdit;
    private TextInputEditText etEdittext;
    private TextView txtimportance1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        btnupdateedittask= findViewById(R.id.btnupdateedittask);
        btncanceledittask= findViewById(R.id.btncanceledittask);
        skbrlimportanceEdit= findViewById(R.id.skbrlimportanceEdit);
        etshorttitleEdit= findViewById(R.id.etshorttitleEdit);
        etEdittext= findViewById(R.id.etEdittext);
        txtimportance1=findViewById(R.id.txtimportance1);

    }

    public void onClickupdateTask (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(EditTaskActivity.this,   MainActivity.class);
        startActivity(i);


    }

    public void onClickCancelUpdateTask (View v)
    {


    }

}
