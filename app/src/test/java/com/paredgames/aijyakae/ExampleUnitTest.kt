package com.paredgames.aijyakae

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Test

import org.junit.Assert.*
import java.io.IOException
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun base64_test(){
        getImgForUrl("https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/temp/0-66161cd4-9d03-4204-8f56-77591c4a1331.base64");
    }
    fun getImgForUrl(url:String): ByteArray? {

        val url =  URL(url);
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        var isSuccess:Boolean=false
        var body: ByteArray? =null
        runBlocking {
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    isSuccess=response.isSuccessful
                    body=response.body()!!.string().toByteArray()
                    println("Response Call")
                    println(response.body()!!.string())
                }
            })
        }

        if(isSuccess)
            return body
        else
            return null


    }
}