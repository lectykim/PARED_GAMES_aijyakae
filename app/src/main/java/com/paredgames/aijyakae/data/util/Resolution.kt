package com.paredgames.aijyakae.data.util

enum class Resolution(
    val width:Int,
    val height:Int
) {
    ONE_BY_ONE(1024,1024),
    NINE_BY_SIXTEEN(576,1024),
    SIXTEEN_BY_NINE(1024,576)
}