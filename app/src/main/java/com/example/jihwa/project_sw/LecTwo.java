package com.example.jihwa.project_sw;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecTwo extends YouTubeBaseActivity {

    DBManager dbmanager;

    YouTubePlayerView youtubeView;
    YouTubePlayer.OnInitializedListener listener;

    private final String[] fileName = {"No", "CoL", "atomicTask", "choose"};
    int fileNum;
    LecReader lecReader;
    String result = "";
    String result2 = "";

    DrawerLayout drawerLayout;
    LinearLayout lectureLayout;
    Button btn_lecture_list, btn_skip, btn_plus_content;
    Button btn_lecture1, btn_lecture2, btn_lecture3, btn_lecture4;
    TypingText tv_lectureContent, tv_lectureContent2;
    ScrollView scrollView;
    Button nextLec, currentQuiz;
    Dialog epicDialog;
    TextView titleTv, messageTv;
    ImageView closePopup;

    ImageView image_lecture1, image_lecture2, image_lecture3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View V = getWindow().getDecorView();
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ShiftTheme);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(Color.parseColor("#FFFFEEEB"));
                V.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else {
            setTheme(R.style.AppTheme);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(Color.parseColor("#FFF8E7"));
                V.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lec_two);

        lecReader = new LecReader();

        drawerLayout = (DrawerLayout) findViewById(R.id.Lecsecond);
        lectureLayout = (LinearLayout) findViewById(R.id.lecturecontentsecond);
        btn_lecture_list = (Button) findViewById(R.id.btn_lectureList);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_plus_content = (Button) findViewById(R.id.btn_pluscontent);
        scrollView = (ScrollView) findViewById(R.id.scroll);
        tv_lectureContent = (TypingText) findViewById(R.id.tv_lectureContent);
        tv_lectureContent.setTypingSpeed(50);
        tv_lectureContent2 = (TypingText) findViewById(R.id.tv_lectureContent2);
        tv_lectureContent2.setTypingSpeed(50);

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("cDA3_5982h8");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youtubeView.initialize("AIzaSyAM9bsTEqbt0xGDztWoGXLAjNTHkZDecZE", listener);

        btn_lecture1 = (Button) findViewById(R.id.btn_lecture1);
        btn_lecture2 = (Button) findViewById(R.id.btn_lecture2);
        btn_lecture3 = (Button) findViewById(R.id.btn_lecture3);
        btn_lecture4 = (Button) findViewById(R.id.btn_lecture4);
        image_lecture1 = (ImageView) findViewById(R.id.image_lecture1);
        image_lecture2 = (ImageView) findViewById(R.id.image_lecture2);
        image_lecture3 = (ImageView) findViewById(R.id.image_lecture3);

        btn_lecture1.setOnClickListener(onClickListener);
        btn_lecture2.setOnClickListener(onClickListener);
        btn_lecture3.setOnClickListener(onClickListener);
        btn_lecture4.setOnClickListener(onClickListener);

        dbmanager = new DBManager(this);
        epicDialog = new Dialog(this);


        int rate = dbmanager.select_Lec(fileName[1]);
        setimage(image_lecture1, rate);
        rate = dbmanager.select_Lec(fileName[2]);
        setimage(image_lecture2, rate);
        rate = dbmanager.select_Lec(fileName[3]);
        setimage(image_lecture3, rate);

        Intent getitt = getIntent();
        fileNum = getitt.getIntExtra("number", 0);

        try {
            loadItemsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btn_lecture_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(lectureLayout);
            }
        });

        final String finalResult = result;
        btn_plus_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                if (!tv_lectureContent.isRunning() && !tv_lectureContent2.isRunning()) {
                    if ((String) btn_plus_content.getText() != "퀴즈 풀기") {
                        tv_lectureContent2.setTextAutoTyping(result2, scrollView);
                        SQLiteDatabase db = dbmanager.getWritableDatabase();
                        db.execSQL("UPDATE RATE SET lecture = 50 WHERE name = 'atomicTask';");
                        btn_plus_content.setText("퀴즈 풀기");
                    } else {
                        SQLiteDatabase db = dbmanager.getWritableDatabase();
                        db.execSQL("UPDATE RATE SET lecture = 100 WHERE name = 'atomicTask';");
                        //퀴즈로 넘어감
                        ShowPopUp();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "강의를 모두 수강하여야 넘어가실 수 있습니다.^^\n퀴즈를 원하시는 분은 오른쪽 위 > 를 눌러보세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //퀴즈로 넘어감
                Intent intent = new Intent(getApplicationContext(), QuizActivity2.class);
                startActivity(intent);
                finish();
            }
        });

    }

    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            //내부 리스트 클릭시 드로어를 닫고 싶을 때
            drawerLayout.closeDrawers();
            Intent itt;
            switch (v.getId()) {
                case R.id.btn_lecture1:
                    itt = new Intent(getApplicationContext(), LecFirst.class);
                    itt.putExtra("number", 1);
                    startActivity(itt);
                    finish();
                    break;
                case R.id.btn_lecture2:
                    itt = new Intent(getApplicationContext(), LecTwo.class);
                    itt.putExtra("number", 2);
                    startActivity(itt);
                    finish();
                    break;
                case R.id.btn_lecture3:
                    itt = new Intent(getApplicationContext(), LecThird.class);
                    itt.putExtra("number", 3);
                    startActivity(itt);
                    finish();
                    break;
                case R.id.btn_lecture4:
                    Toast.makeText(getApplicationContext(), "존재하지 않는 강의 입니다.\n 다른 강의를 눌러주세요 > <", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "존재하지 않는 강의 입니다.\n 다른 강의를 눌러주세요 > <", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void loadItemsFromFile() throws IOException {
        File file = new File(getFilesDir(), fileName[fileNum]);
        File root = getFilesDir();
        FileReader fr = null;
        BufferedReader bufrd = null;
        String str;

        if (!file.exists()) {
            lecReader.secondLec(root, fileNum);
        }
        int i = 0;
        if (file.exists()) {
            try {
                fr = new FileReader(file);
                bufrd = new BufferedReader(fr);

                while ((str = bufrd.readLine()) != null) {
                    result += str;
                    i++;
                    if (i == 5) {
                        tv_lectureContent.setTextAutoTyping(result, scrollView);
                        break;
                    }
                    result += "\n\n";
                }

                while ((str = bufrd.readLine()) != null) {
                    result2 += str;
                    result2 += "\n\n";
                    i++;
                }

                bufrd.close();
                fr.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("오류 발생", "파일 불러오기!!!");
                Toast.makeText(getApplicationContext(), "죄송합니다. 로딩 중 오류가 발생하였으니\n 종료 후 다시 접속해 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    void setimage(ImageView img, int i) {
        if (i == 0) {
            img.setImageResource(R.drawable.zero);
        } else if (i == 50) {
            img.setImageResource(R.drawable.half);
        } else if (i == 100) {
            img.setImageResource(R.drawable.perpect);
        }
    }


    public void ShowPopUp() {
        epicDialog.setContentView(R.layout.epic_popup);
        closePopup = (ImageView) epicDialog.findViewById(R.id.closePopup);
        nextLec = (Button) epicDialog.findViewById(R.id.nextLec);
        currentQuiz = (Button) epicDialog.findViewById(R.id.currentQuiz);

        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

        nextLec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LecThird.class);
                intent.putExtra("number", 3);
                startActivity(intent);
                finish();
            }
        });

        currentQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
