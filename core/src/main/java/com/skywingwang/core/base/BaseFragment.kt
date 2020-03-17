package com.skywingwang.core.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * created by Skywing
 * on 2020-03-16
 */
class BaseFragment: Fragment() {
    protected lateinit var mContext: Context
    private var mContainerView: View? = null
    private var viewModelProvider: ViewModelProvider? = null


    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelProvider = getViewModelProvider()

    }

    protected fun addFragment(targetFragment:BaseFragment,containerViewId : Int){
        var transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
        if(!targetFragment.isAdded)
            transaction.add(containerViewId,targetFragment,targetFragment.javaClass.name)
        else
            transaction.show(targetFragment)
        transaction.commit()
    }

    protected fun replaceFragment(targetFragment: BaseFragment,containerViewId: Int){
        var transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.replace(containerViewId,targetFragment,targetFragment.javaClass.name)
        transaction.commit()
    }

    protected fun removeFragment(fragment: BaseFragment) {
        var transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }


    /**
     * 创建ViewModel对象
     *
     * @param clazz
     * @param <T>
     * @return
    </T> */
    operator fun <T : ViewModel> get(clazz: Class<T>): T? {
        return if (viewModelProvider == null) null else viewModelProvider?.get(clazz)
    }


    /**
     * 初始化ViewModelProvider对象
     *
     * @return
     */
    private fun getViewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
    }

}