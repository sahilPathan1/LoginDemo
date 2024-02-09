package com.example.loginapidemo.retrofit


import android.app.Application
import android.content.Context
import com.example.loginapidemo.di.BaseDataSource
import com.example.loginapidemo.retrofit.ApiConstant.baseUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        var context: Context? = null
        val httpClient = HttpLoggingInterceptor()
        httpClient.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okhttp = OkHttpClient.Builder()
        okhttp.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES)
        okhttp.addNetworkInterceptor(Interceptor { chain ->

            chain.proceed(chain.request().newBuilder().also {
               /* it.addHeader("key", "DPS\$951")*/
                it.addHeader("x-api-key", "s0scww44wwgw880go4sgs8cgw8ckg8g8o8c48kww")
            }.build())
        })
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okhttp.build()).build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

/*    @Provides
    @Singleton
    fun provideMyDataStore(context: Context): MyDataStore {
        return MyDataStore(context)
    }*/

    @Provides
    @Singleton
    fun providesBaseDataSource(): BaseDataSource {
        return BaseDataSource()
    }


    /*@Provides
    @Singleton
    fun provideUserPref(sharedPreferences: SharedPreferences, context: Context): UserPreference {
        return UserPreference(sharedPreferences, context)
    }*/
}
