package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class TimerActivity extends Activity {

    private int mInterval = 5000;
    private Handler mHandler;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mHandler = new Handler(){
            public void handleMessage(Message msg) {
                final TextView tv=(TextView)findViewById(R.id.textView5);
                Bundle b=msg.getData();
                if(b.getString("init")!=null){
                    tv.setText(b.getString("init").toString());
                }
                super.handleMessage(msg);
            }
        };

        startRepeatingTask();
    }

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    private void updateStatus() {
        Log.e("value",""+(i++));
        Message m = mHandler.obtainMessage();
        Bundle b=new Bundle();
        b.putString("init", (""+(i++)));
        m.setData(b);
        mHandler.sendMessage(m);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(TimerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}