package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 2017-12-30.
 */

public class ResultActivity extends Activity {
    TextView pouchNum, location, injectNum, injectVelocity, time_hour, time_min;
    double volume;
    double height, width, length;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pouchNum = (TextView)findViewById(R.id.pouchNum);
        location = (TextView)findViewById(R.id.location);
        injectNum = (TextView)findViewById(R.id.injectNum);
        injectVelocity = (TextView)findViewById(R.id.injectVelocity);
        time_hour = (TextView)findViewById(R.id.time_hour);
        time_min = (TextView)findViewById(R.id.time_min);

        Intent intent = getIntent();
        height = intent.getDoubleExtra("Height", 0.0);
        width = intent.getDoubleExtra("Width", 0.0);
        length = intent.getDoubleExtra("Length", 0.0);

        volume = getVolume(height,width,length);

        pouchNum.setText(Double.toString(getPouch(volume, 1)));




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
