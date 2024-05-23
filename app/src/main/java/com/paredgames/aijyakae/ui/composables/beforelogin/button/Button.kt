package com.paredgames.aijyakae.ui.composables.beforelogin.button

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo

@Composable
fun NextButton(
    modifier: Modifier=Modifier,
    onClick:()->Unit,
    @StringRes buttonText:Int
) {
    Button(
        onClick = onClick,
        modifier.size(width = 480.dp, height = 80.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.warm_button_orange),
            contentColor = Color.White,
            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = buttonText),
            fontFamily = FontData.maruboriFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun NextImageButton(
    modifier: Modifier=Modifier,
    onClick: () -> Unit,
    @StringRes buttonText:Int,
    @DrawableRes buttonImage:Int
) {
    ItemLogo(onClick = { onClick() }, image = buttonImage, title = buttonText, width = 90.dp, height = 90.dp)
}