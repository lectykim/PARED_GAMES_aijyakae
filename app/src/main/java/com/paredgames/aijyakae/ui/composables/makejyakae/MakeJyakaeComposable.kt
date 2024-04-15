package com.paredgames.aijyakae.ui.composables.makejyakae

import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel



    @Preview(
        widthDp = 600,
        heightDp = 900,
        showBackground = true,
        backgroundColor = 0xFFFFF2E6
    )
    @Composable
    fun PreviewStartScreenMakeJyakae(){
        AijyakaeTheme {
            StartScreenMakeJyakae()
        }
    }

    @Composable
    fun StartScreenMakeJyakae(
        makeJyakaeViewModel: MakeJyakaeViewModel= viewModel()
    ) {


        PromptTextArea(makeJyakaeViewModel = makeJyakaeViewModel)
    }

    @Composable
    fun PromptTextArea(
        modifier:Modifier=Modifier,
        makeJyakaeViewModel: MakeJyakaeViewModel
    ){
        var prompt by rememberSaveable {
            mutableStateOf("")
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "이제 나만의 자캐를 만들어 보세요!",
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )

            TextField(
                value = prompt,
                onValueChange ={value->prompt = value},
                modifier= modifier
                    .size(width = 300.dp, height = 300.dp)
                , maxLines = 5
            )

            Spacer(modifier = modifier.padding(40.dp))
            Text(
                text = "자캐의 특징을 키워드 칸에 적어주세요!",
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
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
                onClick = {  },
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
                    text = "이미지 생성하기",
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }