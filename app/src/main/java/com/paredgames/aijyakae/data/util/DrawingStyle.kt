package com.paredgames.aijyakae.data.util

import com.paredgames.aijyakae.R

enum class DrawingStyle(
    val title:Int,
    val image:Int,
    val modelId:String
) {
    DRAWING_STYLE_TWO_FIVE_D(R.string.text_2_5_d,R.drawable.item_logo,"CamelliaMix_2.5D"),
    DRAWING_STYLE_ANIMATION(R.string.text_animation,R.drawable.item_logo,"anything-v5"),
    DRAWING_STYLE_PASTEL_ONE(R.string.text_pastel_1,R.drawable.item_logo,"pastel-2"),
    DRAWING_STYLE_PASTEL_TWO(R.string.text_pastel_2,R.drawable.item_logo,"cnwi74tjsdfw"),
    DRAWING_STYLE_CUTE(R.string.text_cute,R.drawable.item_logo,"rupemixanime")
}