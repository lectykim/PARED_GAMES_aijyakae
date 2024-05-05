package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName

class TranslateRequestDTO {
    @SerializedName("text")
    lateinit var text:Array<String>

    @SerializedName("target_lang")
    val targetLang="EN-US"
}