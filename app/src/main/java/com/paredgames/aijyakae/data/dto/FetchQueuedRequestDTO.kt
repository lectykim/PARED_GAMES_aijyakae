package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName
import com.paredgames.aijyakae.BuildConfig


class FetchQueuedRequestDTO {
    @SerializedName("key")
    val key:String = BuildConfig.STABLE_DIFFUSION_API_KEY
}