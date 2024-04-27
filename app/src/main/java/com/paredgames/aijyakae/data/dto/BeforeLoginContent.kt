package com.paredgames.aijyakae.data.dto

import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import java.lang.StringBuilder

data class BeforeLoginContent(
    var sex: BeforeLoginSex,
    var drawStyle: BeforeLoginDrawStyle,
    var drawSize: BeforeLoginDrawSize
){
    fun getAllNotNone(): Boolean {
        return sex!=BeforeLoginSex.None&&
            drawSize!=BeforeLoginDrawSize.None&&
            drawStyle!=BeforeLoginDrawStyle.None;
    }

    private fun getPrompt():String{

        return StringBuilder()
            .append(sex.stringValue)
            .append(" , ")
            .append(drawStyle.stringValue)
            .append(" , ")
            .append(drawSize.stringValue)
            .toString()
    }
    fun toDto():TextTwoImageRequestDTO{
        val textTwoImageRequestDTO:TextTwoImageRequestDTO = TextTwoImageRequestDTO()
        textTwoImageRequestDTO.prompt=getPrompt()
        return textTwoImageRequestDTO
    }
}
