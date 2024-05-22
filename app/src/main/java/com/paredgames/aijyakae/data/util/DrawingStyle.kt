package com.paredgames.aijyakae.data.util

import com.paredgames.aijyakae.R

enum class DrawingStyle(
    val title:Int,
    val image:Int,
    val modelId:String
) {
    DRAWING_STYLE_TWO_FIVE_D(R.string.text_2_5_d,R.drawable.camelia25d_item,"camelliamix25d"),
    DRAWING_STYLE_ANIMATION(R.string.text_animation,R.drawable.animation_item,"anything-v5"),
    DRAWING_STYLE_PASTEL_ONE(R.string.text_pastel_1,R.drawable.pastel_2_item,"pastel-2"),
    DRAWING_STYLE_PASTEL_TWO(R.string.text_pastel_2,R.drawable.pastel_1_item,"cnwi74tjsdfw"),
    DRAWING_STYLE_CUTE(R.string.text_cute,R.drawable.cute_item,"rupemixanime")
}