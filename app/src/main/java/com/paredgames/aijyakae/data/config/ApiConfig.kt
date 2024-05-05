package com.paredgames.aijyakae.data.config

import com.paredgames.aijyakae.data.util.ApiLinks
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    val okHttpClient:OkHttpClient=OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .build()
    fun getInstance(apiLinks: ApiLinks):Retrofit{
            return Retrofit.Builder()
                .baseUrl(apiLinks.url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}