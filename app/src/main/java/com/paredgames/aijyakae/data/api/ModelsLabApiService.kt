package com.paredgames.aijyakae.data.api

import com.paredgames.aijyakae.data.dto.FetchQueuedCheckResponseDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedRequestDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedResponseDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageRequestDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ModelsLabApiService {


    @POST("/api/v6/images/text2img")
    suspend fun textTwoImg(
        @Body textTwoImageRequestDTO: TextTwoImageRequestDTO
    ):Response<TextTwoImageResponseDTO>

    @POST("/api/v6/realtime/fetch/{id}")
    suspend fun fetchQueued(
        @Path("id") id:String,
        @Body fetchQueuedRequestDTO: FetchQueuedRequestDTO
    ):Response<FetchQueuedResponseDTO>

    @POST("/api/v6/realtime/fetch/{id}")
    suspend fun fetchQueuedCheckStatus(
        @Path("id") id:String,
        @Body fetchQueuedRequestDTO: FetchQueuedRequestDTO
    ):Response<FetchQueuedCheckResponseDTO>
}