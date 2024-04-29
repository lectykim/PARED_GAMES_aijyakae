package com.paredgames.aijyakae.data.repository

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.StyleRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

class BeforeLoginRepository(
    private val apiService: ApiService,
    private val context:Context
){

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY,Context.MODE_PRIVATE)

    suspend fun getTextTwoImg(beforeLoginContent: MutableStateFlow<BeforeLoginContent>):TextTwoImageResponseDTO?{
        val textTwoImageRequestDTO = beforeLoginContent.value.toDto()
        val response: Response<TextTwoImageResponseDTO> =apiService.textTwoImg(textTwoImageRequestDTO)

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


}