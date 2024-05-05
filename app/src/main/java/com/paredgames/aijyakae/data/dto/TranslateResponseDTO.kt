package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName

class TranslateResponseDTO {
    @SerializedName("translations")
    lateinit var translations: List<TranslationsDTO>
}

