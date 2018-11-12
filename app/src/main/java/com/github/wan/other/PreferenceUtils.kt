package com.github.wan.other

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.text.TextUtils

/**
 * Created by swyww on 2018/11/1
 */

object PreferenceUtils {

    const val USERINFO = "user_info"
    const val ISLOGING = "is_login"

    private lateinit var sApplication: Application

    private lateinit var mPreference: SharedPreferences

    fun init(application: Application) {
        sApplication = application
        mPreference = sApplication.getSharedPreferences("", Context.MODE_PRIVATE)
    }

    fun getString(key: String, defValue: String): String {
        return mPreference.getString(key, defValue)
    }

    fun putString(key: String, value: String) {
        val editor = mPreference.edit()
        editor.putString(key, value)
        fastCommit(editor)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return mPreference.getBoolean(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = mPreference.edit()
        editor.putBoolean(key, value)
        fastCommit(editor)
    }

    fun getLong(key: String, defValue: Long): Long {
        return mPreference.getLong(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        val editor = mPreference.edit()
        editor.putLong(key, value)
        fastCommit(editor)
    }

    fun getStringSet(key: String, defValue: Set<String>): Set<String> {
        return mPreference.getStringSet(key, defValue)
    }

    fun putStringSet(key: String, value: Set<String>) {
        val editor = mPreference.edit()
        editor.putStringSet(key, value)
        fastCommit(editor)
    }

    fun getInt(key: String, defaultVal: Int): Int {
        return mPreference.getInt(key, defaultVal)
    }

    fun putInt(key: String, value: Int) {
        val editor = mPreference.edit()
        editor.putInt(key, value)
        fastCommit(editor)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return mPreference.getFloat(key, defValue)
    }

    fun putFloat(key: String, value: Float) {
        val editor = mPreference.edit()
        editor.putFloat(key, value)
        fastCommit(editor)
    }

    fun remove(key: String) {
        if (!TextUtils.isEmpty(key) && mPreference.contains(key)) {
            val editor = mPreference.edit()
            editor.remove(key)
            fastCommit(editor)
        }
    }

    operator fun contains(key: String): Boolean {
        return mPreference.contains(key)
    }

    fun clearAll() {
        val editor = mPreference.edit()
        editor.clear()
        fastCommit(editor)
    }

    fun getAll(): Map<String, *> {
        return mPreference.all
    }

    private fun fastCommit(editor: SharedPreferences.Editor) {
        // edit.apply could not commit your preferences changes in time on
        // Android 4.3
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            editor.apply()
        } else {
            // FIXME: there's no fast commit below GINGERBREAD.
            editor.commit()
        }
    }


}