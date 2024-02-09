package com.example.loginapidemo.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    var activity: Activity? = null
    override fun onCreate() {
        super.onCreate()

    }
}