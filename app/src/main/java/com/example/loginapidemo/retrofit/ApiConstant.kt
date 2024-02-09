package com.example.loginapidemo.retrofit

object ApiConstant {

    const val baseUrl = "https://dev.showfaride.com/"
    private const val baseFolderPath = "api/v3/driver_api/"
    const val login = "${baseFolderPath}login"

    const val editProfile = "${baseFolderPath}update_basic_info"
    const val WEB_PARAM_PROFILE_IMAGE = "profile_image"
    const val END_POINT_LOGIN = "login"
    const val USER_NAME = "username"
    const val LAT = "lat"
    const val LNG = "lng"
    const val DRIVERID = "driver_id"
    const val DEVICE_TYPE = "device_type"
    const val DEVICE_TOKEN = "device_token"

    const val LOG_ERROR_TAG = "Showfa_error"
    const val LOG_TAG = "Showfa_Tag"

}
