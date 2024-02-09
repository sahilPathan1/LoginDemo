package com.example.loginapidemo.util

import android.util.Log
import com.example.loginapidemo.retrofit.ApiConstant
import java.util.Date

object Logger {
    fun logD(message: String?) = Log.d(ApiConstant.LOG_TAG, message!!)

    fun logD(message: String, flag: Int) = Log.d(ApiConstant.LOG_TAG, "$flag-> $message")

    fun logI(message: String?) = Log.i(ApiConstant.LOG_ERROR_TAG, Date().toString() + " " + message!!)

    fun logE(message: String?) = Log.e(ApiConstant.LOG_ERROR_TAG, Date().toString() + " " + message!!)

    fun logE(e: Exception) = Log.e(ApiConstant.LOG_ERROR_TAG, Date().toString() + " " + e.message, e)

    fun logE(message: String?, e: Exception) = Log.e(ApiConstant.LOG_ERROR_TAG, "$message :-  ${e.message}", e)

}