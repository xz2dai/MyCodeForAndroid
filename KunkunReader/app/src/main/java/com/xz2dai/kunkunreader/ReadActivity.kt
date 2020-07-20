package com.xz2dai.kunkunreader

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ReadActivity : AppCompatActivity(),View.OnClickListener {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_read)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏



    }

    override fun onClick(v: View?) {

    }
}