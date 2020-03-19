package com.skywingwang.androidcontroltools.viewmodels

import androidx.lifecycle.MutableLiveData
import com.skywingwang.androidcontroltools.AndroidControlToolsApplication
import com.skywingwang.androidcontroltools.databean.SystemSettingInfo
import com.skywingwang.core.base.BaseViewModel
import com.skywingwang.core.utils.SystemSettingUtils

/**
 * Created by skywingking
 * on 2020-03-18
 */
class SystemSettingViewModel : BaseViewModel(){
    private val systemSettingLiveData:MutableLiveData<SystemSettingInfo> by lazy {
        MutableLiveData<SystemSettingInfo>().also {
            var systemSettingInfo = SystemSettingInfo()
            systemSettingInfo.isBluetoothEnable = SystemSettingUtils.getBluetoothStatus()
            systemSettingInfo.isWifiEnable = SystemSettingUtils.getWifiStatus(AndroidControlToolsApplication.getContext())
            it.value = systemSettingInfo
        }
    }

    fun getSystemSettingInfo():MutableLiveData<SystemSettingInfo>{
        return systemSettingLiveData
    }


    fun openBluetooth(){
        var systemSettingInfo = systemSettingLiveData.value
        if(SystemSettingUtils.openBluetooth())
            systemSettingInfo?.isBluetoothEnable = true
        systemSettingLiveData.value = systemSettingInfo
    }

    fun closeBluetooth(){
        var systemSettingInfo = systemSettingLiveData.value
        if(SystemSettingUtils.closeBluetooth())
            systemSettingInfo?.isBluetoothEnable = false
        systemSettingLiveData.value = systemSettingInfo
    }

    fun openWifi(){
        var systemSettingInfo = systemSettingLiveData.value
        SystemSettingUtils.openWifi(AndroidControlToolsApplication.getContext())
        systemSettingInfo?.isWifiEnable = true
        systemSettingLiveData.value = systemSettingInfo
    }

    fun closeWifi(){
        var systemSettingInfo = systemSettingLiveData.value
        SystemSettingUtils.closeWifi(AndroidControlToolsApplication.getContext())
        systemSettingInfo?.isWifiEnable = false
        systemSettingLiveData.value = systemSettingInfo
    }
}