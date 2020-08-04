package com.xz2dai.kunkunreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import org.json.JSONObject

class ShopActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //取消标题栏
        setContentView(R.layout.activity_shop)
    }

    override fun onClick(v: View?) {

    }

    fun getContent():String{
        var reply:String = ""
        var jobj = JSONObject()
        jobj.put("type","search")
        jobj.put("getBookContent","1")
        jobj.put("bookNum","1")
        jobj.put("chapterNum","1")


        return reply
    }
}