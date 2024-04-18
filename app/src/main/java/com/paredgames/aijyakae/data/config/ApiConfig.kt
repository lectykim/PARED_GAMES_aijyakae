package com.paredgames.aijyakae.data.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private var instance:Retrofit?=null
    private const val TEXTWTWOIMG_BASE_URL = "https://modelslab.com"

    //SingleTon
    fun getInstance():Retrofit{
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl(TEXTWTWOIMG_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }
}