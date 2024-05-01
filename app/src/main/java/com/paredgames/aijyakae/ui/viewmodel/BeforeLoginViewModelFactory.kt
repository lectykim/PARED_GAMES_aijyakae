package com.paredgames.aijyakae.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository

class BeforeLoginViewModelFactory(
    private val beforeLoginRepository: BeforeLoginRepository
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BeforeLoginViewModel::class.java)){
            return BeforeLoginViewModel(beforeLoginRepository = beforeLoginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}