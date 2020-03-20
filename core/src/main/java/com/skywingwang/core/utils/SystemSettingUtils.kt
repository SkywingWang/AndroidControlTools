package com.skywingwang.core.utils

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.media.AudioManager
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

    /**
     * 范围 0～100
     */
    fun getSystemVolume(context: Context):Int{
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM)
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / maxVolume
    }

    /**
     * 加音量
     */
    fun addSystemVolume(context: Context){
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,0)
    }

    /**
     * 减音量
     */
    fun subSystemVolume(context: Context){
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,0)
    }

    /**
     * 范围 0～100
     */
    fun setSystemVolume(context: Context,tVolume:Int){
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val vol = Math.ceil(tVolume * maxVolume * 0.01).toInt()
        if(vol <= 0)
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0)
        else if(vol >= 100)
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,0)
        else
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,vol,0)
    }
}