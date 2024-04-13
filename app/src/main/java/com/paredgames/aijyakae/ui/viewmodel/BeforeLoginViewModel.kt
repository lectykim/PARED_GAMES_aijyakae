package com.paredgames.aijyakae.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BeforeLoginViewModel(): ViewModel() {


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
            //TODO: stable diffusion api 호출
        } else{
            //TODO : 처음으로
        }
    }

}