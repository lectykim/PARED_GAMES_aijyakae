package com.paredgames.aijyakae.data.api

import com.paredgames.aijyakae.data.dto.GetBoardListResponseDTO
import com.paredgames.aijyakae.data.dto.UploadImageRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AijyakaeServerApiService {

    @GET("/api/v1/get-board-list")
    suspend fun getBoardList(
        @Query("page") page:Int
    ):Response<List<GetBoardListResponseDTO>>

    @GET("/api/v1/get-board-item/{id}")
    suspend fun getBoardItem(
        @Path("id") id:String
    ):Response<GetBoardListResponseDTO>

    @POST("/api/v1/upload-img")
    suspend fun uploadImg(
        @Body uploadImageRequestDTO: UploadImageRequestDTO
    ):Response<GetBoardListResponseDTO>
}