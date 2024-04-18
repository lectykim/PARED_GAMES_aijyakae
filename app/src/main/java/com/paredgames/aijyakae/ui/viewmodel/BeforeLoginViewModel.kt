package com.paredgames.aijyakae.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    fun getStableDiffusion(){
        if(beforeLoginContent.value.getAllNotNone()){
            viewModelScope.launch {
                    beforeLoginRepository.getTextTwoImg(_beforeLoginContent)
            }
        } else{
            Log.d("Select Failure","Select Failure")
        }
    }



}