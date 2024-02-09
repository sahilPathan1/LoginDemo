package com.example.loginapidemo.util

import android.util.Log
import com.example.loginapidemo.BuildConfig


object PrintLog {
    const val TAG = "Showfa_driver"
    fun v(tag: String?, msg: String? ) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg!!)
        }
    }

    fun d(msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg!!)
        }
    }

    fun d(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg!!)
        }
    }

    fun i(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg!!)
        }
    }

    fun w(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg!!)
        }
    }

    fun e(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg!!)
        }
    }

    fun e(msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg!!)
        }
    }
}