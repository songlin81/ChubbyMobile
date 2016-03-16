package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends Activity {

    EditText number, content;
    Button send;
    SmsManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        sManager = SmsManager.getDefault();
        number = (EditText) findViewById(R.id.number);
        content = (EditText) findViewById(R.id.content);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                PendingIntent pi = PendingIntent.getActivity(
                        SendSMSActivity.this, 0, new Intent(), 0);
                sManager.sendTextMessage(number.getText().toString(),
                        null, content.getText().toString(), pi, null);
                Toast.makeText(SendSMSActivity.this, "SMS sent", 8000).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SendSMSActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
