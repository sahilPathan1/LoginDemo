package com.example.loginapidemo.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.loginapidemo.R
import com.google.android.material.snackbar.Snackbar

object SnackbarUtil {
    fun show(activity: Activity?, msg: String, duration: Int = Snackbar.LENGTH_LONG) {

        fun getColor(color: Int, context: Context) = ContextCompat.getColor(context, color)
        if (activity != null) {
//            Snackbar.make(
//                activity.window.decorView.findViewById(android.R.id.content), validateString(msg),
//                Snackbar.LENGTH_LONG
//            ).show()

            if (msg.isEmpty().not()) {
                val snackbar = Snackbar.make(
                    activity.window.decorView.findViewById(android.R.id.content),
                    validateString(msg),
                    duration
                )
                val snackbarView: View = snackbar.view
                // styling for background of snackbar
                snackbarView.setBackgroundColor(getColor(R.color.black, activity))

                // styling for text color
                snackbar.setTextColor(getColor(R.color.white, activity))

                //styling for action of text
                snackbar.setActionTextColor(getColor(R.color.white, activity))
                snackbar.show()
                Logger.logI("Snackbar Message :- $msg")
            }
        } else {
            Toast.makeText(activity, validateString(msg), Toast.LENGTH_LONG).show()
        }


    }

//    /*
//    * if you are passing context from some where then it will be show toast because snackbar can show only for activities and view
//    * */
//    fun show(activity: Context?, msg: String) {
//
//        if (activity != null) {
//            if (activity is Activity) {
//                show(activity, msg)
//            } else {
//                Toast.makeText(activity, validateString(msg), Toast.LENGTH_LONG).show()
//            }
//        }
//    }

    // for activity and action
    fun show(
        activity: Activity?,
        msg: String,
        actionText: String,
        clickListener: View.OnClickListener
    ) {
        if (activity != null && msg.isEmpty().not()) {
            Snackbar.make(
                activity.window.decorView.findViewById(android.R.id.content),
                validateString(msg), Snackbar.LENGTH_LONG
            ).setAction(actionText, clickListener).show()
        }
    }

    // for activity and action
    fun show(
        activity: Activity?,
        msg: String,
        actionText: String,
        clickListener: View.OnClickListener,
        duration: Int = Snackbar.LENGTH_LONG
    ) {
        if (activity != null && msg.isEmpty().not()) {
            Snackbar.make(
                activity.window.decorView.findViewById(android.R.id.content),
                validateString(msg),
                duration
            )
                .setAction(actionText, clickListener).show()
        }
    }


    // for view and action
    fun show(view: View?, msg: String, actionText: String, clickListener: View.OnClickListener) {
        if (view != null && msg.isEmpty().not()) {
            Snackbar.make(view, validateString(msg), Snackbar.LENGTH_LONG)
                .setAction(actionText, clickListener).show()
        }
    }

    // for styling view and action color action
    fun show(
        view: View?, viewBgColor: Int, snackBarMsg: String, actionTextColor: Int,
        actionText: String, clickListener: View.OnClickListener,
    ) {
        if (view != null && snackBarMsg.isEmpty().not()) {
            val snackbar = Snackbar.make(view, validateString(snackBarMsg), Snackbar.LENGTH_LONG)
            val snackbarView: View = snackbar.view
            // styling for background of snackbar
            snackbarView.setBackgroundColor(viewBgColor)
            //styling for action of text
            snackbar.setActionTextColor(actionTextColor)
            snackbar.setAction(actionText, clickListener).show()
        }
    }

    private fun validateString(msg: String?): String {
        return msg ?: ""
    }
}