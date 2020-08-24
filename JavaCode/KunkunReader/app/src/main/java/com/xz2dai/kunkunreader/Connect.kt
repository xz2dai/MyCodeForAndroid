package com.xz2dai.kunkunreader

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import java.io.*
import java.net.Socket

class Connect
/**
 * 构造函数
 */
private constructor() {
    //服务端地址及接口
    private val ip = "120.79.87.21"
    private val imageUploadPort = 5423 //图片交互接口
    private val port = 5422 //普通交互接口

    //普通数据交互接口
    private var sc: Socket? = null

    //图片交互接口
    private var ImageSocket: Socket? = null

    //普通交互流
    private var dout: OutputStream? = null
    private var din: InputStreamReader? = null

    //图片交互流
    private var imageInputStream: InputStream? = null
    private var imageFileOutputSteam: DataOutputStream? = null

    //已连接标记
    var isConnect = false
    var ImageConncet = false

    /**
     * 初始化普通交互连接
     */
    private fun InitConnect() {
        try {
            sc = Socket(ip, port) //通过socket连接服务器
            din = InputStreamReader(sc!!.getInputStream(), "gb2312")
            dout = sc!!.getOutputStream()
            sc!!.soTimeout = 10000
            if (sc != null && din != null && dout != null) {
                isConnect = true
                Log.i(TAG, "connect server successful")
            } else {
                Toast.makeText(context,"连接服务器失败",Toast.LENGTH_SHORT).show()
                Log.i(TAG, "connect server failed,now retry...")
                InitConnect()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 初始化图片上传接口
     */
    fun InitImageIO() {
        try {
            ImageSocket = Socket(ip, imageUploadPort)
            ImageSocket!!.soTimeout = 10000
            if (ImageSocket!!.isConnected) {      //判断是否已连接
                imageFileOutputSteam = DataOutputStream(ImageSocket!!.getOutputStream())
                if (imageFileOutputSteam != null) {
                    imageInputStream = ImageSocket!!.getInputStream()
                    if (imageInputStream != null) {
                        ImageConncet = true
                        Log.i(TAG, "ImageIO Ready")
                    }
                } else {
                    Log.i(TAG, "imageOutputStream is null")
                }
            } else {
                Log.i(TAG, "image socket init failed")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun CloseImageIO() {
        if (ImageSocket == null) {
            return
        }
        if (ImageSocket!!.isConnected) {
            try {
                ImageSocket!!.close()
                if (imageFileOutputSteam != null) imageFileOutputSteam!!.close()
                if (imageInputStream != null) imageInputStream!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (ImageSocket!!.isClosed) {
            ImageConncet = false
            Log.i(TAG, "ImageUpLoad Close")
        }
    }

    /**
     * 上传图片
     * 上传图片前必须传输用户信息，必须包含有name
     */
    fun UploadImage(image_toSend: Bitmap): Boolean {
        try {
            InitImageIO() //收发图片接口用完即关
            if (ImageConncet) {
                Log.i(TAG, "start send image")
                image_toSend.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    imageFileOutputSteam
                ) //把图片按参数压缩后压入输出流
                imageFileOutputSteam!!.flush()
                Thread.sleep(1000) //休眠一下线程等待传输完成
                Log.i(TAG, "send image successful")
                CloseImageIO()
                return true
            } else {
                Log.i(TAG, "Init imageIO failed")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.i(TAG, "send image failed")
        return false
    }

    /**
     * 图片输入
     * @return 返回收到的bitmap图片
     */
    fun ReceiveImage(): Bitmap? {
        var image_receive: Bitmap? = null
        try {
            InitImageIO()
            if (ImageConncet) {
                try {
                    Thread.sleep(2000) //发送获取头像申请后要等待服务端上传图片
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                Log.i(TAG, "start receive image message")
                image_receive = BitmapFactory.decodeStream(imageInputStream) //从流中获取图片
                if (image_receive != null) Log.i(
                    TAG,
                    "receive image successful"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        CloseImageIO()
        return image_receive
    }

    /**
     * 发送数据至服务器
     * @param message 要发送至服务器的字符串
     */
    fun sendMessage(message: String?) {
        var message = message
        InitConnect() //发消息的时候连接服务器，收到消息后关闭
        try {
            if (isConnect) {
                if (dout != null && message != null) {        //判断输出流或者消息是否为空，为空的话会产生nullpoint错误
                    message = """
                        $message

                        """.trimIndent() //末尾加上换行让服务器端有消息返回
                    val me = message.toByteArray()
                    dout!!.write(me)
                    dout!!.flush()
                } else {
                    Log.d(
                        "Connect",
                        "The message to be sent is empty or have no connect"
                    )
                }
                Log.i(TAG, "send message successful")
            } else {
                Toast.makeText(context,"连接服务器失败",Toast.LENGTH_SHORT).show()
                Log.i(TAG, "no connect to send message")
            }
        } catch (e: IOException) {
            Log.i(TAG, "send message to server failed")
            e.printStackTrace()
        }
    }

    fun receiveMessage(): String? {
        var message: String? = ""
        try {
            if (isConnect) {
                Log.i(TAG, "开始接收服务端信息")
                val inMessage = CharArray(1024)
                val a = din!!.read(inMessage) //a存储返回消息的长度
                if (a <= -1) {
                    return null
                }
                Log.i(TAG, "reply length:$a")
                message = String(inMessage, 0, a) //必须要用new string来转换
                Log.i("Connect", message)
            } else {
                Toast.makeText(context,"连接服务器失败",Toast.LENGTH_SHORT).show()
                Log.i(TAG, "no connect to receive message")
            }
        } catch (e: IOException) {
            Log.i(TAG, "receive message failed")
            e.printStackTrace()
        }
        CloseConnect() //发消息的时候连接服务器，收到消息后关闭
        return message
    }


    /**
     * 关闭连接
     */
    fun CloseConnect() {
        try {
            if (din != null) {
                din!!.close()
            }
            if (dout != null) {
                dout!!.close()
            }
            if (sc != null) {
                sc!!.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        isConnect = false
        Log.i(TAG, "关闭连接")
    }

    companion object {
        const val TAG = "Connect" //网络连接调试TAG

        //传入一个context对象用于相关操作，一般都是主activity
        private var context: Context? = null

        //数据持久化操作
        private var sp_user: SharedPreferences? = null
        private var editor_user: Editor? = null

        //单例模式获取对象
        val conncet = Connect() //单例模式，装载类时强制初始化

        fun Init(context: Context) {
            SetContext(context)
            SetUserSharePreference(
                context.getSharedPreferences(
                    "user",
                    Context.MODE_PRIVATE
                )
            )
        }

        /**
         * 传入sharedpreference对象，用于保存用户数据
         */
        fun SetUserSharePreference(sharedPreferences: SharedPreferences?) {
            if (sharedPreferences != null) {
                sp_user = sharedPreferences
                editor_user = sp_user!!.edit()
            } else {
                Log.i(TAG, "传入的sharedPreferences为空")
            }
        }

        /**
         *
         */
        fun SetContext(ct: Context?) {
            context = ct
        }
    }
}