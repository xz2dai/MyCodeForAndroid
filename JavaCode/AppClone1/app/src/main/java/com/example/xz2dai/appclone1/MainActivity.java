package com.example.xz2dai.appclone1;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        InitButtons();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_backButton:
                finish();
                break;
            case R.id.title_menuButton:
                MenuBottonClick();
                break;
            case R.id.left_button:
                LeftButtonClick();
                break;
            case R.id.mid_button:
                MidButtonClick();
                break;
            case R.id.right_button:
                RightButtonClick();
                break;
            case R.id.searchLine_Button:
                Toast.makeText(MainActivity.this,"开发中......",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(MainActivity.this,"退出登录",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void InitButtons(){
        Button title_backButton,title_menuButton,left_button,mid_button,right_button;
        title_backButton = findViewById(R.id.title_backButton);
        title_backButton.setOnClickListener(this);
        title_menuButton = findViewById(R.id.title_menuButton);
        title_menuButton.setOnClickListener(this);
        left_button = findViewById(R.id.left_button);
        left_button.setOnClickListener(this);
        mid_button = findViewById(R.id.mid_button);
        mid_button.setOnClickListener(this);
        right_button = findViewById(R.id.right_button);
        right_button.setOnClickListener(this);
    }

    private void LeftButtonClick(){
        Button L_button = findViewById(R.id.left_button);
        Button M_button = findViewById(R.id.mid_button);
        Button R_button = findViewById(R.id.right_button);
        L_button.setBackgroundColor(Color.parseColor("#4595FC"));
        M_button.setBackgroundColor(Color.parseColor("#67A6F7"));
        R_button.setBackgroundColor(Color.parseColor("#67A6F7"));
    }
    private void MidButtonClick(){
        Button L_button = findViewById(R.id.left_button);
        Button M_button = findViewById(R.id.mid_button);
        Button R_button = findViewById(R.id.right_button);
        M_button.setBackgroundColor(Color.parseColor("#4595FC"));
        L_button.setBackgroundColor(Color.parseColor("#67A6F7"));
        R_button.setBackgroundColor(Color.parseColor("#67A6F7"));
    }

    private void RightButtonClick(){
        Button L_button = findViewById(R.id.left_button);
        Button M_button = findViewById(R.id.mid_button);
        Button R_button = findViewById(R.id.right_button);
        R_button.setBackgroundColor(Color.parseColor("#4595FC"));
        M_button.setBackgroundColor(Color.parseColor("#67A6F7"));
        L_button.setBackgroundColor(Color.parseColor("#67A6F7"));
    }

    private void MenuBottonClick(){
        Button button = findViewById(R.id.title_menuButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
}
