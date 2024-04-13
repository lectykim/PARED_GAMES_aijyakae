package com.paredgames.aijyakae.ui.composables

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.theme.PastelPink
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel

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
        beforeLoginViewModel: BeforeLoginViewModel=viewModel()
    ){

        var goNext by rememberSaveable {
            mutableIntStateOf(2)
        }

        if(goNext == 0){
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
                    onClick = { goNext++ },
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
        else if(goNext==1){
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.select_first_page),
                    fontFamily = maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 50.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp
                )
                Row (
                    modifier=modifier
                        .padding(40.dp,400.dp,40.dp,0.dp)
                ){
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Man
                                     }, modifier=modifier.size(width = 150.dp, height = 100.dp) ,content = {
                        Image(painter = painterResource(id = R.drawable.man_character),
                            contentDescription = "남자",
                            modifier=modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp,Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "남성")
                    },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )

                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Women
                    },modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                        Image(painter = painterResource(id = R.drawable.women_character),
                            contentDescription = "남자",
                            modifier=modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp,Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "3D")
                    },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Asexual
                                     },
                        modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                        Image(painter = painterResource(id = R.drawable.asexual),
                            contentDescription = "남자",
                            modifier=modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp,Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "2D")
                    },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )



                }
            }



        }
        else if(goNext==2){
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.select_second_page),
                    fontFamily = maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 50.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp
                )
                Row (
                    modifier=modifier
                        .padding(40.dp,400.dp,40.dp,0.dp)

                ){
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Women
                    },modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                        Image(painter = painterResource(id = R.drawable.women_character),
                            contentDescription = "남자",
                            modifier=modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp,Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "3D")
                    },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Asexual
                    },
                        modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                            Image(painter = painterResource(id = R.drawable.asexual),
                                contentDescription = "남자",
                                modifier=modifier
                                    .size(width = 70.dp, height = 70.dp)
                                    .clip(CircleShape)
                                    .border(2.dp,Color.Gray, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "2D")
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )



                }

                Row (
                    modifier=modifier
                        .padding(40.dp,20.dp,40.dp,0.dp)

                ){
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Women
                    },modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                        Image(painter = painterResource(id = R.drawable.women_character),
                            contentDescription = "남자",
                            modifier=modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp,Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "애니메이션")
                    },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        goNext++
                        beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Asexual
                    },
                        modifier=modifier.size(width = 150.dp, height = 100.dp) , content = {
                            Image(painter = painterResource(id = R.drawable.asexual),
                                contentDescription = "남자",
                                modifier=modifier
                                    .size(width = 70.dp, height = 70.dp)
                                    .clip(CircleShape)
                                    .border(2.dp,Color.Gray, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "실사")
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.warm_button_orange),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                            disabledContentColor = Color.White
                        )
                    )



                }
            }



        } else if(goNext == 3){
            // TODO: 전신 반신 여부 선택
        } else if(goNext == 4){
            // TODO: 수고하셨다는 로고와 함께 스테이블 디퓨전 정보 받아오고 추가로 이용하고 싶으면 로그인 화면 띄워주기
        }


    }

}