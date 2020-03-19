package com.skywingwang.androidcontroltools.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val mCheckPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val REQUEST_PERMISSION_CODE = 1001
    private val RQ_WRITE_SETTING = 1002

    private var mRequestPermissions: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = DataBindingUtil.setContentView(this, R.layout.app_activity_main)
        initView()
        checkPermissions()
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

    private fun checkPermissions() {
        mRequestPermissions.clear()
        for(tPermission in mCheckPermissions){
            when (ContextCompat.checkSelfPermission(this,tPermission)) {
                PackageManager.PERMISSION_DENIED ->
                    mRequestPermissions.add(tPermission)
            }
        }
        if(mRequestPermissions.size > 0){
            ActivityCompat.requestPermissions(this, mRequestPermissions.toTypedArray(),REQUEST_PERMISSION_CODE)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.System.canWrite(mContext)){
                val intent = with(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)){
                    data = Uri.parse("package:$packageName")
                    this
                }
                startActivityForResult(intent,RQ_WRITE_SETTING)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_PERMISSION_CODE -> {

            }
        }
    }
}