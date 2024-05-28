package com.paredgames.aijyakae.ui.viewmodel

import android.util.Log
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
    private var _currentArtBoardItem:MutableStateFlow<ArtBoardContent> =MutableStateFlow(ArtBoardContent("","","",0,0,""))

    var artBoardList = _artBoardList
    var currentArtBoardItem = _currentArtBoardItem.asStateFlow()

    init{
        getBoardList(0)
        //getFakeBoardData(0)
    }
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

    /*fun getFakeBoardData(page:Int){
        if(boardFakeData.size<=(page+1)*10)
            return
        val fakeBoardList = boardFakeData.subList(page*10,(page+1)*10)
        _artBoardList += fakeBoardList
        artBoardList=_artBoardList
    }*/

    fun getBoardItem(id:String){
        viewModelScope.launch {
            val dto = withContext(Dispatchers.Default){
                artBoardRepository.getBoardItem(id)
            }
            if(dto!=null){
                _currentArtBoardItem.value = dto
            }else{
                Log.e("not returned","")
            }
        }
    }

    fun getPreferenceData(key:String,defaultValue:String):String{
        return artBoardRepository.getData(key,defaultValue)
    }

    fun setPreferenceData(key:String,value:String){
        artBoardRepository.saveData(key,value)
    }

}