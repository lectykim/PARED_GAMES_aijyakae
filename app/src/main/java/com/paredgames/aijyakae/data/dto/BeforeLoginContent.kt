package com.paredgames.aijyakae.data.dto

import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex

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
}
