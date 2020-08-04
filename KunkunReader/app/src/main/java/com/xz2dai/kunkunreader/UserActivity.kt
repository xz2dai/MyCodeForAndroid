package com.xz2dai.kunkunreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window

class UserActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏
        setContentView(R.layout.activity_user)
    }

    override fun onClick(v: View?) {

    }
}