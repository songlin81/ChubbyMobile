package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

public class SubActivity extends Activity {

    private TextView tv, preftv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_main);
        LinearLayout root = (LinearLayout) findViewById(R.id.root);

        tv = (TextView) findViewById(R.id.tv);
        Intent intent =getIntent();
        String first = intent.getStringExtra("et1");
        tv.setText("Current time:" + String.valueOf(first));

        SharedPreferences mSharedPreferences = getSharedPreferences("TestSharedPreferences", 0);
        int counter = mSharedPreferences.getInt("counter", 0);
        preftv = (TextView)findViewById(R.id.preferenceValue);
        preftv.setText("This app has been started " + counter + " times.");
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("counter", ++counter);
        mEditor.commit();

        final DrawView draw = new DrawView(this);
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);
        root.addView(draw);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Toast.makeText(SubActivity.this, "选择了--->>" + keyCode, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SubActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            //return false;   //this will stop propagation.
        }
        return super.onKeyDown(keyCode, event);
    }
}