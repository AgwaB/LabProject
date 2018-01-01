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
    EditText topigo, packingThick, crackDeep, voidWidth, voidHeight, voidLength;
    ImageView back, next;
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);

        topigo = (EditText)findViewById(R.id.topigo);
        packingThick = (EditText)findViewById(R.id.packingThick);
        crackDeep = (EditText)findViewById(R.id.crackDeep);
        voidWidth = (EditText)findViewById(R.id.voidWidth);
        voidHeight = (EditText)findViewById(R.id.voidHeight);
        voidLength = (EditText)findViewById(R.id.voidLength);

        back = (ImageView)findViewById(R.id.back);
        next = (ImageView)findViewById(R.id.next);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.stream, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VoidGradeActivity.class);


                if(isNumber(topigo.getText().toString()) && isNumber(packingThick.getText().toString()) && isNumber(crackDeep.getText().toString()) && isNumber(voidWidth.getText().toString()) && isNumber(voidLength.getText().toString())) {
                    if (voidHeight.getText().toString().equals("")){
                        intent.putExtra("voidHeight", "0");
                        intent.putExtra("topigo", topigo.getText().toString());
                        intent.putExtra("packingThick", packingThick.getText().toString());
                        intent.putExtra("crackDeep", crackDeep.getText().toString());
                        intent.putExtra("voidWidth", voidWidth.getText().toString());
                        intent.putExtra("voidLength", voidLength.getText().toString());
                        intent.putExtra("stream", spinner.getSelectedItem().toString());
                        startActivity(intent);
                    } else{
                        if(isNumber(voidHeight.getText().toString())){
                            intent.putExtra("voidHeight", voidHeight.getText().toString());
                            intent.putExtra("topigo", topigo.getText().toString());
                            intent.putExtra("packingThick", packingThick.getText().toString());
                            intent.putExtra("crackDeep", crackDeep.getText().toString());
                            intent.putExtra("voidWidth", voidWidth.getText().toString());
                            intent.putExtra("voidLength", voidLength.getText().toString());
                            intent.putExtra("stream", spinner.getSelectedItem().toString());
                            startActivity(intent);
                        }
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
