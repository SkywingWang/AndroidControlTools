package com.skywingwang.core.utils

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.net.wifi.WifiManager
import android.provider.Settings
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

    /**
     * 亮度范围 0～255
     */
    fun getSystemBrightness(context: Context):Int{
        try{
            return Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        }catch (e: Settings.SettingNotFoundException){
            e.printStackTrace()
            return -1
        }
    }


    /**
     * 亮度范围 0～255
     */
    fun setSystemBrightness(context: Context,brightness:Int){
        val uri = Settings.Secure.getUriFor(Settings.System.SCREEN_BRIGHTNESS)
        Settings.System.putInt(context.contentResolver,Settings.System.SCREEN_BRIGHTNESS,brightness)
    }
}