package com.example.loginapidemo.di

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? = "",
    val code: Int = 200,
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NO_INTERNET_CONNECTION,
        UNKNOWN,
        SHIMMER_VIEW
    }

    companion object {
        fun <T> success(data: T, message: String? = ""): Resource<T> {
            return Resource(Status.SUCCESS, data, message)
        }

        fun <T> error(message: String, data: T? = null, code: Int = 200): Resource<T> {
            return Resource(Status.ERROR, data, message, code)
        }

        fun <T : Any> loading(data: T? = null): Resource<T> {

            return Resource(Status.LOADING, data, null)
        }

        fun <T> shimmer(data: T? = null): Resource<T> {

            return Resource(Status.SHIMMER_VIEW, data, null)
        }

        fun <T> unknown(data: T? = null): Resource<T> {
            return Resource(Status.UNKNOWN, data, null)
        }

        fun <T> noInternetConnection(data: T? = null): Resource<T> {
            return Resource(Status.NO_INTERNET_CONNECTION, null)
        }

    }
}