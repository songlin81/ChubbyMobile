package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    int[] images = new int[]{
            R.drawable.angry,
            R.drawable.sad,
            R.drawable.smiley,
            R.drawable.surprise
    };
    int currentImg = 0;
    CharSequence[] items = {"Toronto", "Beijing", "New York"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btn = new Button(this);
        btn.setText(R.string.ok);
        btn.setGravity(Gravity.CENTER);
        final TextView show = new TextView(this);
        show.setGravity(Gravity.CENTER);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("Current time: " + new java.util.Date());
            }
        });

        setContentView(R.layout.content_main);
        LinearLayout main = (LinearLayout) findViewById(R.id.root);

        final ImageView image = new ImageView(this);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg % images.length]);
            }
        });

        Button btnToSub = new Button(this);
        btnToSub.setText(R.string.goDraw);
        btnToSub.setGravity(Gravity.CENTER);
        btnToSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("Current time: " + new java.util.Date());
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                
                intent.putExtra("et1", show.getText());
                startActivity(intent);
            }
        });

        Button btnGeneral = new Button(this);
        btnGeneral.setText(R.string.promptBtn);
        btnGeneral.setGravity(Gravity.CENTER);
        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this);
                builder.setTitle("Warning");
                builder.setMessage("Time to move on！");
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        Button btnListViewMulti = new Button(this);
        btnListViewMulti.setText(R.string.listBtn);
        btnListViewMulti.setGravity(Gravity.CENTER);
        btnListViewMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this);
                builder.setTitle("请选择城市");

                builder.setMultiChoiceItems(items, new boolean[]{true, false,
                        true}, new OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        String select_item = items[which].toString();
                        Toast.makeText(MainActivity.this,
                                "选择了--->>" + select_item, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
                builder.setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        Button btnToDB = new Button(this);
        btnToDB.setText(R.string.goDraw);
        btnToDB.setGravity(Gravity.CENTER);
        btnToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        main.addView(image);
        main.addView(show);
        main.addView(btn);
        main.addView(btnToSub);
        main.addView(btnGeneral);
        main.addView(btnListViewMulti);
        main.addView(btnToDB);
    }
}