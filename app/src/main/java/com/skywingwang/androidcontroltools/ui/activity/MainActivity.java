package com.skywingwang.androidcontroltools.ui.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skywingwang.androidcontroltools.R;
import com.skywingwang.androidcontroltools.databinding.AppActivityMainBinding;
import com.skywingwang.core.base.BaseActivity;
import com.skywingwang.core.config.ARouterPathConfig;

/**
 * created by Sven
 * on 2019-12-28
 */

@Route(path = ARouterPathConfig.ACTIVITY_URL_APP_MAIN)
public class MainActivity extends BaseActivity {
    private Context mContext;
    private AppActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_main);
    }
}
