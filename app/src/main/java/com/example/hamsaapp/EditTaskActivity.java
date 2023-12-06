package com.example.hamsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
        checkShortTitleAndTextinEdit();
        //to open new activity from current to next activity
        Intent i= new Intent(EditTaskActivity.this,   MainActivity.class);
        startActivity(i);


    }

    public void onClickCancelUpdateTask (View v)
    {
        finish();


    }



    private void checkShortTitleAndTextinEdit()
    {

        boolean isAllOk=true; // يحوي نتيجة فحص الحقول ان كانت سليمة

        String shortTitleEdit=etshorttitleEdit.getText().toString();
        String textEdit=etEdittext.getText().toString();

        if (shortTitleEdit.length()<1)
        {
            isAllOk=false;
            etshorttitleEdit.setError("short title is empty");
        }

        if (textEdit.length()<1)
        {
            isAllOk=false;
            etEdittext.setError("text is empty");
        }

        if (isAllOk)
        {
            Toast.makeText(this,"All ok",Toast.LENGTH_SHORT).show();
        }


    }

}
