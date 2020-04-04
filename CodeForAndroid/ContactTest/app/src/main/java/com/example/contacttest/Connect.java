package com.example.contacttest;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.contacttest.bean.UserOrdinary;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connect implements Runnable {

    private static String TAG = "Connect";

    Socket sc = null;
    private BufferedWriter dout = null;
    private BufferedReader din = null;
    private JSONObject jsonData = null;

    /**
     * 网络连接方法
     * 构造函数
     * @param ip 服务器ip地址
     * @param port 连接端口
     */
    public Connect(String ip,int port){
        try {
            sc = new Socket(ip,port);
            din = new BufferedReader(new InputStreamReader(sc.getInputStream(), StandardCharsets.UTF_8));
            dout = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream(), StandardCharsets.UTF_8));
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
            if(dout != null || message != null){
                dout.write(message+"\n");
                dout.flush();
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
            message = din.readLine();
        } catch (IOException e) {
            Log.d(TAG,"receive message failed");
            e.printStackTrace();
        }
        return message;
    }

    //获取服务器返回数据操作类型
    //4.4：这段代码完全由服务端代码复制而来，需要修改
    public String getDisposeType(String message)  {
        String type = "";
        if(message == null || message.equals("")){
            return "null";
        }else{
            jsonData = JSON.parseObject(message);
            type = String.valueOf(jsonData.get("type"));
        }
        return type;
    }

    //根据服务端返回数据操作类型进行具体操作
    public Object disposeOperation(String message) {
        if(message == null || message.equals("")){
            Log.d(TAG,"message is empty!");
            return null;
        }
        String type = getDisposeType(message);
        JSONObject jdata = null;
        Object bufferpack = null;
        Log.d(TAG,"get message:"+message);
        Log.d(TAG,"type:"+type);
        if(type != null) {
            switch (type) {
                case "search":{
                    /**
                     * 查询操作
                     * 进行判断查询类型
                     * 根据json数据中的其他数据进行查询
                     */
                    //判断登陆操作
                    if(jsonData.get("login") != null) {
                        //调用登陆验证方法
                        jdata = JSONObject.parseObject(message);
                        bufferpack = JSON.toJavaObject(jdata, UserOrdinary.class);
                    }
                    //查询信息新闻显示
                    if(jsonData.get("newMessage") != null) {
                        //调用新闻信息查询
                    }
                    //查询个人信息
                    if(jsonData.get("personMessage") != null) {
                        //调用个人信息查询
                    }
                    //信誉积分
                    if(jsonData.get("creditScore") != null) {
                        //调用信誉积分查询
                    }
                    //个人车位查询
                    if(jsonData.get("stall") != null) {
                        //调用个人车位信息查询
                    }
                    //帮助页面查询
                    if(jsonData.get("help") != null) {
                        //调用帮助查询
                    }
                    break;
                }
                case "updata":{
                    /**
                     * 修改数据操作
                     * 根据json数据中的其他数据进行修改
                     */
                    //修改个人信息
                    if(jsonData.get("personMessage") != null) {
                        //调用修改个人信息的方法
                    }
                    //注册账号
                    if(jsonData.get("ediget") != null) {
                        //调用注册账号
                    }
                    //修改密码
                    if(jsonData.get("updataPassWord") != null) {
                        //调用修改密码
                    }
                    //修改头像
                    if(jsonData.get("upHeadPortrait") != null) {
                        //调用修改头像
                    }
                    //发布停车位
                    if(jsonData.get("publishStall") != null) {
                        //调用停车位信息插入方法
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
        return bufferpack;
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
                job = (JSONObject) JSON.toJSON(source);
                job.put("login","1");
                job.put("type","search");
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
        }else {
            Log.d(TAG,"编码失败，jsonobject为空");
        }
        return jstr;
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

    @Override
    public void run() {
        UserOrdinary tp = new UserOrdinary();
        tp.setId(10086);
        tp.setPassword("qwer10086");

        sendMessage(search("login",tp));
        String rec;
        rec = receiveMessage();
        Log.d(TAG,"received server message:"+rec);
        tp = (UserOrdinary)disposeOperation(rec);
        Log.d(TAG,"UserID:"+tp.getId());
        Log.d(TAG,"PassWord:"+tp.getPassword());
        Log.d(TAG,"AccountNum:"+tp.getAccountNum());
        close();
    }
}