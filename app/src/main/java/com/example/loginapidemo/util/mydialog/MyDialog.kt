package com.example.loginapidemo.util.mydialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.loginapidemo.R
import com.example.loginapidemo.util.Logger
import com.example.loginapidemo.util.SnackbarUtil
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask

class MyDialog(context: Context) {
    var animationView: LottieAnimationView? = null
    var dialog: Dialog? = null
    var activity: Context? = null
    var progressTimerTask: TimerTask? = null
    init {
        activity = context
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.dialog_loading, null)
        dialog = Dialog(context, 0)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(view)
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
        lp.copyFrom(dialog!!.window?.attributes)
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.attributes = lp
        animationView = dialog!!.findViewById(R.id.progressAnimationView)
    }

    fun show() {
        Logger.logI("Dialog showing ...")
        if (dialog != null && activity != null) {
            try {
                animationView!!.playAnimation()
                dialog!!.show()
                Logger.logI("Dialog show ..... ")

                progressTimerTask = timerTask {
                    if (dialog!!.isShowing) {
                        Logger.logI("Time out ...... ")
                        hide()
                        SnackbarUtil.show(activity as Activity, "Connection time out")
                    }
                }

                Timer().schedule(progressTimerTask,((1 * 60) * 1000).toLong())

            } catch (e: Exception) {
                Logger.logE("showDialog: Exception$e")
            }
        }
    }

    fun hide() {
        Logger.logI("Dialog hiding ... ")
        if (dialog != null && dialog!!.isShowing && activity != null) {
            try {
                if (progressTimerTask != null) {
                    progressTimerTask!!.cancel()
                }
                animationView!!.clearAnimation()
                dialog!!.dismiss()
                Logger.logD("Dialog Hide ..... ")
            } catch (e: Exception) {
                Logger.logE("Exception showDialog $e")
            }
        }
    }
}

