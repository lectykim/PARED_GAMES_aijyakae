package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class GetBoardListResponseDTO {

    @SerializedName("id")
    var id:Long=0

    @SerializedName("fileId")
    lateinit var fileId:String

    @SerializedName("prompt")
    lateinit var prompt:String

    @SerializedName("negativePrompt")
    lateinit var negativePrompt:String

    @SerializedName("userName")
    lateinit var userName:String

    @SerializedName("s3Url")
    lateinit var s3Url:String

    @SerializedName("createDateTime")
    lateinit var createDateTime:LocalDateTime

    @SerializedName("width")
    var width:Int=0

    @SerializedName("height")
    var height:Int=0

    fun toEntity():ArtBoardContent{
        return ArtBoardContent(prompt,s3Url,userName,width,height)
    }

}