package com.example.hamsaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {


    private TextView txtwelcome;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        txtwelcome= findViewById(R.id.txtwelcome);


        //start next activity (screen) automatically  after period of time
        Handler h=new Handler();


        Runnable r=new Runnable()
        {
            @Override
            public void run()
            {
                //to open new activity from current to next
                Intent i= new Intent(SplashScreen.this,SignIn .class);
                startActivity(i);
                //to close current activity
                finish();

            }
        }
        h.postDelayed(r,3000);

    }

}
