package com.paredgames.aijyakae.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MakeJyakaeViewModelFactory (

):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MakeJyakaeViewModel::class.java)){
            return MakeJyakaeViewModel() as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}