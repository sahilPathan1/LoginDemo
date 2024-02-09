package com.example.loginapidemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapidemo.base.BaseViewModel
import com.example.loginapidemo.di.Resource
import com.example.loginapidemo.response.LoginResponse
import com.example.loginapidemo.retrofit.ApiConstant
import com.example.loginapidemo.retrofit.ApiInterface
import com.example.loginapidemo.util.PrintLog
import com.example.loginapidemo.util.RealPathUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File
import java.net.ConnectException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    var context: Context,
    var networkService: ApiInterface,
) : BaseViewModel<Any>() {

    var userProfile: Uri? = null
    private val loginObserver: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getLoginObserver(): LiveData<Resource<LoginResponse>> {
        return loginObserver
    }

    fun loginApi(number: String, pass: String) {
        if (NetworkUtils.isNetworkConnected(context)) {
            var response: Response<LoginResponse>

            val loginRequest: MutableMap<String, String> = HashMap()
            loginRequest["username"] = number
            loginRequest["lat"] = "72.6523252"
            loginRequest["lng"] = "3.565652"
            loginRequest["device_type"] = "android"
            loginRequest["device_token"] = "64546546464646465465464"

            viewModelScope.launch {
                loginObserver.value = Resource.loading(null)
                withContext(Dispatchers.IO) {
                    response = networkService.login(loginRequest)
                    Log.e("loginResponse------------------>", "$response")
                }

                withContext(Dispatchers.Main) {
                    response.run {
                        loginObserver.value = baseDataSource.getResult { this }
                    }
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    loginObserver.value = Resource.noInternetConnection(null)
                }
            }
        }
    }


    /**edit profile */

    private val profileInfoObserver: MutableLiveData<Resource<LoginResponse>> =
        MutableLiveData()

    fun getProfileInfoObservable(): LiveData<Resource<LoginResponse>> {
        return profileInfoObserver
    }

    private fun getMultipartImage(imagePath: Uri?, context: Context): MultipartBody.Part {
        var documentImage: File? = null
        var requestImageFile: RequestBody? = null
        if (RealPathUtil.getRealPath(context, imagePath!!) != null) {
            documentImage = File(RealPathUtil.getRealPath(context, imagePath)!!)
            requestImageFile =
                documentImage.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        }

        return MultipartBody.Part.createFormData(
            ApiConstant.WEB_PARAM_PROFILE_IMAGE,
            documentImage?.name,
            requestImageFile!!
        )
    }

    private fun getRequestBody(name: String): RequestBody {
        return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), name)
    }

    @SuppressLint("SuspiciousIndentation")
    fun profileInfo() {
        if (NetworkUtils.isNetworkConnected(context)) {
            profileInfoObserver.value = Resource.loading()
            lateinit var response: Response<LoginResponse>
            val profileInfoRequestMap = HashMap<String, RequestBody>()
            var image: MultipartBody.Part? = null
            if (userProfile != null) {

                image =
                    getMultipartImage(
                        userProfile!!,
                        (context as ContextWrapper).baseContext
                    )
            }
            profileInfoRequestMap[ApiConstant.DRIVERID] = getRequestBody("3288")
            profileInfoRequestMap["car_type"] = getRequestBody("own")
            profileInfoRequestMap["first_name"] = getRequestBody("sahil")
            profileInfoRequestMap["last_name"] = getRequestBody("pathan")
            profileInfoRequestMap["gender"] = getRequestBody("male")
            profileInfoRequestMap["payment_method"] = getRequestBody("card")
            profileInfoRequestMap["address"] = getRequestBody("Kilifi")
            profileInfoRequestMap["dob"] = getRequestBody("05-02-2000")
            profileInfoRequestMap["mobile_no"] = getRequestBody("8206515808")
            profileInfoRequestMap["email"] = getRequestBody("sp8805481@gmail.com")
            profileInfoRequestMap["owner_name"] = getRequestBody("sk")
            profileInfoRequestMap["owner_email"] = getRequestBody("sk@gmail.com")
            profileInfoRequestMap["owner_mobile_no"] = getRequestBody("8200615808")
            Log.e("TAG", "profileInfoRequestMap => $profileInfoRequestMap")

            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        response =
                            networkService.editProfile(profileInfoRequestMap, image)
                    }
                    PrintLog.e(PrintLog.TAG, "$response")


                    withContext(Dispatchers.Main) {
                        response.run {
                            profileInfoObserver.value = baseDataSource.getResult(true) { this }
                            PrintLog.e(
                                PrintLog.TAG,
                                "${baseDataSource.getResult(true) { this }}"
                            )
                        }
                    }
                } catch (e: ConnectException) {
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            profileInfoObserver.value = Resource(
                                Resource.Status.UNKNOWN,
                                null,
                                e.localizedMessage?.toString().plus("- " + e.cause),
                                502
                            )
                        }
                    }
                } catch (e: Exception) {
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            profileInfoObserver.value = Resource(
                                Resource.Status.UNKNOWN,
                                null,
                                e.localizedMessage?.toString().plus("- " + e.cause),
                                500
                            )
                        }
                    }
                }
            }
        }
    }
}