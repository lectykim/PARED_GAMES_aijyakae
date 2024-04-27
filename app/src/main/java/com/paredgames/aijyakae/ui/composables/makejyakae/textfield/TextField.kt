package com.paredgames.aijyakae.ui.composables.makejyakae.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paredgames.aijyakae.R

@Composable
fun CustomTextField(
    modifier: Modifier=Modifier,
    onValueChange:(String)->Unit,
    text:String
){
    BasicTextField(
        value = text,
        onValueChange =onValueChange,
        textStyle = LocalTextStyle.current.copy(fontSize = 25.sp),
        modifier=modifier
            .background(
                color = colorResource(id = R.color.white),
                shape = TextFieldDefaults.shape)
            .size(width = 480.dp, height = 300.dp)
    )
}