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

public class MainActivity extends Activity {

    int[] images = new int[]{
            R.drawable.angry,
            R.drawable.sad,
            R.drawable.smiley,
            R.drawable.surprise
    };
    int currentImg = 0;
    CharSequence[] items = {"Toronto", "Beijing", "New York"};
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        LinearLayout main = (LinearLayout) findViewById(R.id.root);

        //1. click event registered to image view.
        final ImageView image = new ImageView(this);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
        main.addView(image);

        //2. click event registered to button.
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

        //3.
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
                    //final AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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

        main.addView(btnToSub);
        main.addView(btnGeneral);
        main.addView(btnListViewMulti);
        main.addView(btnToDB);
        main.addView(btnToWebView);
        main.addView(btnToFragment);
        main.addView(btnToImage);
        main.addView(btnToSound);
        main.addView(btnToSoundStop);
        main.addView(btnToMVP);
        main.addView(btnToGridView);
        main.addView(btnToNotification);
        main.addView(btnToGridMenu);

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
    }
}