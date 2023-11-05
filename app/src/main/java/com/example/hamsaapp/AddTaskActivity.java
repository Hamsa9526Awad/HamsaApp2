package com.example.hamsaapp;

import android.os.Bundle;
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








}
