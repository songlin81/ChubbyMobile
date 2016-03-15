package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends Activity {

    private static final String ACTIVITY_TAG="LogDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent i = getIntent();
        Person p = i.getParcelableExtra("yes");
        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText(p.name+" "+p.map.size());

        Log.v(QuizActivity.ACTIVITY_TAG, "This is Verbose.");
        Log.d(QuizActivity.ACTIVITY_TAG, "This is Debug.");
        Log.i(QuizActivity.ACTIVITY_TAG, "This is Information");
        Log.w(QuizActivity.ACTIVITY_TAG, "This is Warning.");
        Log.e(QuizActivity.ACTIVITY_TAG, "This is Error.");
    }
}
