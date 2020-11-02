package com.xz2dai.kunkunreader

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

/**
 * @author  袁铨
 * @date    2020-7-30
 * 一个基于安卓平台的读书App
 */


class MainActivity : AppCompatActivity(),View.OnClickListener {

    //线程池
    val threadPoolExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3 + 2)

    val bookshelf:RecyclerView = Main_bookShelf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏
        setContentView(R.layout.activity_main)

        Main_AddLocal.setOnClickListener(this)
        Main_Config.setOnClickListener(this)
        Main_ToInfo.setOnClickListener(this)
        Main_ToShop.setOnClickListener(this)

    }

    fun showToast(msg: CharSequence) {
        showToast(msg)
    }

    override fun onClick(v: View?) {
        when(v){
            Main_AddLocal -> {
                showToast("功能开发中")
            }
            Main_Config -> {
                showToast("功能开发中")
            }
            Main_ToInfo -> {
                startActivity(Intent(this,UserActivity::class.java))
            }
            Main_ToShop -> {
                startActivity(Intent(this,ShopActivity::class.java))
            }
        }
    }
}