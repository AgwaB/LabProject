package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by user on 2017-12-30.
 */

public class SinkEnterActivity extends Activity {
    EditText sinkHeight, sinkWidth, sinkLength;
    ImageView back, next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink);

        sinkHeight = (EditText)findViewById(R.id.sinkHeight);
        sinkWidth = (EditText)findViewById(R.id.sinkWidth);
        sinkLength = (EditText)findViewById(R.id.sinkLength);

        sinkHeight.setSelection(sinkHeight.length());
        sinkWidth.setSelection(sinkWidth.length());
        sinkLength.setSelection(sinkLength.length());

        back = (ImageView)findViewById(R.id.back);
        next = (ImageView)findViewById(R.id.next);


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

                if(isNumber(sinkWidth.getText().toString()) && isNumber(sinkLength.getText().toString())) {
                    intent.putExtra("WhereWereYou", "sink");


                    if (sinkHeight.getText().toString().equals("")){
                        intent.putExtra("Height", 0);
                        intent.putExtra("Width", Double.parseDouble(sinkWidth.getText().toString()) * 0.01);
                        intent.putExtra("Length", Double.parseDouble(sinkLength.getText().toString()) * 0.01);
                        startActivity(intent);
                    } else {
                        if(isNumber(sinkHeight.getText().toString())){
                        intent.putExtra("Height", Double.parseDouble(sinkHeight.getText().toString()) * 0.01);
                        intent.putExtra("Width", Double.parseDouble(sinkWidth.getText().toString()) * 0.01);
                        intent.putExtra("Length", Double.parseDouble(sinkLength.getText().toString()) * 0.01);
                        startActivity(intent);}
                    }

                }
            }
        });

    }

    private boolean isNumber(String str){
        boolean result = false;


        try{
            Double.parseDouble(str) ;
            result = true ;
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "값을 올바르게 입력해 주십시오.", Toast.LENGTH_SHORT).show();
        }


        return result ;
    }
    }
