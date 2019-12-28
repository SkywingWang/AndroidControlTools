package com.skywingwang.core.base;

import android.app.Application;
import android.content.Context;

/**
 * created by Sven
 * on 2019-12-28
 */
public class BaseApplication extends Application {
    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
