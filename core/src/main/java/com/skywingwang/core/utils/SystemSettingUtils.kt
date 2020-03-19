package com.skywingwang.core.utils

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.net.wifi.WifiManager
import android.view.WindowManager

/**
 * Created by skywingking
 * on 2020-03-19
 */
object SystemSettingUtils {
    fun getBluetoothStatus():Boolean{
        var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null)
            return false
        return bluetoothAdapter.isEnabled
    }
    fun openBluetooth():Boolean{
        var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null)
            return false
        return bluetoothAdapter.enable()
    }
    fun closeBluetooth():Boolean{
        var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null)
            return false
        return bluetoothAdapter.disable()
    }

    fun getWifiStatus(context:Context):Boolean{
        val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(mWifiManager.isWifiEnabled)
            return true
        else
            return false
    }

    fun openWifi(context: Context){
        val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(!mWifiManager.isWifiEnabled)
            mWifiManager.setWifiEnabled(true)
    }
    fun closeWifi(context: Context){
        val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if(mWifiManager.isWifiEnabled)
            mWifiManager.setWifiEnabled(false)
    }
}