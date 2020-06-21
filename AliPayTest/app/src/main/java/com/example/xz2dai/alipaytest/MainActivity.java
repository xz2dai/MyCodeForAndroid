package com.example.xz2dai.alipaytest;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alipay.sdk.app.*;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button paybutton;
    EditText editText;
    AliPay aliPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);              //沙箱环境
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aliPay = new AliPay(MainActivity.this);
        paybutton = findViewById(R.id.payButton);
        editText =findViewById(R.id.editText);
        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPay.payV2(editText.getText().toString());
            }
        });
    }


}
