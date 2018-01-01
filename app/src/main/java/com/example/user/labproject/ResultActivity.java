package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 2017-12-30.
 */

public class ResultActivity extends Activity {
    TextView txt_pouchNum, txt_location, txt_injectNum, txt_injectVelocity, txt_time_hour, txt_time_min;
    ImageView img_location, back;
    double volume;
    double pouchNum;
    double height, width, length;
    int time_hour;
    double time_min;
    String stream;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txt_pouchNum = (TextView)findViewById(R.id.pouchNum);
        txt_location = (TextView)findViewById(R.id.location);
        txt_injectNum = (TextView)findViewById(R.id.injectNum);
        txt_injectVelocity = (TextView)findViewById(R.id.injectVelocity);
        txt_time_hour = (TextView)findViewById(R.id.time_hour);
        txt_time_min = (TextView)findViewById(R.id.time_min);

        img_location = (ImageView)findViewById(R.id.img_location);
        back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();

        if(intent.getStringExtra("WhereWereYou").equals("sink")) {
            height = intent.getDoubleExtra("Height", 0.0);
            width = intent.getDoubleExtra("Width", 0.0);
            length = intent.getDoubleExtra("Length", 0.0);

            if(height == 0)
                volume = getVolume(width, length)/2;
            else
                volume = getVolume(height, width, length)/2;

            pouchNum = getPouch(volume, 4);
            time_min = getTime(volume,4);

            time_hour = (int)(time_min/60);
            time_min = time_min - (60*time_hour);


            txt_pouchNum.setText(Integer.toString((int)Math.ceil(pouchNum)));
            txt_location.setText("없음");
            txt_injectNum.setText("4");
            txt_injectVelocity.setText(Integer.toString((int)Math.ceil(pouchNum/(time_min* 60))));
            txt_time_hour.setText(Integer.toString((int)time_hour));
            txt_time_min.setText(Integer.toString((int)Math.ceil(time_min)));

            img_location.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("WhereWereYou").equals("void")) {
            height = intent.getDoubleExtra("Height", 0.0);
            width = intent.getDoubleExtra("Width", 0.0);
            length = intent.getDoubleExtra("Length", 0.0);

            if (height == 0)
                volume = getVolume(width, length);
            else
                volume = getVolume(height, width, length);

            stream = intent.getStringExtra("stream");

            if (stream.contains("방향")) {
                txt_location.setText("가장자리");
                txt_injectNum.setText("2");
                pouchNum = getPouch(volume, 2);
                time_min = getTime(volume, 2);
            }
            else{
                txt_location.setText("중앙");
                txt_injectNum.setText("4");
                pouchNum = getPouch(volume, 4);
                time_min = getTime(volume, 4);
            }

            time_hour = (int)(time_min/60);
            time_min = time_min - (60*time_hour);


            txt_pouchNum.setText(Integer.toString((int)Math.ceil(pouchNum)));
            txt_injectVelocity.setText(Integer.toString((int)Math.ceil(pouchNum/(time_min* 60))));
            txt_time_hour.setText(Integer.toString((int)time_hour));
            txt_time_min.setText(Integer.toString((int)Math.ceil(time_min)));

            if(txt_location.getText().equals("가장자리"))
                img_location.setBackgroundResource(R.mipmap.edge);
            if(txt_location.getText().equals("중앙"))
                img_location.setBackgroundResource(R.mipmap.center);
        }


    }

    private double getVolume(double height, double width, double length){
        return (4.0/3.0)*Math.PI*height*width*length;
    }
    private double getVolume(double width, double length){ // 높이가 입력 되지 않았을 경우
        return (4.0/3.0)*Math.PI*width*length*length;
    }

    private double getPouch(double volume,int injectNum){ //0.1m3, 주입구 1개 기준 파우치 11900개, 20분 소요 , 1분에 0.005m3
        return volume / 0.00000840336134 / (double)injectNum;
    }
    private double getTime(double volume, int injectNum){
        return volume / 0.005 / (double)injectNum;
    }

}
