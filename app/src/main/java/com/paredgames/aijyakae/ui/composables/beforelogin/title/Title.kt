package com.paredgames.aijyakae.ui.composables.beforelogin.title

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.paredgames.aijyakae.data.util.FontData


@Composable
fun TitleText(
    modifier: Modifier=Modifier,
    @StringRes titleText:Int
) {
    Text(
        fontFamily = FontData.maruboriFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        maxLines = Int.MAX_VALUE,
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        text = stringResource(id = titleText)
    )
}