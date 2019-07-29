package com.example.jihwa.project_sw;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

public class MainActivity extends AppCompatActivity {
    Button button_learning, button_help, button_setting, button_quiz;
    CircleProgressView mCircleView;
    DBManager dbmanager;
    SQLiteDatabase sqlDB;
    String avg;

    public static Context mContext;
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
        setContentView(R.layout.activity_main);


        button_learning = (Button)findViewById(R.id.btn_learn);
        button_help = (Button)findViewById(R.id.btn_help);
        button_quiz = (Button)findViewById(R.id.btn_quiz);
        button_setting = (Button)findViewById(R.id.btn_setting);
        mCircleView = (CircleProgressView) findViewById(R.id.btn_keep);
        mContext=this;
        dbmanager = new DBManager(this);

        Intent intent=new Intent(this, SwipeGraph.class);
        startActivity(intent);

        button_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), class_list.class);
                startActivity(intent);
            }
        });
        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), quiz_list.class);
                startActivity(intent);
            }
        });
        button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), QnAActivity.class);
                startActivity(intent);
            }
        });

        mCircleView.setOnAnimationStateChangedListener(
                new AnimationStateChangedListener() {
                    @Override
                    public void onAnimationStateChanged(AnimationState _animationState) {
                        switch (_animationState) {
                            case IDLE:
                            case ANIMATING:
                            case START_ANIMATING_AFTER_SPINNING:
                                mCircleView.setTextMode(TextMode.PERCENT); // show percent if not spinning
                                mCircleView.setUnitVisible(true);
                                break;
                            case SPINNING:
                                mCircleView.setTextMode(TextMode.TEXT); // show text while spinning
                                mCircleView.setUnitVisible(false);
                            case END_SPINNING:
                                break;
                            case END_SPINNING_START_ANIMATING:
                                break;

                        }
                    }
                }
        );
        new MainActivity.LongOperation().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new MainActivity.LongOperation().execute();
    }

    private class LongOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            sqlDB = dbmanager.getWritableDatabase();
            Cursor cursor = sqlDB.rawQuery("SELECT AVG(lecture) FROM RATE",null);
            avg = "";
            while(cursor.moveToNext()){
                avg+=cursor.getString(0);
                Log.d("KOYO",avg);
            }
            cursor.close();
            sqlDB.close();
            mCircleView.setValueAnimated(Float.parseFloat(avg));
        }
    }

}
