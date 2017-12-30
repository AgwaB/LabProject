package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
                intent.putExtra("Height",Double.parseDouble(sinkHeight.getText().toString())*0.01);
                intent.putExtra("Width",Double.parseDouble(sinkWidth.getText().toString())*0.01);
                intent.putExtra("Length",Double.parseDouble(sinkLength.getText().toString())*0.01);

                startActivity(intent);
            }
        });

    }
}
