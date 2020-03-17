package com.skywingwang.core.base

import android.content.Context
import android.os.Handler
import androidx.multidex.MultiDexApplication
import kotlin.properties.Delegates

/**
 * created by Sven
 * on 2020-03-16
 */
open class BaseApplication : MultiDexApplication(){
    open var mHandler: Handler? = null
    open var mMainThreadId: Int = 0

    override fun onCreate() {
        super.onCreate()
        mContext = this
        instance = this
        mHandler = Handler()
        //3、获取主线程的线程id
        mMainThreadId = android.os.Process.myTid()
    }

    companion object{
        var instance : BaseApplication by Delegates.notNull()
            private set

        var mContext : Context?= null
        fun getContext():Context{
            return mContext!!
        }
    }
}