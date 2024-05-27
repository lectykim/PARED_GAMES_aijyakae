package com.paredgames.aijyakae.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.paredgames.aijyakae.data.api.AijyakaeServerApiService
import com.paredgames.aijyakae.data.dto.ArtBoardContent
import com.paredgames.aijyakae.data.dto.GetBoardListResponseDTO
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import java.util.stream.Collectors
import kotlin.streams.toList

class ArtBoardRepository(
    private val aijyakaeServerApiService: AijyakaeServerApiService,
    private val context:Context
) {
    private var sharedPreferences:SharedPreferences

    init{
        sharedPreferences=context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY_BEFORE_LOGIN,Context.MODE_PRIVATE)
    }

    suspend fun getBoardList(page:Int):List<ArtBoardContent>?{
        val response = aijyakaeServerApiService.getBoardList(page)

        if(response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body.stream().map { it->it.toEntity() }.collect(Collectors.toList())
            }
        }
        return null
    }

    suspend fun getBoardItem(id:String):ArtBoardContent?{
        val response = aijyakaeServerApiService.getBoardItem(id)

        if(response.isSuccessful){
            val body = response.body()
            if(body!=null){
                return body.toEntity()
            }
        }
        return null
    }
}