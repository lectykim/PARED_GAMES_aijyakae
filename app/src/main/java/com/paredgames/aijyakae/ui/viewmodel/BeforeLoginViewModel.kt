package com.paredgames.aijyakae.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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

    fun getStableDiffusion(){
        if(beforeLoginContent.value.getAllNotNone()){
            //TODO: stable diffusion api 호출
        } else{
            //TODO : 처음으로
        }
    }



}