package com.example.loginapidemo.base


import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.loginapidemo.datastore.MyDataStore
import com.example.loginapidemo.util.SnackbarUtil
import com.example.loginapidemo.util.connection.ConnectionLiveData
import com.example.loginapidemo.util.connection.TAG
import com.example.loginapidemo.util.mydialog.MyDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.net.URISyntaxException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject


@Suppress("DEPRECATION")
abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {
    abstract val layoutId: Int
    lateinit var activity: Activity
    abstract val bindingVariable: Int
 /*   lateinit var dataStore: MyDataStore*/

    lateinit var baseActivity: BaseActivity<T, V>
    lateinit var myDialog: MyDialog
    lateinit var dataStore: MyDataStore

    abstract fun setUpObserver()
  /*  lateinit var myDialog: MyDialog*/
    lateinit var dialog: ProgressDialog
    val RQ_CODE_CAMERA = 100
    val RQ_CODE_LOCATION = 101
    var isInternetConnected: Boolean = true

    private lateinit var connectionLiveData: ConnectionLiveData
    val bundle = Bundle()

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var mViewModel: V
    lateinit var binding: T


    /*   @Inject
       lateinit var userPreference: UserPreference*/

    @Inject
    lateinit var appContextModule: Context

  /*  lateinit var fragManager: FragmentManager*/

 /*   private lateinit var connectionLiveData: ConnectionLiveData*/

    companion object {

        var deviceToken: String? = ""
        var userId: String? = ""

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.TRANSPARENT)
        performDataBinding()
        setAnimation()
       /* getDeviceToken()*/
        baseActivity = this
     /*   fragManager = supportFragmentManager*/
  /*      dataStore = MyDataStore(applicationContext)*/
        dialog = ProgressDialog(this)
        myDialog = MyDialog(this)
        dataStore = MyDataStore(applicationContext)
       /* myDialog = MyDialog(this)*/

        try {
            connectionLiveData = ConnectionLiveData(this)
            connectionLiveData.observe(this) { isNetworkAvailable ->
                if (!isNetworkAvailable) {
                    SnackbarUtil.show(
                        this,
                      "no internet connection",
                        Snackbar.LENGTH_LONG
                    )
                } else {
                    dialog.hide()
                }
            }
        } catch (e: URISyntaxException) {
            e.localizedMessage?.let { Log.e(TAG, it) }
        }
    }

/*    fun getDeviceToken() {
        try {
            FirebaseApp.initializeApp(this)
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (!it.isSuccessful) {
                    Logger.logE("getInstanceId failed", it.exception!!)
                    return@addOnCompleteListener
                }
                deviceToken = it.result.toString()
                lifecycleScope.launch {
                    dataStore.setFcmToken(deviceToken!!)
                }
                Log.e("Tag", "Device Token :- $deviceToken")
            }
        } catch (e: Exception) {
            Logger.logE("getInstanceId failed", e)
        }
    }*/


    private fun performDataBinding() {
        activity = this
        binding = DataBindingUtil.setContentView(activity, layoutId)
        binding.setVariable(bindingVariable, mViewModel)
        binding.executePendingBindings()
        setUpObserver()
    }
    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    fun getColors(id: Int): Int {
        return ContextCompat.getColor(activity, id)
    }

    open fun setAnimation() {
        val slide = Slide()
        slide.slideEdge = Gravity.LEFT
        slide.duration = 400
        slide.interpolator = AccelerateDecelerateInterpolator()
        window.exitTransition = slide
        window.enterTransition = slide
    }

    open fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.packageManager.getPackageInfo(
                pContext.packageName, PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.e("printHashKey() Hash Key:", hashKey)
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("printHashKey()", e.toString())
        } catch (e: java.lang.Exception) {
            Log.e("printHashKey()", e.toString())
        }
    }

    fun finishClick(v: View) = finish()

    private fun setStatusBarColor(color: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (color != Color.WHITE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (color == Color.TRANSPARENT) {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        window.statusBarColor = color
    }


    fun navigatePage(activity: Activity, bundle: Bundle, isFinish: Boolean = false) {
        val intent = Intent()
    }

  /*  override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransition(R.anim.left_in, R.anim.right_out)
        CommonFun.hideKeyboard(this)
    }*/


    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?) =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED

    open fun hideKeyboard() {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }


    open fun walletClick(view: View) {}

}