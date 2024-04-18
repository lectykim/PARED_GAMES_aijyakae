package com.paredgames.aijyakae.data.repository

import android.util.Log
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.TextTwoImageRequestDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import retrofit2.Retrofit

class BeforeLoginRepository(
    private val apiService: ApiService
){
    suspend fun getTextTwoImg(beforeLoginContent: BeforeLoginContent){
        val textTwoImageRequestDTO = beforeLoginContent.toDto()
        val response: Response<TextTwoImageResponseDTO> =apiService.textTwoImg(textTwoImageRequestDTO)

        Log.d("http call response",response.toString())
    }
}