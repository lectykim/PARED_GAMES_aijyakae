package com.paredgames.aijyakae.ui.viewmodel

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paredgames.aijyakae.data.api.AijyakaeServerApiService
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.repository.MakeJyakaeRepository
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.SamplingMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MakeJyakaeViewModel(
    private val makeJyakaeRepository: MakeJyakaeRepository
):ViewModel() {

    private val _makeJyakaeContent = MutableStateFlow(
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
    private val _loading = MutableStateFlow(false)
    private val _response = MutableStateFlow(TextTwoImageResponseDTO())
    private val _isFinal = MutableStateFlow(false)

    val makeJyakaeContent:StateFlow<MakeJyakaeContent> = _makeJyakaeContent
    val loading:StateFlow<Boolean> = _loading.asStateFlow()
    val response:StateFlow<TextTwoImageResponseDTO> = _response.asStateFlow()
    val isFinal:StateFlow<Boolean> = _isFinal.asStateFlow()


    fun getStableDiffusion(){
        _loading.value=true

        viewModelScope.launch {
            val dto = withContext(Dispatchers.Default){
                makeJyakaeRepository.getTextTwoImg(_makeJyakaeContent)
            }
            if(dto!=null){
                _response.value=dto
                _loading.value=false
                _isFinal.value=true
            }else{
                Log.e("not returned","")
            }
        }
    }

    fun uploadImg(){
        viewModelScope.launch {
            val dto = withContext(Dispatchers.Default){
                makeJyakaeRepository.uploadImg(_response,_makeJyakaeContent)
            }
        }
    }

    fun getPreferenceData(key:String,defaultValue:String):String{
        return makeJyakaeRepository.getData(key,defaultValue)
    }

    fun setPreferenceData(key:String,value:String){
        makeJyakaeRepository.saveData(key,value)
    }



    fun downloadImage(bitmap: Bitmap, title:String){
        makeJyakaeRepository.downloadImage(bitmap,title)
    }

    fun setInitialState(){
        _isFinal.value=false
    }

    fun addAd(){
        makeJyakaeRepository.addAd()
    }
}