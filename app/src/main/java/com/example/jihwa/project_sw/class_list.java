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
 * Created by jihwa on 2018-11-09.
 */

public class class_list extends Activity {
    Button btn_class1, btn_class2, btn_class3, btn_class4;

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
        setContentView(R.layout.class_list);

        btn_class1 = (Button)findViewById(R.id.btn_class1);
        btn_class2 = (Button)findViewById(R.id.btn_class2);
        btn_class3 = (Button)findViewById(R.id.btn_class3);
        btn_class4 = (Button)findViewById(R.id.btn_class4);

        btn_class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LecFirst.class);
                startActivity(intent);
            }
        });
        btn_class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LecTwo.class);
                startActivity(intent);
            }
        });
        btn_class3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LecThird.class);
                startActivity(intent);
            }
        });



    }
}
