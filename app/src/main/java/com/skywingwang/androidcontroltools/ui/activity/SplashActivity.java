package com.skywingwang.androidcontroltools.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.skywingwang.androidcontroltools.R;
import com.skywingwang.androidcontroltools.databinding.AppActivitySplashBinding;
import com.skywingwang.core.base.BaseActivity;
import com.skywingwang.core.config.ARouterPathConfig;

/**
 * created by Sven
 * on 2019-12-27
 */
public class SplashActivity extends BaseActivity {
    private Context mContext;
    private AppActivitySplashBinding binding;
    private final int START_DELAY_TIME = 3000;  //启动页面延迟跳转时长，单位ms

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_splash);
        mContext = this;
        startJumpDelay();
    }

    // 启动页播放动画,延迟跳转到MainActivity
    private void startJumpDelay() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(ARouterPathConfig.ACTIVITY_URL_APP_MAIN).navigation();
                finish();
            }
        }, START_DELAY_TIME);
    }
}
