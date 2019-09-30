package com.example.xz2dai.alipaytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2019092267703373";
    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";
    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    public static final String RSA2_PRIVATE = "";

    public static final String ALIPAY_PUBLIC_KEY = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                APPID,
                RSA2_PRIVATE,
                "json",
                "UTF-8",
                ALIPAY_PUBLIC_KEY,
                "RSA2");
    }
}
