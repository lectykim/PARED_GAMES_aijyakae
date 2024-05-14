package com.paredgames.aijyakae.ui.viewmodel

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paredgames.aijyakae.data.dto.BeforeLoginContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


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


    private val _loading = MutableStateFlow(false)
    private val _response = MutableStateFlow(TextTwoImageResponseDTO())
    private val _isFinal = MutableStateFlow(false)



    val beforeLoginContent: StateFlow<BeforeLoginContent> = _beforeLoginContent.asStateFlow()
    val loading:StateFlow<Boolean> = _loading.asStateFlow()
    val response:StateFlow<TextTwoImageResponseDTO> = _response.asStateFlow()
    val isFinal:StateFlow<Boolean> = _isFinal.asStateFlow()

    fun getStableDiffusion(){
        if(beforeLoginContent.value.getAllNotNone()){
            _loading.value=true

            viewModelScope.launch {
                val dto =withContext(Dispatchers.Default){
                     beforeLoginRepository.getTextTwoImg(_beforeLoginContent)
                }
                if(dto!=null){
                    _response.value=dto
                    _loading.value=false
                    _isFinal.value=true
                }else{
                    Log.e("not returned","")
                }

            }




        } else{
            Log.d("Select Failure","Select Failure")

        }

    }

    fun getPreferenceData(key:String,defaultValue:String):String{
        return beforeLoginRepository.getData(key,defaultValue)
    }

    fun setPreferenceData(key:String,value:String){
        beforeLoginRepository.saveData(key,value)
    }

    fun downloadImage(uri:String,title:String){
        beforeLoginRepository.downloadImage(uri,title)
    }




}