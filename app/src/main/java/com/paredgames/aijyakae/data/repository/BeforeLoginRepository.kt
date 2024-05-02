package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.paredgames.aijyakae.BuildConfig
import com.paredgames.aijyakae.data.api.DeepLApiService
import com.paredgames.aijyakae.data.api.ModelsLabApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.dto.TranslateRequestDTO
import com.paredgames.aijyakae.data.dto.TranslateResponseDTO
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

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
        val prompt=promptResponse?.translations?.text
        if (prompt != null) {
            Log.d("DeepL Api Response",prompt)
            textTwoImageRequestDTO.prompt=prompt
        }
        val response: Response<TextTwoImageResponseDTO> =modelsLabApiService.textTwoImg(BuildConfig.DEEPL_AUTH_KEY,textTwoImageRequestDTO)

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

    private suspend fun translatePrompt(prompt:String):TranslateResponseDTO?{
        val translateRequestDTO = TranslateRequestDTO()
        translateRequestDTO.text=prompt
        val response:Response<TranslateResponseDTO> = deepLApiService.translate(translateRequestDTO)

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
}