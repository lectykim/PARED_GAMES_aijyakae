package com.paredgames.aijyakae.data.api

import com.paredgames.aijyakae.data.dto.TranslateRequestDTO
import com.paredgames.aijyakae.data.dto.TranslateResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepLApiService {

    @POST("/v2/translate")
    suspend fun translate(@Body translateRequestDTO: TranslateRequestDTO):Response<TranslateResponseDTO>

}