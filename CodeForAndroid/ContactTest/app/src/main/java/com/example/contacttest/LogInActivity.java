package com.example.contacttest;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Confirm,btn_Agree,btn_fanhui,btn_Quit;

    EditText et_name,et_password;

    //网络连接操作对象
    Connect ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_login);
        btn_Confirm = findViewById(R.id.Login_ConfirmButton);
        btn_Confirm.setOnClickListener(AgreeButtonListener);
        btn_fanhui = findViewById(R.id.Login_fanhui);
        et_name = findViewById(R.id.Login_name);
        et_password = findViewById(R.id.Login_password);
        //ct = MainActivity       //引用主方法中的网络连接操作对象
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Login_fanhui: {
                finish();
                break;
            }
            default:
                break;
        }
    }

    View.OnClickListener AgreeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    ct.login(et_name.getText().toString(),et_password.getText().toString());
                }
            });
        }
    };

    }


