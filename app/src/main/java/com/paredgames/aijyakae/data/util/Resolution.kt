package com.paredgames.aijyakae.data.util

import com.paredgames.aijyakae.R

enum class Resolution(
    val width:Int,
    val height:Int,
    val title:Int,
    val image:Int
) {
    ONE_BY_ONE(1024,1024, R.string.one_by_one,R.drawable.item_logo),
    NINE_BY_SIXTEEN(576,1024,R.string.nine_by_sixteen,R.drawable.item_logo),
    SIXTEEN_BY_NINE(1024,576,R.string.sixteen_by_nine,R.drawable.item_logo)
}