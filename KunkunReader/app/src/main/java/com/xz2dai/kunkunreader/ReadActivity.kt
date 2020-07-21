package com.xz2dai.kunkunreader

import android.annotation.SuppressLint
import android.icu.text.Transliterator
import android.opengl.Visibility
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_read.*

/**
 * 阅读activity，指小说阅读界面，主要显示小说内容，点击屏幕左右两边翻页，点击中间呼出菜单
 */
class ReadActivity : AppCompatActivity(),View.OnClickListener, View.OnTouchListener {

    var MenuIsOpen:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_read)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏

        Read_ContentTextView.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v){
            Read_back -> this.finish()
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        var startX:Float = 0f
        var startY:Float = 0f
        var endX:Float = 0f
        var endY:Float = 0f
        when (p1!!.action) {
            MotionEvent.ACTION_DOWN ->{
                startX = p1.x
                startY = p1.y
            }
            MotionEvent.ACTION_MOVE ->{

            }
            MotionEvent.ACTION_UP -> {
                endX = p1.x
                endY = p1.y

                var i = endX - startX

                when(startX){
                    in 0..p0!!.width/3 -> {
                        //在左三分之一，向左翻页
                    }
                    in p0.width*2/3..p0.width ->{
                        //在右三分之一，向右翻页
                    }
                    else ->{
                        //其他位置打开菜单
                        if(MenuIsOpen){
                            Read_TopBar.visibility = View.INVISIBLE
                            MenuIsOpen = false
                        }else{
                            Read_TopBar.visibility = View.VISIBLE
                            MenuIsOpen = true
                        }
                    }

                }
            }
        }
        return true
    }
}