package com.skywingwang.androidcontroltools.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.skywingwang.androidcontroltools.R
import com.skywingwang.androidcontroltools.databinding.AppActivityMainBinding
import com.skywingwang.core.base.BaseActivity
import com.skywingwang.core.config.ARouterPathConfig

/**
 * Created by skywingking
 * on 2020-03-17
 */
@Route(path = ARouterPathConfig.ACTIVITY_URL_APP_MAIN)
class MainActivity : BaseActivity() {
    private lateinit var binding: AppActivityMainBinding
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_main)
        initView()
    }

    private fun initView() {
        binding.iTitle.tvBack.visibility = View.INVISIBLE
        binding.iTitle.tvTitle.setText(mContext.getString(R.string.app_main_title))
        binding.appTvSeekbar.setOnClickListener { l -> }
        binding.appTvSystemSetting.setOnClickListener { l ->
            ARouter.getInstance().build(ARouterPathConfig.ACTIVITY_URL_APP_SYSTEM_SETTING)
                .navigation()
        }
    }
}