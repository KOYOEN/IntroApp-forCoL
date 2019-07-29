package com.example.jihwa.project_sw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by A on 2018-11-15.
 */

public class QuizActivity2 extends Activity {
    Button next;
    EditText answer_sheet;
    TextView question;
    ImageView number,popopimage;
    RadioButton true_answer, false_answer;
    Button nextLec,currentQuiz;
    Dialog epicDialog;
    ImageView closePopup;
    TextView messageTv,messageTv1,titleTv;
    LottieAnimationView action_image;
    int a;
    String[] answers = {"x", "o", "o"};
    String[] questions = {" \" 매점에 가서 메론빵 하나만 사와줄래? \" ", " \" a == 2   [판단문] \"", " \"  b = 3   [배정문] \""};

    @SuppressLint("WrongConstant")
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
        setContentView(R.layout.quiz2);

        Intent itt = getIntent();
        a = itt.getIntExtra("a", 0);

        true_answer = findViewById(R.id.true_answer);
        false_answer = findViewById(R.id.false_answer);
        number = findViewById(R.id.number);
        question = findViewById(R.id.textView);
        next = findViewById(R.id.next_btn);
        findViewById(R.id.submit_btn).setOnClickListener(SClickListener);
        findViewById(R.id.show_answer).setOnClickListener(AClickListener);
        answer_sheet = findViewById(R.id.answer_sheet);
        epicDialog = new Dialog(this);


        /*질의문 변경,숫자 사진 변경*/
        switch (a) {
            case 0:
               number.setImageResource(R.drawable.number_1);
                question.setText(questions[0]);
                break;
            case 1:
                question.setText(questions[1]);
                number.setImageResource(R.drawable.number_2);
                break;
            case 2:
                question.setText(questions[a]);
                number.setImageResource(R.drawable.number_3);
                next.setText("목록으로");
                break;
            case 3:
              finish();
        }

        /*다음 버튼 눌렀을때,  a값 증가시키고 새로고침*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a++;
                Intent intent = getIntent();
                intent.putExtra("a", a);
                if(a < 3) {
                    finish();
                    startActivity(intent);
                }
                else {
                    new AlertDialog.Builder(QuizActivity2.this)
                        .setTitle("마지막 퀴즈")
                        .setMessage("해당 챕터의 퀴즈를 모두 풀었습니다!\n목록으로 돌아갑니다.")
                        .setPositiveButton("돌아가기", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
            }
        });
    }

    /*answer 클릭시 팝업 메세지*/
    Button.OnClickListener AClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ShowPopUp("정답", "정답 : " + answers[a]);
        }
    };

    /*submit 클릭시 팝업 메세지*/
    Button.OnClickListener SClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String result;
            if ((true_answer.isChecked()&&a==0)||(false_answer.isChecked()&&a==1)||(false_answer.isChecked()&&a==2))
                result = "다시 한번 시도해 보세요!";
            else if (!true_answer.isChecked()&&!false_answer.isChecked()) {
                result = "정답을 선택하지 않았습니다.\n정답을 선택해 주세요.";
            }else
                result = "정답입니다!";

            ShowPopUp("정답확인", result);
        }
    };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("퀴즈 종료")
                .setMessage("퀴즈를 끝내고 목록으로 나가시겠습니까?")
                .setNegativeButton("아니오", null)
                .setPositiveButton("네", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }


    @SuppressLint("WrongConstant")
    public void ShowPopUp(String A, String B) {
        epicDialog.setContentView(R.layout.epic_popup);
        popopimage=epicDialog.findViewById(R.id.popup_image);
        action_image=epicDialog.findViewById(R.id.action_image);
        popopimage.setVisibility(View.GONE);

        /*추가 부분*/
        if(B=="정답입니다!"){
            action_image.setVisibility(View.GONE);
        }
        else if(B=="다시 한번 시도해 보세요!"){
            action_image.setVisibility(View.GONE);
        }else{
            action_image.setVisibility(View.VISIBLE);
        }

        closePopup = (ImageView) epicDialog.findViewById(R.id.closePopup);
        nextLec = (Button)epicDialog.findViewById(R.id.nextLec);
        currentQuiz = (Button)epicDialog.findViewById(R.id.currentQuiz);

        titleTv=epicDialog.findViewById(R.id.titleTv);
        messageTv=epicDialog.findViewById(R.id.messageTv);
        messageTv1=epicDialog.findViewById(R.id.messageTv1);

        nextLec.setVisibility(0x00000004);
        messageTv.setVisibility(0x00000004);

        titleTv.setText(A);
        messageTv1.setText(B);
        currentQuiz.setText("확인");

        currentQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


        //nextLec과 currentQuiz 추후 추가 필요함
    }
}
