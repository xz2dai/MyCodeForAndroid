package com.example.contacttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.contacttest.bean.Bean.NewMessage;
import com.example.contacttest.bean.UserOrdinary;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    static boolean logflag;

    private final static String TAG = "Connect";

    public final static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3 + 2);

    Button btn1;
    TextView text1,text2;
    EditText editText1,editText2;
    ImageButton image1;


    private static String ip = "120.79.87.21";
    private static int portUpload = 5423;
    private static int port = 5422;

    Connect ct;

    Bitmap bitmap;

    public void toastText(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void handleException(Exception e, String prefix)
    {
        e.printStackTrace();
        toastText(prefix + e.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ct = Connect.getConncet();
        Connect.Init(this);
        btn1 = findViewById(R.id.btn1);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);
        image1 = findViewById(R.id.image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPoolExecutor.execute(testconect);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ct.upHeadPortrait(bitmap);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {        //程序结束后没消除，可能导致内存泄露
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Bundle data = msg.getData();
                    bitmap = (Bitmap)msg.obj;
                    image1.setImageBitmap(bitmap);
                    break;
                case 2:
                    break;
                default:
                        break;
            }

        }
    };

    Runnable testconect = new Runnable() {
        @Override
        public void run() {


            bitmap = ct.getHeadPortrait();
            Message msg = new Message();
            msg.what = 1;
            msg.obj = bitmap;
            handler.sendMessage(msg);


            /*
            try {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
                File file = new File(getFilesDir()+"/image");
                Log.i(TAG,file.toString());
                if (!file.exists()) {
                    file.mkdirs();
                }
                File f = new File(file, "test.txt");
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(new String("test test test").getBytes());
                fos.flush();
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }

             */
        }
    };

}
