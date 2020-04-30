package com.example.contacttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.contacttest.bean.Bean.NewMessage;
import com.example.contacttest.bean.UserOrdinary;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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


    private static String ip = "120.79.87.21";
    private static int portUpload = 5423;
    private static int port = 5422;

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
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPoolExecutor.execute(testconect);
            }
        });

    }

    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.d(TAG,"请求结果-->" + val);
        }
    };

    Runnable testconect = new Runnable() {
        @Override
        public void run() {

            Connect ct = Connect.getConncet();

            NewMessage newMessage1 = ct.newMessage(1);
            Log.i(TAG,String.valueOf(newMessage1.getMessageId()));
            Log.i(TAG,newMessage1.getMessageTitle());
            Log.i(TAG,newMessage1.getMessagecontent());

        }
    };

}
