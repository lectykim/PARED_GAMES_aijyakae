package com.paredgames.aijyakae.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paredgames.aijyakae.data.dto.ArtBoardContent
import com.paredgames.aijyakae.data.repository.ArtBoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ArtBoardViewModel(
    private val artBoardRepository: ArtBoardRepository
):ViewModel() {

    private var _artBoardList:List<ArtBoardContent> = emptyList()

    var artBoardList = _artBoardList

    fun getBoardList(page:Int) {
        viewModelScope.launch {
            val list = withContext(Dispatchers.Default){
                artBoardRepository.getBoardList(page)
            }
            if(list!=null){
                _artBoardList += list
                artBoardList=_artBoardList
            }
        }
    }

}