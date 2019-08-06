package com.example.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button ShowButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ShowButton = findViewById(R.id.ShowButton1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ShowDialog(){
        AlertDialog dialog = null;

    }
}
