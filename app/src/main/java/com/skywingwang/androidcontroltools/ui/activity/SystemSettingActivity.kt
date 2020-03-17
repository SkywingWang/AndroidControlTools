package com.skywingwang.androidcontroltools.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.skywingwang.androidcontroltools.R
import com.skywingwang.androidcontroltools.databinding.AppActivitySystemSettingBinding
import com.skywingwang.core.base.BaseActivity
import com.skywingwang.core.config.ARouterPathConfig

/**
 * Created by skywingking
 * on 2020-03-17
 */
@Route(path = ARouterPathConfig.ACTIVITY_URL_APP_SYSTEM_SETTING)
class SystemSettingActivity:BaseActivity() {
    private lateinit var binding: AppActivitySystemSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_system_setting)
    }
}