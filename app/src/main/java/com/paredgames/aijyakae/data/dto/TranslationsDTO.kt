package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName

class TranslationsDTO{
    @SerializedName("detected_source_language")
    lateinit var detectedSourceLanguage:String

    @SerializedName("text")
    lateinit var text:String

}