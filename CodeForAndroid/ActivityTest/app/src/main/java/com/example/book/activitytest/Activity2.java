package com.example.book.activitytest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button SwitchButton1 = findViewById(R.id.SwitchButton1);
        Button SwitchButton2 = findViewById(R.id.SwitchButton2);
        Button SwitchButton3 = findViewById(R.id.SwitchButton3);

        SwitchButton1.setOnClickListener(l);
        SwitchButton2.setOnClickListener(l);
        SwitchButton3.setOnClickListener(l);
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;
            switch (v.getId()){
                case R.id.SwitchButton1:
                    f = new FragmentTest1();
                    break;
                case R.id.SwitchButton2:
                    f = new FragmentTest2();
                    break;
                case R.id.SwitchButton3:
                    f = new FragmentTest2();
                    break;
                default:
                        break;
            }
            ft.replace(R.id.frg,f);
            ft.commit();
        }
    };
}
