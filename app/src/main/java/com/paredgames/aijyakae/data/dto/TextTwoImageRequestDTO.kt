package com.paredgames.aijyakae.data.dto

import com.google.gson.annotations.SerializedName
import com.paredgames.aijyakae.BuildConfig

class TextTwoImageRequestDTO {
    @SerializedName("key")
    val key:String = "BuildConfig.STABLE_DIFFUSION_API_KEY"
    @SerializedName("prompt")
    lateinit var prompt:String

    @SerializedName("model_id")
    var modelId:String="anything-v5"

    @SerializedName("negative_prompt")
    var negativePrompt:String="(((nsfw))),(((big breast)))"
    @SerializedName("width")
    var width:Int=1024
    @SerializedName("height")
    var height:Int=1024
    @SerializedName("samples")
    val samples:Int=1
    @SerializedName("num_inference_steps")
    val inferenceSteps:Int=41
    @SerializedName("safety_checker")
    val safetyChecker:String = "no"
    @SerializedName("enhance_prompt")
    val enhancePrompt:String="yes"
    @SerializedName("enhance_style")
    val enhanceStyle:String="misc-fairy-tale"
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
    @SerializedName("base64")
    val base64:String="yes"

}