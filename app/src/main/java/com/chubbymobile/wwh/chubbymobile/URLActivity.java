package com.chubbymobile.wwh.chubbymobile;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;


public class URLActivity extends Activity {

    ImageView show;
    Bitmap bitmap;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 0x123)
            {
                show.setImageBitmap(bitmap);
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        show = (ImageView) findViewById(R.id.show);
        new Thread()
        {
            public void run()
            {
                try
                {
                    URL url = new URL("http://images.pchome.net/global/img2/copyright2011_cnet.png");
                    InputStream is = url.openStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    handler.sendEmptyMessage(0x123);
                    is.close();
                    is = url.openStream();
                    OutputStream os = openFileOutput("test.png"
                            , MODE_WORLD_READABLE);
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    while((hasRead = is.read(buff)) > 0)
                    {
                        os.write(buff, 0 , hasRead);
                    }
                    is.close();
                    os.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(URLActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
