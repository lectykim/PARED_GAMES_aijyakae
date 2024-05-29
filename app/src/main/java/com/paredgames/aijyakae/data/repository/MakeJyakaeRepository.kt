package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.billingclient.api.BillingClient
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.paredgames.aijyakae.BuildConfig
import com.paredgames.aijyakae.MainActivity
import com.paredgames.aijyakae.data.api.AijyakaeServerApiService
import com.paredgames.aijyakae.data.api.DeepLApiService
import com.paredgames.aijyakae.data.api.ModelsLabApiService
import com.paredgames.aijyakae.data.billing.BillingManager
import com.paredgames.aijyakae.data.dto.FetchQueuedCheckResponseDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedRequestDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedResponseDTO
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.dto.TranslateRequestDTO
import com.paredgames.aijyakae.data.dto.TranslateResponseDTO
import com.paredgames.aijyakae.data.dto.UploadImageRequestDTO
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
import java.util.UUID

class MakeJyakaeRepository (
    private val modelsLabApiService: ModelsLabApiService,
    private val deepLApiService: DeepLApiService,
    private val aijyakaeServerApiService: AijyakaeServerApiService,
    private val context:Context,
    private val imageDownloadManager: ImageDownloadManager,
    private val billingManager: BillingManager,
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

        var response: Response<TextTwoImageResponseDTO> =modelsLabApiService.textTwoImg(textTwoImageRequestDTO)
        /*
        test
        val textTwoImageResponseDTO=TextTwoImageResponseDTO()
        textTwoImageResponseDTO.status="processing"
        textTwoImageResponseDTO.id="97996919"
        val response:Response<TextTwoImageResponseDTO> = Response.success(textTwoImageResponseDTO)
        */
        if(response.isSuccessful){

            var responseData = response.body();
            Log.d("API Response status",responseData!!.status)
            //processing 상태라면?
            if(responseData.status=="processing"){
                val fetchQueuedResponseDTO = fetchQueued(response)
                responseData.output = fetchQueuedResponseDTO!!.body()!!.output
            }
            var base64Array=getImgForUrl(responseData.output[0])


            //이미지가 2~3초 후에 cdn에 올라오는 경우가 있어 그 경우를 대비하는 코드
            //리팩토링 필요
            base64Array = changeBase64Array(base64Array,responseData.output[0]);
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

    suspend fun uploadImg(response:MutableStateFlow<TextTwoImageResponseDTO>,makeJyakaeContent: MutableStateFlow<MakeJyakaeContent>){
        val uploadImageResponseDTO = UploadImageRequestDTO()
        uploadImageResponseDTO.id = response.value.id
        uploadImageResponseDTO.prompt=makeJyakaeContent.value.prompt
        uploadImageResponseDTO.negativePrompt=makeJyakaeContent.value.negativePrompt
        uploadImageResponseDTO.userName=UUID.randomUUID().toString()
        uploadImageResponseDTO.base64Img=getImgForUrlStringVer(response.value.output[0])
        uploadImageResponseDTO.width=makeJyakaeContent.value.resolution.width.toLong()
        uploadImageResponseDTO.height=makeJyakaeContent.value.resolution.height.toLong()

        val response = aijyakaeServerApiService.uploadImg(uploadImageResponseDTO)

        if(response.isSuccessful){
            Log.d("upload img","success")
        }else{
            Log.e("upload img","fail")
        }
    }

    private suspend fun fetchQueued(response:Response<TextTwoImageResponseDTO>): Response<FetchQueuedResponseDTO>? {
        val responseData = response.body()
        //밀리세컨드는 세컨드 곱하기 1000
        val estimated:Long=(responseData!!.eta*1000).toLong()
        //그 만큼 지연
        delay(estimated)
        val fetchQueuedRequestDTO=FetchQueuedRequestDTO()
        val id = responseData.id
        for(i in 1..100){
            // Fetch Queued 함수 호출
            val fetchQueuedResponse:Response<FetchQueuedCheckResponseDTO> = modelsLabApiService.fetchQueuedCheckStatus(id,fetchQueuedRequestDTO)
            if(fetchQueuedResponse.isSuccessful){
                val body = fetchQueuedResponse.body()
                if(body!!.status=="success"){
                    val realFetchQueuedResponse = modelsLabApiService.fetchQueued(id,fetchQueuedRequestDTO)
                    if(realFetchQueuedResponse.isSuccessful){
                        val innerBody = realFetchQueuedResponse.body()
                        if(innerBody!!.status=="success"){
                            return realFetchQueuedResponse
                        }
                    }
                }else{
                    //3초 대기 후 한번 더 시도하기
                    delay(3000)
                    Log.d("Fetch Queue Retry Failed","Retry Failed")
                }
            }
        }
        return null


    }
    private suspend fun changeBase64Array(base64Array:ByteArray?,url:String):ByteArray?{
        var res:ByteArray? = null
        var newBase64Array: ByteArray?;
        //10번 반복
        for(i:Int in 1..100){
            delay(2000L);
            try{
                newBase64Array=getImgForUrl(url)
                res = Base64.decode(newBase64Array,Base64.DEFAULT);
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

    private fun getImgForUrlStringVer(url: String): String {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val response = okHttpClient.newCall(request).execute()
        val responseBody = response.body()

        return responseBody?.string() ?: "Response body is null"
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

    fun downloadImage(bitmap: Bitmap, title:String){
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

    fun addBilling(){
        billingManager.startBilling()
    }

}