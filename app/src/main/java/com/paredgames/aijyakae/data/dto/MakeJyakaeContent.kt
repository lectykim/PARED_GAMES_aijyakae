package com.paredgames.aijyakae.data.dto

import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.SamplingMethod

data class MakeJyakaeContent(
    var prompt: String,
    var negativePrompt:String,
    var samplingMethod:SamplingMethod,
    var modelId:ModelId,
    var step:Int,
    var seed:Int,
    var width:Int,
    var height:Int
){
    fun toDto():TextTwoImageRequestDTO{
        val textTwoImageRequestDTO=TextTwoImageRequestDTO()
        textTwoImageRequestDTO.prompt=prompt
        textTwoImageRequestDTO.negativePrompt=negativePrompt
        return textTwoImageRequestDTO
    }
}
