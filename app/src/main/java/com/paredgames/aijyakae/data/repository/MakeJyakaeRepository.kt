package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.paredgames.aijyakae.BuildConfig
import com.paredgames.aijyakae.MainActivity
import com.paredgames.aijyakae.data.api.DeepLApiService
import com.paredgames.aijyakae.data.api.ModelsLabApiService
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.dto.TranslateRequestDTO
import com.paredgames.aijyakae.data.dto.TranslateResponseDTO
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.URL

class MakeJyakaeRepository (
    private val modelsLabApiService: ModelsLabApiService,
    private val deepLApiService: DeepLApiService,
    private val context:Context,
    private val imageDownloadManager: ImageDownloadManager,
    private val mainActivity: MainActivity
) {
    private var sharedPreferences: SharedPreferences

    init{
        sharedPreferences=context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY_BEFORE_LOGIN,Context.MODE_PRIVATE)
    }

    suspend fun getTextTwoImg(makeJyakaeContent: MutableStateFlow<MakeJyakaeContent>):TextTwoImageResponseDTO?{
        val textTwoImageRequestDTO = makeJyakaeContent.value.toDto()
        runBlocking {
            val promptResponse=translatePrompt(textTwoImageRequestDTO.prompt)
            val prompt=promptResponse?.translations?.get(0)
            if (prompt != null) {
                Log.d("DeepL Api Response",prompt.text)
                textTwoImageRequestDTO.prompt=prompt.text
            }
        }

        val response: Response<TextTwoImageResponseDTO> =modelsLabApiService.textTwoImg(textTwoImageRequestDTO)

        if(response.isSuccessful){
            val responseData = response.body();
            Log.d("API Response",responseData.toString())

            var base64Array=getImgForUrl(responseData!!.output[0])
            Log.d("Base64 Img",base64Array.toString())
            //cdn이 응답하지 않을 경우를 대처
            //하지만 만약, 초당 5request를 넘어서 응답하지 않는 것이라면?
            //이 부분의 응답은 processing일 것이며,
            //리팩토링 필요
            base64Array = changeBase64Array(base64Array);

            if(base64Array==null){
                Log.e("Image Load Error","because cdn response is null")
            }


            val bitmap: Bitmap? = base64Array?.let {
                BitmapFactory.decodeByteArray(base64Array,0,
                    it.size )
            }
            if (bitmap != null) {
                responseData.base64Img=bitmap
            }
            return responseData
        }else{
            val errorMessage = response.errorBody()?.string()
            Log.e("API Error",errorMessage?:"Unknown error")
            return null
        }
    }
    private suspend fun changeBase64Array(base64Array:ByteArray?):ByteArray?{
        var res:ByteArray? = null
        //10번 반복
        for(i:Int in 1..10){
            delay(2000L);
            try{
                res = Base64.decode(base64Array,Base64.DEFAULT);
            } catch (e: IllegalArgumentException){
                e.printStackTrace()
                continue
            }
            return res;
        }
        return null
    }
    private fun getImgForUrl(url:String): ByteArray? {

        val url =  URL(url);
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val response=okHttpClient.newCall(request).execute()

        return response.body()!!.bytes()
    }

    private suspend fun translatePrompt(prompt:String): TranslateResponseDTO?{
        val translateRequestDTO = TranslateRequestDTO()
        translateRequestDTO.text= arrayOf(prompt)
        val response:Response<TranslateResponseDTO> = deepLApiService.translate("DeepL-Auth-Key "+BuildConfig.DEEPL_AUTH_KEY,"application/json",translateRequestDTO)

        if(response.isSuccessful){
            val responseData = response.body();
            Log.d("API Response",responseData.toString())
            return responseData
        }else{
            val errorMessage = response.errorBody()?.string()
            Log.e("API Error",errorMessage?:"Unknown error")
            return null
        }
    }

    fun saveData(key:String,value: String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getData(key:String,defaultValue:String):String{
        return sharedPreferences.getString(key,defaultValue)?:defaultValue
    }

    fun downloadImage(bitmap: Bitmap,title:String){
        imageDownloadManager.downloadImage(bitmap,title)
    }

    fun addAd(){
        val adRequest=AdRequest.Builder().build()

        RewardedAd.load(context,BuildConfig.AD_UNIT_ID_MOVIE,adRequest,object:RewardedAdLoadCallback(){
            override fun onAdLoaded(ad: RewardedAd) {
                Log.d("MainActivity", "Ad was loaded.")

                ad?.show(mainActivity, OnUserEarnedRewardListener {rewardItem->
                    // Handle the reward.

                })
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d("MainActivity", it) }


            }
        })
    }

}