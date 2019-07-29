package com.example.jihwa.project_sw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

/**
 * Created by A on 2018-10-02.
 */

public class LoadingActivity_1 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.loading_1);

        startLoading();
    }
    private void startLoading(){

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);

    }
}
