package com.paredgames.aijyakae.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.SamplingMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MakeJyakaeViewModel():ViewModel() {

    private val _makeJyakaeContent = MutableStateFlow(
        MakeJyakaeContent(
            "",
            "",
            SamplingMethod.DPMPP_2M_KARRAS,
            ModelId.ANYTHING_V3,
            25,
            -1,
            512,
            512
        )
    )

    val makeJyakaeContent:StateFlow<MakeJyakaeContent> = _makeJyakaeContent

    fun getStableDiffusion(){
        // TODO: 프롬프트 검증 로직
        if(true){
            //TODO: Stable Diffusion API 호출
        }else{
            // TODO: 경고띄우기
        }
    }

}