package com.paredgames.aijyakae.data.config

import com.paredgames.aijyakae.data.util.ApiLinks
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    fun getInstance(apiLinks: ApiLinks):Retrofit{
            return Retrofit.Builder()
                .baseUrl(apiLinks.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}