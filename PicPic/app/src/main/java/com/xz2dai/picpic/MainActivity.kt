package com.xz2dai.picpic

import PixivDownloader
import android.graphics.Bitmap
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(),View.OnClickListener  {

    val pixivDownloader:PixivDownloader = PixivDownloader()
    var reSize:Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_getIMG.setOnClickListener(this)
        btn_changeLocal.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_getIMG -> getImg()
            btn_changeLocal -> changeLocal()
            imageview ->{
                if (!reSize){
                    //初始处于缩放状态，点击后放大到全屏
                }else{
                    //处于放大状态，缩放图片
                }

            }
        }
    }

    private fun changeLocal() {

    }

    fun getImg(){
        var pvid:String
        var bitmap:Bitmap
        if(edittext.text!=null){
            pvid = edittext.text.toString()
            thread {
                bitmap = pixivDownloader.GetIMG(pvid)       //获取图片
                runOnUiThread(Runnable {        //修改View需要在创建view的线程中进行
                    imageview.setImageBitmap(bitmap)
                })
            }
        }else{
            Toast.makeText(this, "please input a pvid", Toast.LENGTH_SHORT).show()
        }
    }

}