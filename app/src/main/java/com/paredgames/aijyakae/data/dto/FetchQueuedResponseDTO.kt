package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName

class FetchQueuedResponseDTO {

    @SerializedName("status")
    lateinit var status:String

    @SerializedName("output")
    lateinit var output:Array<String>

    @SerializedName("id")
    lateinit var id:String
}