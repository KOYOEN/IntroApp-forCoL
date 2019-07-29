package com.example.jihwa.project_sw;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
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
        setContentView(R.layout.activity_qna);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar1);



        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<QnAGroup> QuestGroup = new ArrayList<>();

        ArrayList<QnAChild> ans1 = new ArrayList<>();
        ans1.add(new QnAChild("강의 - 강의목록을 보시면 표시되어있습니다."));
        QnAGroup quest1 = new QnAGroup("Q1. 강의진척도를 어디서 볼 수 있나요?", ans1);
        QuestGroup.add(quest1);

        ArrayList<QnAChild> ans2 = new ArrayList<>();
        ans2.add(new QnAChild("롤리팝(21)버전부터 가능합니다."));
        QnAGroup quest2 = new QnAGroup("Q2. 모든 안드로이드폰에서 사용 가능한가요?", ans2);
        QuestGroup.add(quest2);

        ArrayList<QnAChild> ans3 = new ArrayList<>();
        ans3.add(new QnAChild("010-4937-3844로 연락하시기 바랍니다. 장난문자 환영 남자 환영."));
        QnAGroup quest3 = new QnAGroup("Q3. 기타 문의사항은 어디로 말해야하나요?", ans3);
        QuestGroup.add(quest3);

        ArrayList<QnAChild> ans4 = new ArrayList<>();
        ans4.add(new QnAChild("문제를 풀 때 마다 확인하실 수 있습니다."));
        QnAGroup quest4 = new QnAGroup("Q4. 퀴즈 정답은 저장이 되나요?", ans4);
        QuestGroup.add(quest4);

//         추가용
//        ArrayList<QnAChild> ans5 = new ArrayList<>();
//        ans5.add(new QnAChild("강의 - 강의목록을 보시면 표시되어있습니다."));
//        QnAGroup quest5 = new QnAGroup("Q1. 강의진척도를 어디서 볼 수 있나요?", ans5);
//        QusetGroup.add(quest5);

        QnAAdapter adapter = new QnAAdapter(QuestGroup);
        recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call: {
                Uri uri = Uri.parse("tel:01049373844");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            }
            case R.id.email: {
                Uri uri2 = Uri.parse("mailto:jihwancom@naver.com");
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri2);
                startActivity(intent1);
                break;
            }
        }
        return true;
    }
}
