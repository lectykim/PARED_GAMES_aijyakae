package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.paredgames.aijyakae.BuildConfig
import com.paredgames.aijyakae.data.api.DeepLApiService
import com.paredgames.aijyakae.data.api.ModelsLabApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.FetchQueuedCheckResponseDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedRequestDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedResponseDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.dto.TranslateRequestDTO
import com.paredgames.aijyakae.data.dto.TranslateResponseDTO
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import java.lang.IllegalArgumentException
import java.net.URL

class BeforeLoginRepository(
    private val modelsLabApiService: ModelsLabApiService,
    private val deepLApiService: DeepLApiService,
    private val context:Context,
    private val imageDownloadManager: ImageDownloadManager
){

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY_BEFORE_LOGIN,Context.MODE_PRIVATE)

    suspend fun getTextTwoImg(beforeLoginContent: MutableStateFlow<BeforeLoginContent>):TextTwoImageResponseDTO?{
        val textTwoImageRequestDTO = beforeLoginContent.value.toDto()
        val promptResponse=translatePrompt(textTwoImageRequestDTO.prompt)
        val prompt= promptResponse?.translations?.get(0)
        if (prompt != null) {
            Log.d("DeepL Api Response", prompt.text)
            textTwoImageRequestDTO.prompt=prompt.text

        }
        val response: Response<TextTwoImageResponseDTO> =modelsLabApiService.textTwoImg(textTwoImageRequestDTO)

        if(response.isSuccessful){
            var responseData = response.body();
            Log.d("API Response",responseData!!.status)
            if(responseData.status=="processing"){
                val fetchQueuedResponseDTO = fetchQueued(response)
                responseData.output = fetchQueuedResponseDTO!!.body()!!.output
            }
            var base64Array=getImgForUrl(responseData!!.output[0])
            Log.d("Base64 Img",base64Array.toString())

            base64Array = changeBase64Array(base64Array,responseData.output[0]);

            if(base64Array==null)
                Log.e("Image Load Error","because cdn response is null")

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

    private suspend fun fetchQueued(response:Response<TextTwoImageResponseDTO>): Response<FetchQueuedResponseDTO>? {
        val responseData = response.body()
        //밀리세컨드는 세컨드 곱하기 1000
        val estimated:Long=(responseData!!.eta*1000).toLong()
        //그 만큼 지연
        delay(estimated)
        val fetchQueuedRequestDTO= FetchQueuedRequestDTO()
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
    private suspend fun translatePrompt(prompt:String):TranslateResponseDTO?{
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
}