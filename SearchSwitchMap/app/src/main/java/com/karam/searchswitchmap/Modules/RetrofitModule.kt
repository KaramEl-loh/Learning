package com.karam.searchswitchmap.Modules

import com.karam.searchswitchmap.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitModule {


    fun getRetrofitClient():Retrofit {

        val httpLoggingIntereceptor = HttpLoggingInterceptor()
        httpLoggingIntereceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client =  OkHttpClient.Builder().addInterceptor(httpLoggingIntereceptor).build()



        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }





}