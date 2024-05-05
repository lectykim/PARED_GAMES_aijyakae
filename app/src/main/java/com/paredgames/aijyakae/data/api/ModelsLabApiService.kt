package com.paredgames.aijyakae.data.api

import com.paredgames.aijyakae.data.dto.TextTwoImageRequestDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ModelsLabApiService {


    @POST("/api/v6/images/text2img")
    suspend fun textTwoImg(
        @Body textTwoImageRequestDTO: TextTwoImageRequestDTO
    ):Response<TextTwoImageResponseDTO>

}