package com.paredgames.aijyakae.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.theme.PastelPink

class BeforeLoginComposable {

    val maruboriFontFamily = FontFamily(
        Font(R.font.maruborilight, FontWeight.Light),
        Font(R.font.maruboriregular,FontWeight.Normal),
        Font(R.font.maruboribold,FontWeight.Bold)
    )



    @Preview(
        widthDp = 600,
        heightDp = 900,
        showBackground = true,
        backgroundColor = 0xFFFFF2e6
    )
    @Composable
    fun PreviewStartScreenBeforeLogin(){
        AijyakaeTheme {
            StartScreenBeforeLogin()
        }
    }

    @Composable
    fun StartScreenBeforeLogin(
        modifier: Modifier=Modifier,
    ){
        var isFirstClicked by rememberSaveable { mutableStateOf(false) }
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(R.string.first_enter_page),
                fontFamily = maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
        }
        Column (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier=Modifier
                    .size(width = 480.dp, height = 120.dp)
                    ,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.first_enter_page_button_start),
                    fontFamily = maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp)
            }
        }

    }

}