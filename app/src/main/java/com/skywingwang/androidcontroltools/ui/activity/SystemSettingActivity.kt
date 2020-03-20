package com.skywingwang.androidcontroltools.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.skywingwang.androidcontroltools.R
import com.skywingwang.androidcontroltools.databinding.AppActivitySystemSettingBinding
import com.skywingwang.androidcontroltools.viewmodels.SystemSettingViewModel
import com.skywingwang.core.base.BaseActivity
import com.skywingwang.core.config.ARouterPathConfig

/**
 * Created by skywingking
 * on 2020-03-17
 */
@Route(path = ARouterPathConfig.ACTIVITY_URL_APP_SYSTEM_SETTING)
class SystemSettingActivity : BaseActivity() {
    private lateinit var binding: AppActivitySystemSettingBinding
    private lateinit var mContext: Context
    private lateinit var viewModel: SystemSettingViewModel
    private var mBrightness:Int = 0
    private var mVolume:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_system_setting)
        viewModel = ViewModelProviders.of(this)[SystemSettingViewModel::class.java]

        initView()
        initViewForData()
    }

    private fun initView() {
        binding.iTitle.tvTitle.setText(mContext.getString(R.string.app_system_title))
        binding.iTitle.tvBack.setOnClickListener { l -> finish() }
        binding.tvBluetoothSwitch.setOnClickListener { l ->
            if (viewModel.getSystemSettingInfo().value?.isBluetoothEnable!!)
                viewModel.closeBluetooth()
            else
                viewModel.openBluetooth()
        }
        binding.tvWifiSwitch.setOnClickListener { l ->
            if (viewModel.getSystemSettingInfo().value?.isWifiEnable!!)
                viewModel.closeWifi()
            else
                viewModel.openWifi()
        }
        binding.tvBrightnessAdd.setOnClickListener { l -> viewModel.setBrightness(mBrightness + 1)}
        binding.tvBrightnessSub.setOnClickListener { l -> viewModel.setBrightness(mBrightness - 1)}

        binding.tvVolumeAdd.setOnClickListener { l -> viewModel.setVolume(mVolume + 1) }
        binding.tvVolumeSub.setOnClickListener { l -> viewModel.setVolume(mVolume - 1) }
    }

    private fun initViewForData() {
        viewModel.getSystemSettingInfo().observe(this, Observer { systemSettingInfo ->
            if (systemSettingInfo.isBluetoothEnable)
                binding.tvBluetoothSwitch.setText(mContext.getString(R.string.core_on))
            else
                binding.tvBluetoothSwitch.setText(mContext.getString(R.string.core_off))

            if (systemSettingInfo.isWifiEnable)
                binding.tvWifiSwitch.setText(mContext.getString(R.string.core_on))
            else
                binding.tvWifiSwitch.setText(mContext.getString(R.string.core_off))
            mBrightness = systemSettingInfo.brightness
            mVolume = systemSettingInfo.volume
            binding.tvBrightness.setText("${systemSettingInfo.brightness}")
            binding.tvVolume.setText("${systemSettingInfo.volume}")
        })
    }
}