package com.skywingwang.androidcontroltools.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.skywingwang.androidcontroltools.R
import com.skywingwang.core.base.BaseActivity
import com.skywingwang.core.config.ARouterPathConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by skywingking
 * on 2020-03-17
 */
@Route(path = ARouterPathConfig.ACTIVITY_URL_APP_SPLASH)
class SplashActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_splash)

        Observable.interval(1, TimeUnit.SECONDS)
            .take(1)
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{aLong ->
                ARouter.getInstance().build(ARouterPathConfig.ACTIVITY_URL_APP_MAIN).navigation()
                finish()}
    }
}