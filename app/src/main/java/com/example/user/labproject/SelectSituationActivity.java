package com.example.user.labproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by user on 2017-12-29.
 */

public class SelectSituationActivity extends Activity {
    ImageView sink, Void;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        sink = (ImageView)findViewById(R.id.to_activity_sink);
        Void = (ImageView)findViewById(R.id.to_activity_void);


        sink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),VoidEnterActivity.class);
                startActivity(intent);
            }
        });

        Void.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SinkEnterActivity.class);
                startActivity(intent);
            }
        });
    }
}
