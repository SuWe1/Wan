package com.github.wan.app

import android.app.Application
import com.github.wan.other.PreferenceUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceUtils.init(this)
    }
}