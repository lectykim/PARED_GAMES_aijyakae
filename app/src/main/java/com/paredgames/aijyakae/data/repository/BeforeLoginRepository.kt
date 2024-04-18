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
    suspend fun getTextTwoImg(beforeLoginContent: MutableStateFlow<BeforeLoginContent>){
        val textTwoImageRequestDTO = beforeLoginContent.toDto()
        val response: Response<TextTwoImageResponseDTO> =apiService.textTwoImg(textTwoImageRequestDTO)

        Log.d("http call response",response.toString())
    }
}