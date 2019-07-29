package com.example.jihwa.project_sw;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;

import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import at.grabner.circleprogress.AnimationState;
import at.grabner.circleprogress.AnimationStateChangedListener;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

public class SwipeGraph extends SwipeBackActivity {
    CircleProgressView mCircleView;
    DBManager dbmanager;
    SQLiteDatabase sqlDB;
    String avg;
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
        setContentView(R.layout.activity_swipe_graph);

        Intent intent=new Intent(this, LoadingActivity_1.class);
        startActivity(intent);

        setDragEdge(SwipeBackLayout.DragEdge.RIGHT);
        dbmanager = new DBManager(this);

        mCircleView = (CircleProgressView) findViewById(R.id.circleView);
        mCircleView.setShowTextWhileSpinning(true); // Show/hide text in spinning mode
        mCircleView.setText("불러오는중");
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
        new LongOperation().execute();

    }
    private class LongOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            SwipeGraph.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCircleView.setValue(0);
                    mCircleView.spin();
                }
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


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
