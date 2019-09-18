package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    //private MyReceiver receiver;
    int level,scale,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        //filter.addAction("android.net.conn.CONNECTIVIY_CHANGE");
        IntentFilter BatteryFliter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intent = getApplicationContext().registerReceiver(null,BatteryFliter);
        if(intent != null){
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);  //电量
            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);  //刻度
            status = intent.getIntExtra(BatteryManager.EXTRA_SCALE,BatteryManager.BATTERY_PROPERTY_STATUS); //充电状态
        }
        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setProgress(level);
        //receiver = new MyReceiver();
        //registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info != null && info.isAvailable()){
                Toast.makeText(context,"有网",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(context,"没网",Toast.LENGTH_LONG).show();
            }
        }

    }
}

