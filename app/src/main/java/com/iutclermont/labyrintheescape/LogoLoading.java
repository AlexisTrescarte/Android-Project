package com.iutclermont.labyrintheescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogoLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoloading);

        this.waitS(6);
        //faire une fonction qui attend 5 secondes

        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private void waitS(double time){
        double startTime = System.currentTimeMillis();
        double stoptime = startTime+time*1000;
        while(startTime!=stoptime){
            startTime= System.currentTimeMillis();
        }
    }
}
