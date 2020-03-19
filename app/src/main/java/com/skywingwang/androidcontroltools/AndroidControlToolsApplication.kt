package com.skywingwang.androidcontroltools

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.skywingwang.core.base.BaseApplication
import com.skywingwang.core.config.AppConfig
import kotlin.properties.Delegates

/**
 * Created by skywingking
 * on 2020-03-17
 */

open class AndroidControlToolsApplication:BaseApplication(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = this

        if (AppConfig.isDebug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        //阿里ARouter框架
        ARouter.init(this)

    }

    companion object{
        var instance : BaseApplication by Delegates.notNull()
            private set
        var mContext : Context?= null
        fun getContext(): Context {
            return mContext!!
        }
    }

}