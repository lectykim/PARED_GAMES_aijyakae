package com.paredgames.aijyakae.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paredgames.aijyakae.data.repository.MakeJyakaeRepository

class MakeJyakaeViewModelFactory (
    private val makeJyakaeRepository: MakeJyakaeRepository
):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MakeJyakaeViewModel::class.java)){
            return MakeJyakaeViewModel(makeJyakaeRepository=makeJyakaeRepository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}