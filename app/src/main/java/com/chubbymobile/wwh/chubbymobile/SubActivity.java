package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class SubActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_main);
        LinearLayout root = (LinearLayout) findViewById(R.id.root);

        //get access to the previous activity value.
        tv = (TextView) findViewById(R.id.tv);
        Intent intent =getIntent();
        String first = intent.getStringExtra("et1");
        tv.setText("Current time:" + String.valueOf(first));

        final DrawView draw = new DrawView(this);
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);
        root.addView(draw);
    }

}
