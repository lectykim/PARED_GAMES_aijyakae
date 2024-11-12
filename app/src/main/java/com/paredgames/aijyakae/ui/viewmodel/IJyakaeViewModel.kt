package com.paredgames.aijyakae.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.SamplingMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface IJyakaeViewModel {
     val _makeJyakaeContent: StateFlow<MakeJyakaeContent>
         get() = MutableStateFlow(
             MakeJyakaeContent(
                 "",
                 "",
                 SamplingMethod.DPMPP_2M_KARRAS,
                 ModelId.ANYTHING_V3,
                 25,
                 -1,
                 512,
                 512,
                 DrawingStyle.DRAWING_STYLE_ANIMATION,
                 Resolution.ONE_BY_ONE
             )
         )
    val _loadingStateFlow: StateFlow<Boolean>
        get() = MutableStateFlow(false)
    val _response: StateFlow<TextTwoImageResponseDTO>
        get() = MutableStateFlow(TextTwoImageResponseDTO())
    val _isFinal: StateFlow<Boolean>
         get() = MutableStateFlow(false)

    val makeJyakaeContent: StateFlow<MakeJyakaeContent>
        get() = MutableStateFlow(
            MakeJyakaeContent(
                "",
                "",
                SamplingMethod.DPMPP_2M_KARRAS,
                ModelId.ANYTHING_V3,
                25,
                -1,
                512,
                512,
                DrawingStyle.DRAWING_STYLE_ANIMATION,
                Resolution.ONE_BY_ONE
            )
        )
    val loading: StateFlow<Boolean>
        get() = MutableStateFlow(false)
    val response: StateFlow<TextTwoImageResponseDTO>
        get() = MutableStateFlow(TextTwoImageResponseDTO())
    val isFinal: StateFlow<Boolean>
        get() = MutableStateFlow(false)

    fun getStableDiffusion(){


    }

    fun uploadImg(){

    }

    fun getPreferenceData(key:String,defaultValue:String):String

    fun setPreferenceData(key:String,value:String)



    fun downloadImage(bitmap: Bitmap, title:String)

    fun setInitialState()

    fun addAd()

    fun billingPayment()

    fun verifyBilling()
}