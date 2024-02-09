package com.example.loginapidemo.di

import android.util.Log
import com.example.loginapidemo.baseresposne.BaseResponse
import retrofit2.Response


open class BaseDataSource {
    open suspend fun <T> getResult(
        isInit: Boolean = false,
        call: suspend () -> Response<T>,
    ): Resource<T & Any> {
        try {
            val response = call()
            Log.e("responseCode", "isSuccessful => ${response.code()}")
            if (response.code() == 403) {
                return Resource.error("Something went wrong, please try after sometime", code = response.code())
            } else if (response.code() == 502) {
                return Resource.error("Something went wrong, please try after sometime", code = response.code())
            } else if (response.code() != 200) {
                return Resource.error(
                    "Something went wrong, please try after sometime",
                    code = response.code()
                )
            } else if (response.body() != null) {
                val baseResponse = (response.body() as BaseResponse)
                Log.e(
                    "responseCode",
                    "is If condition => ${(response.isSuccessful && baseResponse.status!!)}"
                )

                if (response.isSuccessful && baseResponse.status!!) {
                    response.body()?.let {
                        return Resource.success(it, baseResponse.message)
                    }
                } else {
                    if (isInit) {
                        response.body()?.let {
                            return Resource.success(it, baseResponse.message)
                        }
                    } else {
                        val body = response.body()
                        Log.e("response.body", "response.body => ${response.body()}")

                        if (body != null) {
                            Log.e("response.body", "response.bodyyyyy => ${response.body()}")
                            Log.e(
                                "response.body",
                                "response.bodyyyyy11111 => ${baseResponse.message}"
                            )

                            return Resource.error(baseResponse.message!!)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            return Resource.error("Error => ${e.message} ?: ${e.toString()}")
        }
        return Resource.error("Internet Connection Issue")
    }

    /* private fun <T> error(message: String): Resource<T> {
         return .error(message)
     }*/
}