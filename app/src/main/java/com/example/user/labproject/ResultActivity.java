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
    TextView txt_pouchNum, txt_punch_location, txt_injectNum, txt_injectVelocity, txt_time_hour, txt_time_min, txt_injectPressure;
    ImageView img_location, back;
    double volume;
    double pouchNum;
    double height, width, length;
    int time_hour;
    double time_min;
    double under_location;
    double topigo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txt_pouchNum = (TextView)findViewById(R.id.pouchNum);
        txt_punch_location = (TextView)findViewById(R.id.location);
        txt_injectNum = (TextView)findViewById(R.id.injectNum);
        txt_injectVelocity = (TextView)findViewById(R.id.injectVelocity);
        txt_time_hour = (TextView)findViewById(R.id.time_hour);
        txt_time_min = (TextView)findViewById(R.id.time_min);
        txt_injectPressure = (TextView)findViewById(R.id.injectPressure);

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
            txt_punch_location.setText("없음");
            txt_injectNum.setText("4");
            txt_injectVelocity.setText(Float.toString(5950.0f/600.0f*2.0f));
            txt_time_hour.setText(Integer.toString((int)time_hour));
            txt_time_min.setText(Integer.toString((int)Math.ceil(time_min)));

                img_location.setBackgroundResource(R.mipmap.result_sink);
        }

        if(intent.getStringExtra("WhereWereYou").equals("void")) {
            topigo = intent.getDoubleExtra("topigo",0.0);
            width = intent.getDoubleExtra("Width", 0.0);
            length = intent.getDoubleExtra("Length", 0.0);
            under_location = intent.getDoubleExtra("under_location",0.0);

            volume = getVolume(width, length);

            if((width*1.2 > length) || (length*1.2 > width)){
                txt_punch_location.setText("가장자리");
                txt_injectNum.setText("2");
                txt_injectVelocity.setText(Float.toString(5950.0f/600.0f));
                pouchNum = getPouch(volume, 2);
                time_min = getTime(volume, 2);

                if(topigo > under_location)
                    img_location.setBackgroundResource(R.mipmap.edge_half);
                else
                    img_location.setBackgroundResource(R.mipmap.edge_full);
            }
            else {
                txt_punch_location.setText("중앙");
                txt_injectNum.setText("4");
                txt_injectVelocity.setText(Float.toString(5950.0f / 600.0f * 2.0f));
                pouchNum = getPouch(volume, 4);
                time_min = getTime(volume, 4);

                if(topigo > under_location)
                    img_location.setBackgroundResource(R.mipmap.center_half);
                else
                    img_location.setBackgroundResource(R.mipmap.center_full);
            }

            time_hour = (int)(time_min/60);
            time_min = time_min - (60*time_hour);


            txt_pouchNum.setText(Integer.toString((int)Math.ceil(pouchNum)));
            txt_time_hour.setText(Integer.toString((int)time_hour));
            txt_time_min.setText(Integer.toString((int)Math.ceil(time_min)));

        }


    }

    private double getVolume(double height, double width, double length){
        return (4.0/3.0)*Math.PI*height*width*length;
    }
    private double getVolume(double width, double length){ // 높이가 입력 되지 않았을 경우
        return (4.0/3.0)*Math.PI*width*length*length;
    }

    private double getPouch(double volume,int injectNum){ //0.1m3, 주입구 1개 기준 파우치 5950개, 20분 소요 , 1분에 0.005m3
        return ((volume * 59500)); // / (double)injectNum;
    }
    private double getTime(double volume, int injectNum){
        return ((volume * 20) / 0.1) / (double)injectNum;
    }

}
