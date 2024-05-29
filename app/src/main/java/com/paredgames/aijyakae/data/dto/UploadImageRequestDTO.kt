package com.paredgames.aijyakae.data.dto

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

class UploadImageRequestDTO {
    @SerializedName("id")
    lateinit var id:String

    @SerializedName("prompt")
    lateinit var prompt:String

    @SerializedName("negativePrompt")
    lateinit var negativePrompt:String

    @SerializedName("userName")
    lateinit var userName:String

    @SerializedName("imageBase64")
    var base64Img:String?=null

    @SerializedName("width")
    var width:Long=0

    @SerializedName("height")
    var height:Long=0
}