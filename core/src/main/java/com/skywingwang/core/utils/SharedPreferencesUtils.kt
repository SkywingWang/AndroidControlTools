package com.skywingwang.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.skywingwang.core.base.BaseApplication
import java.io.*
import java.lang.Exception
import java.util.*
import kotlin.reflect.KProperty

class SharedPreferencesUtils{
    private val preferences: SharedPreferences by lazy {
        BaseApplication.getContext().getSharedPreferences(
            "Android_Control_Tools",
            Context.MODE_PRIVATE
        )
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        val edit: SharedPreferences.Editor = preferences.edit()
        edit.putBoolean(key, value)
        edit.commit()
    }

    fun getString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }

    fun setString(key: String, value: String) {
        val edit: SharedPreferences.Editor = preferences.edit()
        edit.putString(key, value)
        edit.commit()
    }

    fun getInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun setInt(key: String, value: Int) {
        val edit: SharedPreferences.Editor = preferences.edit()
        edit.putInt(key, value)
        edit.commit()
    }

    fun getLong(key: String, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun setLong(key: String, value: Long) {
        val edit: SharedPreferences.Editor = preferences.edit()
        edit.putLong(key, value)
        edit.commit()
    }

    fun getFloat(key: String, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    fun setFloat(key: String, value: Float) {
        val edit: SharedPreferences.Editor = preferences.edit()
        edit.putFloat(key, value)
        edit.commit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun writeObject(key: String, value: Object) {
        var bAOS: ByteArrayOutputStream = ByteArrayOutputStream()
        try {
            var oOS: ObjectOutputStream = ObjectOutputStream(bAOS)
            oOS.writeObject(value)
            oOS.close()
            bAOS.flush()
            bAOS.close()
            var base64Str: String = Base64.getEncoder().encodeToString(bAOS.toByteArray())
            preferences.edit().putString(key, base64Str).commit()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readObject(key: String): Object? {
        var obj: Object? = null
        var value: String? = preferences.getString(key,"")
        try {
            var buffer: ByteArray? = Base64.getDecoder().decode(value)
            var bAIS:ByteArrayInputStream = ByteArrayInputStream(buffer)
            var oIS :ObjectInputStream = ObjectInputStream(bAIS)
            obj = oIS.readObject() as Object
            oIS.close()
            bAIS.close()
        }catch (e:Exception){
            e.printStackTrace()

        }
        return obj
    }
}