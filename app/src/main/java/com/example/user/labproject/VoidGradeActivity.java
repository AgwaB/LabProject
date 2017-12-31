package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2017-12-30.
 */

public class VoidGradeActivity extends Activity {
    double topigo, packingThick, crackDeep, voidWidth, voidHeight, voidLength;
    TextView txt_grade, txt_limit;
    ImageView back, next;
    String stream;
    String grade = null, limit = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Intent intent = getIntent();
        topigo = Double.parseDouble(intent.getStringExtra("topigo"));
        packingThick = Double.parseDouble(intent.getStringExtra("packingThick"));
        crackDeep = Double.parseDouble(intent.getStringExtra("crackDeep"));
        voidWidth = Double.parseDouble(intent.getStringExtra("voidWidth"));
        voidHeight = Double.parseDouble(intent.getStringExtra("voidHeight"));
        voidLength = Double.parseDouble(intent.getStringExtra("voidLength"));
        stream = intent.getStringExtra("stream");


        back = (ImageView)findViewById(R.id.back);
        next = (ImageView)findViewById(R.id.next);

        txt_grade = (TextView)findViewById(R.id.grade);
        txt_limit = (TextView)findViewById(R.id.limit);

        grade = checkGrade(topigo,packingThick,crackDeep,voidWidth);
        limit = checkLimit(grade);

        txt_grade.setText(grade);
        txt_limit.setText(limit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("WhereWereYou", "void");
                intent.putExtra("Height", voidHeight*0.01);
                intent.putExtra("Width",voidWidth*0.01);
                intent.putExtra("Length",voidLength*0.01);
                intent.putExtra("stream",stream);

                startActivity(intent);
            }
        });
    }

    private String checkGrade(double topigo, double packingThick, double crackDeep, double voidWidth){
        if((topigo>=40 || packingThick >=30) && voidWidth<80){
            return "관찰등급";
        }
        else if(voidWidth>=150){
            return "우선등급";

        }
        else if(topigo>=20 || packingThick>=10 || (crackDeepPercent(packingThick, crackDeep)>=10 && crackDeepPercent(packingThick,crackDeep)<50)){
            return "우선등급";
        }
        else if(topigo>=0 || packingThick>=0 || crackDeepPercent(packingThick, crackDeep)>=50){
                return "긴급등급";
        }
        return "일반등급";

    }
    private String checkLimit(String grade){
        if(grade.equals("긴급등급"))
            return "4시간 이내 복구";
        if(grade.equals("우선등급"))
            return "신속한 조치계획 수립과 복구 필요";
        if(grade.equals("일반등급"))
            return "우기철 이전까지 복구 필요";
        if(grade.equals("관찰등급"))
            return "일정 시간 괄찰 후 반복 탐사 시작 년도의 우기 이전까지의 복구 필요";
        return null;
    }
    private double crackDeepPercent(double packingThick,double crackDeep){
        return (crackDeep/packingThick)*100;
    }
}
