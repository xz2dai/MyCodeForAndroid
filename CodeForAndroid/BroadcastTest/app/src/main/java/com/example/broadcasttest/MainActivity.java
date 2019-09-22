package com.example.broadcasttest;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int level,scale,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CallForBattery();
        CallForNet();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void CallForBattery(){
        IntentFilter BatteryFliter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intent = getApplicationContext().registerReceiver(null,BatteryFliter);
        if(intent != null){
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);  //电量
            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);  //刻度
            status = intent.getIntExtra(BatteryManager.EXTRA_SCALE,BatteryManager.BATTERY_PROPERTY_STATUS); //充电状态
        }
        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setProgress(level);
    }

    public void CallForNet(){
        final TextView text_net = findViewById(R.id.text_net);
        final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        cm.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() {
            @Override
            public void onLost(Network network) {
                super.onLost(network);
                text_net.setText(R.string.net_msg_noNet);
            }

            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                text_net.setText(R.string.net_msg_haveNet);
            }
        });


    }
}

