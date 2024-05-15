package com.paredgames.aijyakae.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paredgames.aijyakae.data.repository.ArtBoardRepository

class ArtBoardViewModelFactory(
    private val artBoardRepository: ArtBoardRepository
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArtBoardViewModel::class.java)){
            return ArtBoardViewModel(artBoardRepository=artBoardRepository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}