package com.paredgames.aijyakae.data.dto

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

class TextTwoImageResponseDTO {

    @SerializedName("status")
    lateinit var status:String


    @SerializedName("generationTime")
    lateinit var generationTime:String

    @SerializedName("id")
    lateinit var id:String

    @SerializedName("output")
    lateinit var output:Array<String>

    @SerializedName("proxy_links")
    lateinit var proxyLink:Array<String>

    @SerializedName("nsfw_content_detected")
    lateinit var nsfwContentDetected:String

    lateinit var base64Img:Bitmap

}