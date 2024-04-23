package com.paredgames.aijyakae.data.repository

import android.util.Log
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

class BeforeLoginRepository(
    private val apiService: ApiService
){
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
}