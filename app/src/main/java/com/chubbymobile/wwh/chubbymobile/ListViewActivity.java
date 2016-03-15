package com.chubbymobile.wwh.chubbymobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

public class ListViewActivity extends ListActivity {

    String classes[] = { "Quiz Trivia", "Sign A New User", "Friend List",
            "Download A File", "Upload A File", "Select Pdf files", "Memory Game",
            "Dzidza Maths", "Write Exam" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(ListViewActivity.this,
                android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        if (position == 0) {
            Intent intent = new Intent();
            Person p = new Person();
            p.map = new HashMap<String,String>();
            p.map.put("yes", "ido");
            p.name="ok";
            intent.putExtra("yes", p);
            intent.setClass(this, QuizActivity.class);
            startActivity(intent);
        }
        else if (position == 1) {
            Intent intent = new Intent(ListViewActivity.this, SignUp.class);
            startActivity(intent);
        }
        else if (position == 2) {
            Intent intent = new Intent(ListViewActivity.this, FriendList.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ListViewActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
