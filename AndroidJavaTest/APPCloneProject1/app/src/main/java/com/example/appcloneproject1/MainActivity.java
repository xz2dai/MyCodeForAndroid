package com.example.appcloneproject1;

import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_backButton:
                finish();
                break;
            case R.id.title_menuButton:
                break;
            case R.id.left_button:
                break;
            case R.id.mid_button:
                break;
            case R.id.right_button:
                break;
            case R.id.searchLine_Button:
                Toast.makeText(MainActivity.this,"开发中......",Toast.LENGTH_SHORT);
            default:
                break;
        }
    }

    private void InitApp(){

    }

    private void LeftButtonClick(){
        Button button = findViewById(R.id.left_button);
        button.setBackgroundColor(Color.parseColor("#4595FC"));
    }
}

