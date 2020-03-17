package com.skywingwang.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

/**
 * created by Skywing
 * on 2020-03-16
 */
abstract class BaseActivity :FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    protected fun addFragment(targetFragment:BaseFragment,containerViewId : Int){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        if(!targetFragment.isAdded)
            transaction.add(containerViewId,targetFragment,targetFragment.javaClass.name)
        else
            transaction.show(targetFragment)
        transaction.commit()
    }

    protected fun replaceFragment(targetFragment: BaseFragment,containerViewId: Int){
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if(!targetFragment.isAdded)
            transaction.replace(containerViewId,targetFragment,targetFragment.javaClass.name)
        else
            transaction.show(targetFragment)
        transaction.commit()
    }
}