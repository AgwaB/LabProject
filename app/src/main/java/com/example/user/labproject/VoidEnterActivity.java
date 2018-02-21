package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by user on 2017-12-30.
 */

public class VoidEnterActivity extends Activity {
    EditText topigo, voidHeight, voidWidth, voidLength, under_location;
    ImageView back, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);

        topigo = (EditText)findViewById(R.id.topigo);
        voidHeight = (EditText)findViewById(R.id.voidHeight);
        voidWidth = (EditText)findViewById(R.id.voidWidth);
        voidLength = (EditText)findViewById(R.id.voidLength);
        under_location = (EditText)findViewById(R.id.under_location);

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


                if(isNumber(topigo.getText().toString())  &&  isNumber(voidWidth.getText().toString()) && isNumber(voidLength.getText().toString()) && isNumber(under_location.getText().toString())) {
                    intent.putExtra("WhereWereYou", "void");
                    intent.putExtra("topigo", Double.parseDouble(topigo.getText().toString()));
                    intent.putExtra("Height", Double.parseDouble(voidLength.getText().toString()));
                    intent.putExtra("Width", Double.parseDouble(voidWidth.getText().toString()));
                    intent.putExtra("Length", Double.parseDouble(voidLength.getText().toString()));
                    intent.putExtra("under_location", Double.parseDouble(under_location.getText().toString()));
                    if(voidHeight.getText().toString().equals("")){
                        intent.putExtra("Height", Double.parseDouble(voidLength.getText().toString())*(0.8));
                    }
                    startActivity(intent);
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
