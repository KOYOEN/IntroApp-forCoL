package com.example.jihwa.project_sw;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by YM on 2018-11-15.
 */

public class LecReader {

    private final String[] fileName={"No","CoL", "atomicTask","choose"};

    public void firstLec(File root, int fileNum) throws IOException {
        File file=new File(root,fileName[fileNum]);
        FileWriter fw=null;
        BufferedWriter bufwr=null;

        try{
            fw=new FileWriter(file);
            bufwr=new BufferedWriter(fw);

            String str=null;
            str="인공지능에서는 자연어를 기계가 인식할 수 있는 언어로 변환하는 과정이 필요한데 현존하는 프로그래밍 언어로는 그 과정을 구현하기에 적합하지 않습니다.\n";
            str+="왜냐하면, 현재 사용하고 있는 언어는 기계어로부터 자연어로 변화되어왔지만,\n 인공지능은 자연어를 기계어로 해석하는 기술이 필요하기 때문입니다.\n";
            str+="그래서 인공지능의 새로운 패러다임을 제시하기 위해선 자연어로부터 출발하는 새로운 프로그래밍 언어가 필요합니다.\n";
            str+="이 필요성을 충족시키기 위해 나온 언어가 ’CoL‘입니다.\n\n";

            str+="기존의 프로그래밍 언어는 기계어로부터 자연어로 변화되었습니다.\n";
            str+="이러한 특징으로 인해 기존의 언어에서는 ’x²==4인 x를 구하라’라는 자연어로 이루어진 명령어를 처리하기에 한계가 있습니다. \n";
            str+="하지만 CoL은 자연어로부터 시작되기 때문에 기존의 언어보다 자연어 처리 능력이 뛰어납니다.\n";
            str+="그러므로 CoL은 앞으로 인공지능 분야를 발전시킬 핵심 언어가 될 것 입니다. 또한, 이 언어를 배우는 과정에서 프로그래밍 분야의 새로운 시각을 가지게 될 것입니다.\n";

            Log.d("내용",str);
            bufwr.write(str);
            bufwr.newLine();

        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","첫번째 강의 저장!");
        }

        try {
            if(bufwr!=null){
                bufwr.close();
            }
            if(fw!=null){
                fw.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","첫번째 파일 닫기!!");
        }
    }

    public void secondLec(File root, int fileNum) throws IOException {
        File file=new File(root,fileName[fileNum]);
        FileWriter fw=null;
        BufferedWriter bufwr=null;

        try{
            fw=new FileWriter(file);
            bufwr=new BufferedWriter(fw);

            String str=null;
            str="이 영상에서 ‘샌드위치’를 만들기 위해 ‘빵의 봉지를 연다.‘, ‘빵을 2개를 꺼낸다.’ 등과 같이 더 이상 세분화할 수 없는 최소 행위 단위를 atomic task라고 합니다.\n";
            str+="예를 들어, ‘지금 나오는 노래를 알려줘’라는 문장은 atomic task가 될 수 없습니다.\n";
            str+="왜냐하면, 이 한 문장에는 여러 가지 atomic task가 혼합되어 있습니다.\n";
            str+="노래를 들고 어떤 노래인지 판단하고 그것을 알려주는 총 3가지의 atomic task로 나눌 수 있지만,\n 어떤 노래인지 판단하는 것은 무한히 많은 노래 중에 하나를 찾아야 되기 때문에,\n 무한개의 atomic task 입니다.\n";
            str+="그렇기에 3가지 atomic task라고 생각했던 저 예시는 무한개의 atomic task를 포함하고 있는 문장으로 볼 수 있습니다.\n";
            str+="컴퓨터 측면으로 본다면,\n";
            str+="a=1;과 같은 배정문도 하나의 atomic task로 볼 수 있습니다.\n";
            str+="그에 비해, for( 초기식 ; 조건식 ; 증감식 )은 여러개의 atomic task로 이루어져 있습니다. 첫줄에서만 여러개의 atomic task로 구성될 수 있습니다.\n";

            Log.d("내용",str);
            bufwr.write(str);
            bufwr.newLine();

        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","두번째 강의 저장!");
        }

        try {
            if(bufwr!=null){
                bufwr.close();
            }
            if(fw!=null){
                fw.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","두번째 파일 닫기!!");
        }
    }

    public void thirdLec(File root, int fileNum) throws IOException {
        File file=new File(root,fileName[fileNum]);
        FileWriter fw=null;
        BufferedWriter bufwr=null;

        try{
            fw=new FileWriter(file);
            bufwr=new BufferedWriter(fw);

            String str=null;
            str="⊔choose는 유한개의 선택지를 가진 질문에 대해 상대방이 결정할 수 있도록 하는 문법입니다.\n" +
                "if(판단문){\n" +
                "\tcase A; }\n" +
                "else{\n" +
                "\tcase B; }\n"+
                "if then else문은 판단문에서 false가 판단되면 B를 선택하게 되고\n true로 판단되면 A가 선택됩니다. 이와 같이 A 혹은 B 둘 중 하나를 무조건 선택하게 됩니다.\n"+
                "CoL에서 if then else와 유사한 기능을 하는 것이 ⊔choose입니다.\n"+
                "다른 점은 case를 2개 이상의 유한개로 확장 가능하다는 점입니다.\n"+
                "예를 들어, A가 B에게 아메리카노와 카푸치노를 사와서 ‘어떤 걸 마실래?’라고 물어봅니다.\n"+
                "그때 B는 아메리카노와 카푸치노 중에 선택할 수 있는 결정권을 가집니다. 이 상황에서 위의 질문 대신에 CoL 문법으로 ‘아메리카노 ⊔ 카푸치노’ 라고 표현할 수 있습니다.\n"+
                "두 질문 모두 A가 결정권을 B에게 넘기는 것을 의미합니다.\n";

            Log.d("내용",str);
            bufwr.write(str);
            bufwr.newLine();

        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","세번째 강의 저장!");
        }

        try {
            if(bufwr!=null){
                bufwr.close();
            }
            if(fw!=null){
                fw.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("오류 발생","세번째 파일 닫기!!");
        }
    }

}
