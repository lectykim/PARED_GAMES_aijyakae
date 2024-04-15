package com.paredgames.aijyakae.ui.composables.beforelogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel


    @Preview(
        widthDp = 600,
        heightDp = 900,
        showBackground = true,
        backgroundColor = 0xFFFFF2e6
    )
    @Composable
    fun PreviewStartScreenResultPage(){
        AijyakaeTheme {
            StartScreenResultPage()
        }
    }

    @Composable
    fun StartScreenResultPage(
        beforeLoginViewModel: BeforeLoginViewModel= viewModel()
    ) {
        FinalResultImage(beforeLoginViewModel = beforeLoginViewModel)
    }

    @Composable
    fun FinalResultImage(
        modifier: Modifier=Modifier,
        beforeLoginViewModel: BeforeLoginViewModel
    ) {
        Column (
            modifier= modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(R.string.final_result_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
            Image(
                painter = painterResource(id = R.drawable.man_image),
                contentDescription = "결과",
                modifier = modifier
                    .padding(40.dp, 150.dp, 40.dp, 0.dp)
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO: 구글로그인이랑 광고구현*/ },
                modifier = Modifier
                    .size(width = 480.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_download),
                    contentDescription = "다운로드",
                    modifier=modifier
                        .size(width = 50.dp, height = 50.dp)
                )
                Text(
                    text = "저장하기",
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
            Spacer(modifier = Modifier
                .padding(20.dp)
            )
            Button(
                onClick = { /*TODO: 구글로그인이랑 광고구현*/ },
                modifier = Modifier
                    .size(width = 480.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.make_other_art),
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
        }

    }