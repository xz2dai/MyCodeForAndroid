package com.xz2dai.kunkunreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window

class UserActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏
    }

    override fun onClick(v: View?) {

    }
}