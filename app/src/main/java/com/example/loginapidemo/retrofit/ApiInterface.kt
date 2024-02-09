package com.example.loginapidemo.retrofit

import com.example.loginapidemo.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface ApiInterface {

    @FormUrlEncoded
    @POST(ApiConstant.login)
    suspend fun login(@FieldMap map: Map<String, String>): Response<LoginResponse>

    @Multipart
    @POST(ApiConstant.editProfile)
    suspend fun editProfile(
        @PartMap paramEditProfile: @JvmSuppressWildcards Map<String, RequestBody>,
        @Part image: MultipartBody.Part? = null,
    ): Response<LoginResponse>

}