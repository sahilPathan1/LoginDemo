package com.example.loginapidemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapidemo.di.BaseDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.intellij.lang.annotations.Language
import java.lang.ref.WeakReference
import javax.inject.Inject

open class BaseViewModel<N> : ViewModel() {

/*    lateinit var mNavigator:WeakReference<N>

    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator:N) {
        mNavigator=WeakReference(navigator)
    }*/

    lateinit var mNavigator: WeakReference<N>



    fun setLanguageObject(lng: Language) {
        language = lng
    }

    private var gson: Gson? = null
    public var language: Language? = null

    @Inject
    lateinit var baseDataSource: BaseDataSource

  /*  @Inject
    lateinit var baseDataSource: BaseDataSource

    @Inject
    lateinit var dataStore: MyDataStore
*/
  /*  private val validationObserver = MutableLiveData<ValidationStatus>()

    open fun getValidationStatus(): MutableLiveData<ValidationStatus> {
        return validationObserver
    }*/

    private val goBack = MutableLiveData<String>()

    fun getGoBackStatus(): MutableLiveData<String> {
        return goBack
    }
    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }
    protected open fun <T> getStringFromObject(requestObject: T): Map<String, String> {
        gson = Gson()
        val jsonString: String = gson!!.toJson(requestObject)
        val mapType = object : TypeToken<Map<String?, String?>?>() {}.type
        return gson!!.fromJson(jsonString, mapType)
    }

    open fun createPartFromString(param: String): RequestBody {
        return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), param)
    }

/*    open fun preventMultipleClick(view: View) {
        view.disabled()

        Handler(Looper.getMainLooper()).postDelayed({
            view.enabled()
        }, 1000)
    }*/
}