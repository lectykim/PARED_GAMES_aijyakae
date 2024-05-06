package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import retrofit2.Response

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
            return responseData
        }else{
            val errorMessage = response.errorBody()?.string()
            Log.e("API Error",errorMessage?:"Unknown error")
            return null
        }
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

    fun downloadImage(uri:String,title:String){
        imageDownloadManager.downloadImage(Uri.parse(uri),title)
    }

    fun addAd(){
        val adRequest=AdRequest.Builder().build()

        RewardedAd.load(context,BuildConfig.AD_UNIT_ID,adRequest,object:RewardedAdLoadCallback(){
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