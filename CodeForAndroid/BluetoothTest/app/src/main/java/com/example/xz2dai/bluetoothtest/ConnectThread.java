package com.example.xz2dai.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread {

    private static final String TAG = "ConnectThread";
    private final BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    public ConnectThread(BluetoothAdapter bluetoothAdapter,BluetoothDevice bluetoothDevice,String uuid){
        this.mBluetoothAdapter = bluetoothAdapter;
        this.mmDevice = bluetoothDevice;
        BluetoothSocket tmp = null;
        if(mmSocket != null){
            Log.e(TAG,"ConnectThread-->mmSocket != null 先去释放");
            try{
                mmSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Log.e(TAG,"ConnectThread-->mmSocket != null 已释放");
        try{
            tmp = mmDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(uuid));
        }catch (IOException e){
            Log.e(TAG,"ConnectThread-->获取buletoothSocket异常！"+e.getMessage());
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
