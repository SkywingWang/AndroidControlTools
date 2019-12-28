package com.skywingwang.androidcontroltools;

import com.alibaba.android.arouter.launcher.ARouter;
import com.skywingwang.core.base.BaseApplication;
import com.skywingwang.core.config.AppConfig;

/**
 * created by Sven
 * on 2019-12-28
 */
public class AndroidControlToolsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Arouter初始化
         */
        if(AppConfig.isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
