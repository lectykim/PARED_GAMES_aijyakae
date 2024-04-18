package com.paredgames.aijyakae.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BeforeLoginViewModel(
    private val beforeLoginRepository: BeforeLoginRepository
): ViewModel() {


    private val _beforeLoginContent = MutableStateFlow(
        BeforeLoginContent(
            BeforeLoginSex.None,
            BeforeLoginDrawStyle.None,
            BeforeLoginDrawSize.None
        )
    )



    val beforeLoginContent: StateFlow<BeforeLoginContent> = _beforeLoginContent.asStateFlow()

    suspend fun getStableDiffusion(){
        if(beforeLoginContent.value.getAllNotNone()){
            beforeLoginRepository.getTextTwoImg(_beforeLoginContent)
        } else{
            Log.d("Select Failure","Select Failure")
        }
    }



}