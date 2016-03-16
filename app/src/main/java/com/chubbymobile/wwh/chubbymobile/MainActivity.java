package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
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

import java.util.jar.Manifest;

public class MainActivity extends Activity {

    int[] images = new int[]{
            R.drawable.angry,
            R.drawable.sad,
            R.drawable.smiley,
            R.drawable.surprise
    };
    int currentImg = 0;

    CharSequence[] items = {"Toronto", "Beijing", "New York"};
    final boolean[] arrayCountry = new boolean[] {true, false, true};

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        LinearLayout main = (LinearLayout) findViewById(R.id.root);


        //1. Display images in rotation.
        final ImageView image = new ImageView(this);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
        main.addView(image);

        //2. Display time.
        Button btn = new Button(this);
        btn.setText(R.string.ok);
        btn.setGravity(Gravity.CENTER);
        final TextView show = new TextView(this);
        show.setGravity(Gravity.CENTER);
        show.setText("Click to display");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("Current time: " + new java.util.Date());
            }
        });
        main.addView(show);
        main.addView(btn);

        //3. Pass data across activities, draw view and keep data in SharedPreferences.
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
                finish();
            }
        });
        main.addView(btnToSub);

        // 4. Dialog
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
                builder.setIcon(R.drawable.angry);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Dialog closed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        main.addView(btnGeneral);

        //5. multiple choice menu
        Button btnListViewMulti = new Button(this);
        btnListViewMulti.setText(R.string.listBtn);
        btnListViewMulti.setGravity(Gravity.CENTER);
        btnListViewMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择城市");
                builder.setMultiChoiceItems(items, arrayCountry, new OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String select_item = items[which].toString();
                        Toast.makeText(MainActivity.this,
                                "选择了--->>" + select_item + " checked: " + isChecked, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
                builder.setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < arrayCountry.length; i++) {
                            if (arrayCountry[i] == true) {
                                stringBuilder.append(items[i] + "、");
                            }
                        }
                        Toast.makeText(MainActivity.this, "->" + stringBuilder, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        main.addView(btnListViewMulti);

        //6. SQLite
        Button btnToDB = new Button(this);
        btnToDB.setText(R.string.SQLiteDB);
        btnToDB.setGravity(Gravity.CENTER);
        btnToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
                finish();
            }
        });
        main.addView(btnToDB);

        //7. WebView with close button
        Button btnToWebView = new Button(this);
        btnToWebView.setText(R.string.goWebView);
        btnToWebView.setGravity(Gravity.CENTER);
        btnToWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
        main.addView(btnToWebView);

        //8. Fragment
        Button btnToFragment = new Button(this);
        btnToFragment.setText(R.string.goFragment);
        btnToFragment.setGravity(Gravity.CENTER);
        btnToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentPage.class);
                startActivity(intent);
                finish();
            }
        });
        main.addView(btnToFragment);

        //9. Image Viewer
        Button btnToImage = new Button(this);
        btnToImage.setText(R.string.goImage);
        btnToImage.setGravity(Gravity.CENTER);
        btnToImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        main.addView(btnToImage);

        //10. Ringtong on.
        Button btnToSound = new Button(this);
        btnToSound.setText(R.string.goSound);
        btnToSound.setGravity(Gravity.CENTER);
        btnToSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mMediaPlayer == null)
                        mMediaPlayer = new MediaPlayer();
                    else
                        mMediaPlayer.reset();
                    Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    mMediaPlayer.setDataSource(MainActivity.this, alert);
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        main.addView(btnToSound);

        //11. Ringtong off.
        Button btnToSoundStop = new Button(this);
        btnToSoundStop.setText(R.string.stopSound);
        btnToSoundStop.setGravity(Gravity.CENTER);
        btnToSoundStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mMediaPlayer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToSoundStop);

        //12. MVP
        Button btnToMVP = new Button(this);
        btnToMVP.setText(R.string.goMVP);
        btnToMVP.setGravity(Gravity.CENTER);
        btnToMVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, MVPActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToMVP);

        //13. GridView
        Button btnToGridView = new Button(this);
        btnToGridView.setText(R.string.goGridView);
        btnToGridView.setGravity(Gravity.CENTER);
        btnToGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToGridView);

        //14. Notification
        Button btnToNotification = new Button(this);
        btnToNotification.setText(R.string.goNotification);
        btnToNotification.setGravity(Gravity.CENTER);
        btnToNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, NotifyActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToNotification);

        //15. GridView menu
        Button btnToGridMenu = new Button(this);
        btnToGridMenu.setText(R.string.goGridMenu);
        btnToGridMenu.setGravity(Gravity.CENTER);
        btnToGridMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, GridViewMenuActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToGridMenu);

        //16. Fragment under bottom switching views.
        Button btnToNavigation = new Button(this);
        btnToNavigation.setText(R.string.goNavigation);
        btnToNavigation.setGravity(Gravity.CENTER);
        btnToNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToNavigation);

        //17. Forced horizontal view
        Button btnToTooth = new Button(this);
        btnToTooth.setText(R.string.goTooth);
        btnToTooth.setGravity(Gravity.CENTER);
        btnToTooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, ToothActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToTooth);

        //18. Render pix from assets
        Button btnToAsset = new Button(this);
        btnToAsset.setText(R.string.goAsset);
        btnToAsset.setGravity(Gravity.CENTER);
        btnToAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, AssetActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToAsset);

        //19. ListView route to sub activity
        Button btnToListView = new Button(this);
        btnToListView.setText(R.string.goListView);
        btnToListView.setGravity(Gravity.CENTER);
        btnToListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToListView);

        //20. Pull to refresh
        Button btnToPull = new Button(this);
        btnToPull.setText(R.string.goPull);
        btnToPull.setGravity(Gravity.CENTER);
        btnToPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, PullActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToPull);

        //21. Send SMS
        Button btnToSendSMS = new Button(this);
        btnToSendSMS.setText(R.string.goSendSMS);
        btnToSendSMS.setGravity(Gravity.CENTER);
        btnToSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, SendSMSActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        main.addView(btnToSendSMS);
    }
}