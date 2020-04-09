package com.example.contacttest;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.contacttest.bean.UserOrdinary;


import java.io.*;
import java.net.Socket;

public class Connect  {

    private String ip = "120.79.87.21";
    private int imageUploadPort = 5423;
    private int port = 5422;

    private static String TAG = "Connect";

    private Socket sc = null;
    private OutputStream dout = null;
    private InputStreamReader din = null;

    private JSONObject jsonData = null;

    String replyMessage = "";

    /**
     * 构造函数
     */
    public Connect(){
        try {
            sc = new Socket(ip,port);       //通过socket连接服务器
            din = new InputStreamReader(sc.getInputStream(),"gb2312");
            dout = sc.getOutputStream();
            Log.d(TAG,"connect server successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据至服务器
     * @param message 要发送至服务器的字符串
      */
    public void sendMessage(String message){
        try {
            if(dout != null || message != null){        //判断输出流或者消息是否为空，为空的话会产生nullpoint错误
                message = message + "\n";       //末尾加上换行让服务器端有消息返回
                byte[] me = message.getBytes();
                dout.write(me);
                dout.flush();

            }else{
                Log.d("Connect","The message to be sent is empty");
            }
            Log.d(TAG,"send message successful");
        } catch (IOException e) {
            Log.d(TAG,"send message to server failed");
            e.printStackTrace();
        }
    }

    public String receiveMessage(){
        String message = "";
        try {
            Log.d(TAG,"开始接收服务端信息");
            char[] inMessage = new char[1024];
            int a =din.read(inMessage);     //a存储返回消息的长度
            message = new String(inMessage,0,a);        //必须要用new string来转换
            Log.d("Connect",message);
        } catch (IOException e) {
            Log.d(TAG,"receive message failed");
            e.printStackTrace();
        }
        return message;
    }


    /**
     * 包装search操作类型json数据
     * @param type 操作类型
     * @param source 包含信息的javabean
     * @return 返回source转换的json格式的字符串
     */
    public String search(String type,Object source){
        JSONObject job = null;
        String jstr = null;
        Log.d(TAG,"开始查找编码操作类型");
        switch (type){
            case "login":   //登陆
                Log.d(TAG,"开始编码login内容");
                if(source.getClass() != JSONObject.class){      //判断传入的是否已经是jsonobject类型，是的话直接转为字符串返回，不是的话开始编码
                    job = (JSONObject) JSON.toJSON(source);
                    job.put("login","1");
                    job.put("type","search");
                }else{
                    jstr = JSON.toJSONString(source);
                }

                break;
            case "newMessage":  //新闻信息查询
                break;
            case "PersonMessage":   //个人信息查询
                break;
            case "creditScore": //信誉积分查询
                break;
            case "stall":   //个人车位查询
                break;
            case "help":    //帮助查询
                break;
            default:
                break;
        }
        if (job != null) {
            jstr = job.toJSONString();
            Log.d(TAG,"编码成功，编码结果为："+jstr);
        }
        return jstr;
    }

    /**
     * 登陆方法
     * @param name 用户名
     * @param password  登陆密码
     */
    public void login(String name,String password){
        JSONObject job = new JSONObject();  //创建一个json对象存放login信息
        job.put("name",name);
        job.put("passWord",password);
        job.put("login","1");
        job.put("type","search");

        String Msend = search("login",job); //用本类的search方法将json对象转换为json字符串
        sendMessage(Msend);
        String reply = receiveMessage();    //获取服务器返回信息
        Log.d("Connect","message received"+reply);
        UserOrdinary uo;
        uo = JSON.parseObject(reply,UserOrdinary.class);    //将服务器返回的消息解码为user类
        Log.d(TAG,"user id:"+uo.getIdNum());
        Log.d(TAG,"user password:"+uo.getPassword());
    }


    /**
     * 包装update操作类型json数据
     * 参数用法同search方法
     */
    public String update(String type,Object source){
        JSONObject job = null;
        String jstr = null;
        switch (type){
            case "personMessage":   //修改个人信息
                break;
            case "ediget":  //注册账号
                break;
            case "updataPassword":  //修改密码
                break;
            case "upHeadPortrait":  //修改头像
                break;
            case "publishStall":    //停车位信息插入
                break;
            default:
                break;
        }
        if (job != null) {
            jstr= job.toJSONString();
        }
        return jstr;
    }

    /**
     * 关闭连接
     */
    public void close(){
        try {
            if(din != null){
                din.close();
            }
            if(dout != null){
                dout.close();
            }
            if(sc != null) {
                sc.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.d(TAG,"关闭连接");
    }

}
