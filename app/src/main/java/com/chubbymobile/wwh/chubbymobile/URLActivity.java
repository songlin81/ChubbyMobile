package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static android.util.Base64.encodeToString;

public class URLActivity extends Activity {

    ImageView show;
    Bitmap bitmap;
    private String result = "";

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

    Button send;
    public void send() {
        String target = "https://chubbyng-songlin81.c9users.io/products/";
        URL url;
        try {
            url = new URL(target);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false); // 禁止缓存
            urlConn.setInstanceFollowRedirects(true);   //自动执行HTTP重定向
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());

            Resources r=this.getResources();
            Bitmap bm=BitmapFactory.decodeResource(r, R.drawable.angry);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String decodedString= Base64.encodeToString(b, 1);
            String param = //"nickname="
                    //+ URLEncoder.encode("testName", "utf-8")
                    //+ "&content="
                    //+ URLEncoder.encode("testContent", "utf-8")
                    "image=" + URLEncoder.encode(decodedString, "utf-8");
            out.writeBytes(param);
            out.flush();
            out.close();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
                BufferedReader buffer = new BufferedReader(in);
                String inputLine = null;
                while ((inputLine = buffer.readLine()) != null) {
                    result += inputLine + "\n";
                }
                in.close();
            }
            urlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread(new Runnable() {
                    public void run() {
                        send();
                        Message m = handler.obtainMessage();
                        handler.sendMessage(m);
                    }
                }).start();
                Toast.makeText(URLActivity.this, "image sent", 8000).show();
            }
        });

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
                    OutputStream os = openFileOutput("test.png", MODE_WORLD_READABLE);
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

        takePhotoBn = (Button) findViewById(R.id.button2);
        showImage = (ImageView) findViewById(R.id.show);
        takePhotoBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //图片名称 时间命名
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date(System.currentTimeMillis());
                filename = format.format(date);
                //创建File对象用于存储拍照的图片 SD卡根目录
                //File outputImage = new File(Environment.getExternalStorageDirectory(),test.jpg);
                //存储至DCIM文件夹
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File outputImage = new File(path, filename +".jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将File对象转换为Uri并启动照相程序
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //照相
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
                startActivityForResult(intent, TAKE_PHOTO); //启动照相
                //拍完照startActivityForResult() 结果返回onActivityResult()函数
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(URLActivity.this, "ActivityResult resultCode error", Toast.LENGTH_SHORT).show();
            return;
        }
        switch(requestCode) {
            case TAKE_PHOTO:
                Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
                intent.setDataAndType(imageUri, "image");
        intent.putExtra("scale", true);
        //设置宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        Toast.makeText(URLActivity.this, "剪裁图片", Toast.LENGTH_SHORT).show();
        //广播刷新相册
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(imageUri);
        this.sendBroadcast(intentBc);
        startActivityForResult(intent, CROP_PHOTO); //设置裁剪参数显示图片至ImageView
        break;
    case CROP_PHOTO:
        try {
            //图片解析成Bitmap对象
            final Bitmap bitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(imageUri));
            Toast.makeText(URLActivity.this, imageUri.toString(), Toast.LENGTH_SHORT).show();
            showImage.setImageBitmap(bitmap); //将剪裁后照片显示出来

            new Thread(new Runnable() {
                public void run() {
                    send2(bitmap);
                    Message m = handler.obtainMessage();
                    handler.sendMessage(m);
                }
            }).start();
            Toast.makeText(URLActivity.this, "image sent", 8000).show();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        break;
        default:
            break;
        }
    }

    public void send2(Bitmap bmIn) {
        String target = "https://chubbyng-songlin81.c9users.io/products/";
        URL url;
        try {
            url = new URL(target);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false); // 禁止缓存
            urlConn.setInstanceFollowRedirects(true);   //自动执行HTTP重定向
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());

            //Resources r=this.getResources();
            Bitmap bm=bmIn;//BitmapFactory.decodeResource(r, R.drawable.angry);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String decodedString= Base64.encodeToString(b, 1);
            String param = //"nickname="
                    //+ URLEncoder.encode("testName", "utf-8")
                    //+ "&content="
                    //+ URLEncoder.encode("testContent", "utf-8")
                    "image=" + URLEncoder.encode(decodedString, "utf-8");
            out.writeBytes(param);
            out.flush();
            out.close();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
                BufferedReader buffer = new BufferedReader(in);
                String inputLine = null;
                while ((inputLine = buffer.readLine()) != null) {
                    result += inputLine + "\n";
                }
                in.close();
            }
            urlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Button takePhotoBn;
    private ImageView showImage;
    private Uri imageUri;
    private String filename;
}
