package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName

class TranslateRequestDTO {
    @SerializedName("text")
    lateinit var text:String

    @SerializedName("target_lang")
    val targetLang="EN-US"

    @SerializedName("source_lang")
    val sourceLang="KO"
}