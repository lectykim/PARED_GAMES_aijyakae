package com.paredgames.aijyakae.ui.composables.makejyakae.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paredgames.aijyakae.data.util.FontData

@Composable
fun ItemLogo(
    onClick:()->Unit,
    image:Int,
    title:Int,
    width: Dp =90.dp,
    height: Dp =90.dp,
    borderStroke: BorderStroke= BorderStroke(0.dp, Color.Black)
){
    Column (
        modifier=Modifier
            .border(borderStroke)
    ){
        Image(painter = painterResource(id = image),
            contentDescription = stringResource(id = title),
            modifier= Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(9.dp))
                .clickable(onClick = onClick),
        )
        Text(text = stringResource(id = title),fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontData.maruboriFontFamily,
            fontWeight = FontWeight.Normal,)
    }


}