package com.example.jihwa.project_sw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

/**
 * Created by jihwa on 2018-11-20.
 */

public class quiz_list extends Activity{
    Button quiz1, quiz2, quiz3, quiz4, quiz5, quiz6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        View V = getWindow().getDecorView();
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.ShiftTheme);
            if(Build.VERSION.SDK_INT>=21)
            {
                getWindow().setStatusBarColor(Color.parseColor("#FFFFEEEB"));
                V.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        else{
            setTheme(R.style.AppTheme);
            if(Build.VERSION.SDK_INT>=21)
            {
                getWindow().setStatusBarColor(Color.parseColor("#FFF8E7"));
                V.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_list);

        quiz1 = (Button)findViewById(R.id.btn_quiz1);
        quiz2 = (Button)findViewById(R.id.btn_quiz2);
        quiz3 = (Button)findViewById(R.id.btn_quiz3);
        quiz4 = (Button)findViewById(R.id.btn_quiz4);
        quiz5 = (Button)findViewById(R.id.btn_quiz5);
        quiz6 = (Button)findViewById(R.id.btn_quiz6);

        quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity2.class);
                startActivity(intent);
            }
        });

        quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        quiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        quiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        quiz5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        quiz6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
