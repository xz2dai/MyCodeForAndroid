package com.xz2dai.kunkunreader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast(msg: CharSequence) {
        showToast(msg)
    }

    override fun onClick(v: View?) {

    }
}