package com.example.xz2dai.viewtest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper vf;
    private GestureDetector gd;
    private Animation in_12r,out_12r,in_r21,out_r21;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vfliper);
        gd = new GestureDetector(this,new MyGDListener());
        vf = findViewById(R.id.vf);
        in_12r = AnimationUtils.loadAnimation(this,R.anim.in_leftright);
        out_12r = AnimationUtils.loadAnimation(this,R.anim.out_leftright);
        in_r21 = AnimationUtils.loadAnimation(this,R.anim.in_rightleft);
        out_r21 = AnimationUtils.loadAnimation(this,R.anim.out_rightleft);
        vf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //vf.showNext();
                return gd.onTouchEvent(event);

            }
        });
    }
    public class MyGDListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(velocityX < -10) {
                vf.setInAnimation(in_r21);
                vf.setOutAnimation(out_r21);
                vf.showNext();
            }else if(velocityX > 10){
                vf.setInAnimation(in_12r);
                vf.setOutAnimation(out_12r);
                vf.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}


