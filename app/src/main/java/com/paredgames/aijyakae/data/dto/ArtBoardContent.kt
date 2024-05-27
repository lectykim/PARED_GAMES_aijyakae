package com.paredgames.aijyakae.data.dto

data class ArtBoardContent(
    var prompt:String,
    var s3Url:String,
    var userName:String,
    var width:Int,
    var height:Int,
    var id:String
){
    fun changeItemSize(){
        if(width==1024&&height==1024){
            width=200
            height=200
        } else if(width==576&&height==1024){
            width=115
            height=200
        }else{
            width=200
            height=115
        }
    }

    fun changeItemSizeDetailPage(){
        if(width==1024&&height==1024){
            width=400
            height=400
        } else if(width==576&&height==1024){
            width=225
            height=400
        }else{
            width=400
            height=225
        }
    }
}
