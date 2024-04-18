package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName
import com.paredgames.aijyakae.BuildConfig

class TextTwoImageRequestDTO {
    @SerializedName("key")
    val key:String = BuildConfig.STABLE_DIFFUSION_API_KEY
    @SerializedName("prompt")
    lateinit var prompt:String
    @SerializedName("negetive_prompt")
    lateinit var negativePrompt:String
    @SerializedName("width")
    val width:Int=512
    @SerializedName("height")
    val height:Int=512
    @SerializedName("samples")
    val samples:Int=1
    @SerializedName("num_inference_steps")
    val inferenceSteps:Int=20
    @SerializedName("safety_checker")
    val safetyChecker:String = "no"
    @SerializedName("enhance_prompt")
    val enhancePrompt:String="yes"
    @SerializedName("temp")
    val temp:String ="yes"
    @SerializedName("seed")
    val seed: String? =null
    @SerializedName("guidance_scale")
    val guidanceScale:Double=7.5
    @SerializedName("webhook")
    val webhook:String?=null
    @SerializedName("track_id")
    val trackId:String?=null

}